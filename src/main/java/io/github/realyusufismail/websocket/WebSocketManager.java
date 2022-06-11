/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
 */

package io.github.realyusufismail.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.neovisionaries.ws.client.*;
import io.github.realyusufismail.websocket.core.CloseCode;
import io.github.realyusufismail.websocket.core.OpCode;
import io.github.realyusufismail.websocket.handle.OnHandler;
import io.github.realyusufismail.ydw.GateWayIntent;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.YDWInfo;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydwreg.YDWReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebSocketManager extends WebSocketAdapter implements WebSocketListener {

    // The session id. This is basically a key that stores the past activity of the bot.
    @Nullable
    private static volatile String sessionId = null;
    private final Logger logger = LoggerFactory.getLogger(WebSocketManager.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    // The bots token.
    private String token;
    // The gateway intents
    private int intent;
    // The core class of the wrapper,
    private final YDWReg ydw;
    // Create a WebSocketFactory instance.
    private WebSocket ws;
    @NotNull
    ObjectMapper mapper = new ObjectMapper();
    // The sequence number, used for resuming sessions and heartbeats.
    @Nullable
    private Integer seq = null;
    // Used to determine if any heart beats hve been missed.
    private int missedHeartbeats = 0;
    // The status of the bot e.g. online, idle, dnd, invisible etc.
    private String status;
    private int largeThreshold;
    // The activity of the bot e.g. playing, streaming, listening, watching etc.
    private ActivityConfig activity;

    // The inner d key of the invalid session event is a boolean that indicates whether the session
    // may be resumable. See Connecting and Resuming for more information.
    private Boolean isResumable;

    // Used to indicate that the bot has connected to the gateway.
    private boolean connected = false;

    // The thread used for the heartbeat. Needed in cases such as disconnect.
    private volatile Future<?> heartbeatThread;


    public WebSocketManager(YDWReg ydw) {
        this.ydw = ydw;
        this.isResumable = ydw.isResumable();
    }

    public WebSocketManager setRequiredDetails(String token, Integer intent, String status,
            int largeThreshold, ActivityConfig activity) throws WebSocketException, IOException {
        this.token = token;
        this.intent = intent;
        this.status = status;
        this.largeThreshold = largeThreshold;
        this.activity = activity;

        ws = new WebSocketFactory().createSocket(YDWInfo.DISCORD_GATEWAY_LINK);
        ws.addHeader("Accept-Encoding", "gzip");
        ws.addListener(this);
        ws.connect();

        // TODO: Find out why it does not connect.

        return this;
    }

    public static void setSessionId(String sessionId) {
        WebSocketManager.sessionId = sessionId;
    }

    @Override
    public void onTextMessage(WebSocket websocket, String message) throws Exception {
        onHandelMessage(message);
    }

    public void onHandelMessage(String message) throws Exception {
        logger.debug("Received message: {}", message);

        try {
            JsonNode payload = mapper.readTree(message);
            onEvent(payload);
        } catch (Exception e) {
            logger.error("Error while handling message", e);
        }
    }

    public void onEvent(@NotNull JsonNode payload) {
        Optional<Integer> s = Optional.of(payload.get("s").asInt());
        s.ifPresent(integer -> this.seq = integer);

        Optional<JsonNode> d = Optional.of(payload.get("d"));
        Optional<Integer> op = Optional.of(payload.get("op").asInt());
        op.ifPresent(integer -> onOpcode(op.get(), d.get()));

        Optional<String> t = Optional.of(payload.get("t").asText());
        logger.debug("Received event: {}", t.orElse(""));
        t.ifPresent(text -> new OnHandler(ydw, text, payload).start());
    }

    public void onOpcode(Integer opcode, @NotNull JsonNode d) {
        OpCode op = OpCode.fromCode(opcode);
        switch (op) {
            case HEARTBEAT -> sendHeartbeat();
            case HELLO -> {
                int heartbeatInterval = d.get("heartbeat_interval").asInt();
                // Send heartbeat
                sendHeartbeat(heartbeatInterval);
            }
            case HEARTBEAT_ACK -> {
                logger.debug("Heartbeat acknowledged");
                missedHeartbeats = 0;
            }
            case INVALID_SESSION -> {
                boolean shouldResume = d.asBoolean();
                if (shouldResume)
                    logger.debug("Session invalidated, resuming session");

                int closeCode = shouldResume ? 4900 : 1000;

                prepareClose();
                ws.sendClose(closeCode, "Session invalidated");
            }
            default -> logger.debug("Unhandled opcode: {}", op);
        }
    }

    /**
     * <p>
     * The gateway may request a heartbeat from the client in some situations by sending an Opcode 1
     * Heartbeat. When this occurs, the client should immediately send an Opcode 1 Heartbeat without
     * waiting the remainder of the current interval.
     * </p>
     * <p>
     * If a client does not receive a heartbeat ack between its attempts at sending heartbeats, this
     * may be due to a failed or "zombied" connection. The client should then immediately terminate
     * the connection with a non-1000 close code, reconnect, and attempt to Resume.
     * </p>
     */
    public void sendHeartbeat() {

        Integer sequence = seq == null ? null : seq;

        JsonNode heartbeat = JsonNodeFactory.instance.objectNode().put("op", 1).put("d", sequence);


        if (missedHeartbeats >= 2) {
            missedHeartbeats = 0;
            prepareClose();
            ws.disconnect(1000, "Heartbeat missed");
        } else {
            missedHeartbeats += 1;
            ws.sendText(heartbeat.asText());
        }
    }

    /**
     * After receiving Opcode 10 Hello, the client may begin sending Opcode 1 Heartbeat payloads
     * after heartbeat_interval * jitter milliseconds (where jitter is a random value between 0 and
     * 1), and every heartbeat_interval milliseconds thereafter. You may send heartbeats before this
     * interval elapses, but you should avoid doing so unless necessary. here is already tolerance
     * in the heartbeat_interval that will cover network latency, so you do not need to account for
     * it in your own implementation - waiting the precise interval will suffice.
     *
     * @param heartbeatInterval the interval in milliseconds between heartbeats
     */
    public void sendHeartbeat(int heartbeatInterval) {

        try {
            Socket rawSocket = this.ws.getSocket();
            if (rawSocket != null)
                rawSocket.setSoTimeout(heartbeatInterval + 10000); // setup a timeout when we miss
                                                                   // heartbeats
        } catch (SocketException ex) {
            logger.warn("Failed to setup timeout for socket", ex);
        }

        heartbeatThread = scheduler.scheduleAtFixedRate(() -> {
            try {
                if (connected)
                    sendHeartbeat();
            } catch (Exception e) {
                logger.error("Error sending heartbeat", e);
            }
        }, 0, heartbeatInterval, TimeUnit.MILLISECONDS);
    }

    /**
     * This was taken from JDA's WebSocket. <a href=
     * "https://github.com/DV8FromTheWorld/JDA/blob/023a6c9de489d2e487d44b294614bb4911597817/src/main/java/net/dv8tion/jda/internal/requests/WebSocketClient.java#L671">WebSocketClient.java</a>
     */
    private void prepareClose() {
        try {
            if (ws != null) {
                Socket rawSocket = ws.getSocket();
                if (rawSocket != null) // attempt to set a 10 second timeout for the close frame
                    rawSocket.setSoTimeout(10000); // this has no affect if the socket is already
                                                   // stuck in a read call
            }
        } catch (SocketException ignored) {
        }
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
        ydw.setApiStatus(YDW.ApiStatus.LOGGED_IN_AND_IDENTIFYING);
        prepareClose();
        connected = true;
        if (sessionId == null)
            identify();
        else
            resume();

    }

    public void identify() {
        ObjectNode properties = JsonNodeFactory.instance.objectNode()
            .put("os", System.getProperty("os.name"))
            .put("browser", "YDW")
            .put("device", "YDW");

        ObjectNode payload = JsonNodeFactory.instance.objectNode()
            .put("status", status)
            .put("token", token)
            .set("properties", properties);

        payload.put("large_threshold", largeThreshold).put("intents", intent);

        JsonNode identify = JsonNodeFactory.instance.objectNode()
            .put("op", OpCode.IDENTIFY.getCode())
            .set("d", payload);

        ws.sendText(identify.asText());
        logger.info("Connected");
    }

    public void resume() {
        JsonNode payload = JsonNodeFactory.instance.objectNode()
            .put("token", token)
            .put("session_id", sessionId)
            .put("seq", seq);

        JsonNode identify = JsonNodeFactory.instance.objectNode()
            .put("op", OpCode.RESUME.getCode())
            .set("d", payload);

        ws.sendText(identify.asText());
        logger.info("Reconnected");
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame,
            WebSocketFrame clientCloseFrame, boolean closedByServer) {
        connected = false;
        if (Thread.currentThread().isInterrupted()) {
            Thread thread = new Thread(() -> handleDisconnect(websocket, serverCloseFrame,
                    clientCloseFrame, closedByServer));
            thread.setName("reconnect-thread");
            thread.start();
        } else {
            handleDisconnect(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
        }
    }

    private void handleDisconnect(WebSocket websocket, WebSocketFrame serverCloseFrame,
            WebSocketFrame clientCloseFrame, boolean closedByServer) {
        ydw.setApiStatus(YDW.ApiStatus.WEBSOCKET_DISCONNECTED);
        CloseCode closeCode = null;
        String closeReason = null;
        int rawCloseCode = 1005;

        //Done to make sure no more heartbeats are sent
        if (heartbeatThread != null) {
            heartbeatThread.cancel(false);
            heartbeatThread = null;
        }

        if (closedByServer && serverCloseFrame != null) {

        }
    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {

    }

    public int getGatewayIntents() {
        return intent;
    }
}
