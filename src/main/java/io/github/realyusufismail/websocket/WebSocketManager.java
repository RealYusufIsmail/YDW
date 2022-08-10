/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.neovisionaries.ws.client.*;
import io.github.realyusufismail.websocket.core.CloseCode;
import io.github.realyusufismail.websocket.core.EventNames;
import io.github.realyusufismail.websocket.core.OpCode;
import io.github.realyusufismail.ydw.GateWayIntent;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.YDWInfo;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydw.entities.AvailableGuild;
import io.github.realyusufismail.ydw.entities.SelfUser;
import io.github.realyusufismail.ydw.entities.UnavailableGuild;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;
import io.github.realyusufismail.ydw.event.events.ReconnectEvent;
import io.github.realyusufismail.ydw.event.events.ResumedEvent;
import io.github.realyusufismail.ydw.event.events.ShutdownEvent;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.AvailableGuildReg;
import io.github.realyusufismail.ydwreg.entities.SelfUserReg;
import io.github.realyusufismail.ydwreg.entities.UnavailableGuildReg;
import io.github.realyusufismail.ydwreg.handle.OnHandler;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * Inspired from JDA's <a href=
 * "https://github.com/DV8FromTheWorld/JDA/blob/master/src/main/java/net/dv8tion/jda/internal/requests/WebSocketClient.java">WebSocketClient</a>
 */
public class WebSocketManager extends WebSocketAdapter implements WebSocketListener {

    // Create a WebSocketFactory instance.
    static WebSocket ws;
    // YDW
    protected final YDWReg ydwReg;
    public static final Logger logger = LoggerFactory.getLogger(WebSocketManager.class);
    // the core pool
    private int corePoolSize;
    // the scheduled thread pool
    protected final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(corePoolSize);
    // The bots token.
    protected final String token;
    // The gateway intents
    protected final int intent;
    // The sequence number, used for resuming sessions and heartbeats.
    // The status of the bot e.g. online, idle, dnd, invisible etc.
    protected final String status;
    protected final Integer largeThreshold;
    // The activity of the bot e.g. playing, streaming, listening, watching etc.
    protected final ActivityConfig activity;
    // The sequence number, used for resuming sessions and heartbeats.
    protected Integer seq = null;
    // The session id. This is basically a key that stores the past activity of the bot.
    protected volatile String sessionId = null;
    // gateway url for resuming
    protected String resumeGateWayUrl = null;
    // Used to indicate that the bot has connected to the gateway.
    protected boolean connected = false;
    // The thread used for the heartbeat. Needed in cases such as disconnect.
    protected volatile Future<?> heartbeatThread;
    // code 1000 meaning resuming can not be done
    protected boolean canNotResume = false;
    // the time that the hearbeat started
    protected long heartbeatStartTime;
    // The amounts of time that the heartbeat has been missed.
    protected int heartbeatsMissed = 0;
    // weather we need to reconnect or not.
    protected boolean needsToReconnect;
    // weather the authentication info has been sent or not.
    protected boolean sentAuthInfo = false;
    protected static final String INVALID_SESSION = "INVALID_SESSION";
    // As discord states if the TCP connection is closed, or use a different close code, the bot
    // session will remain active and timeout after a few minutes. This can be useful for a
    // reconnect, which will resume the previous session.
    protected int reconnectTimeout = 2;
    // Conforms that everything is done in the ws.
    protected boolean loadingDoneHere = false;
    // Indicates that a reconnect has been queued.
    protected boolean reconnectQueued = false;
    // Used for reconnecting and starting(not ready) the bot.
    protected volatile SetUpSystem setUpSystem;

    protected WebSocketManager(YDW ydw) {
        this.ydwReg = (YDWReg) ydw;
        this.token = null;
        this.intent = 0;
        this.status = null;
        this.largeThreshold = null;
        this.activity = null;
        this.setUpSystem = null;
    }

    public WebSocketManager(YDW ydw, String token, Integer intent, String status,
            int largeThreshold, ActivityConfig activity) {
        this.ydwReg = (YDWReg) ydw;
        this.token = token;
        this.intent = intent;
        this.status = status;
        this.largeThreshold = largeThreshold;
        this.activity = activity;
        this.setUpSystem = new ConnectEvent();
        // TODO: add the setUpSystem to the queue.
        // Create a WebSocketFactory instance.
        connect();
    }


    public WebSocketManager(YDW ydw, String token, @NotNull GateWayIntent intent, String status,
            int largeThreshold, ActivityConfig activity) {
        this(ydw, token, intent.getValue(), status, largeThreshold, activity);
    }

    public synchronized void connect() {
        try {
            String url =
                    (resumeGateWayUrl != null ? resumeGateWayUrl : YDWInfo.DISCORD_GATEWAY_LINK)
                            + YDWInfo.REST_OF_DISCORD_GATEWAY_LINK;

            ws = new WebSocketFactory().createSocket(url);
            ws.addHeader("Accept-Encoding", "gzip");
            ws.addListener(this);
            ws.connect();
        } catch (IOException | WebSocketException e) {
            logger.error("Error while connecting to the gateway", e);
        }
    }

    @Override
    public void onTextMessage(WebSocket websocket, String message) throws Exception {
        onHandelMessage(message);
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
        // in a rare case, HELLO might not be sent so waiting 10 seconds to see if it is sent
        prepareClose();
        connected = true;
        if (sessionId == null) {
            logger.info("Connected to the gateway.");
            identify();
        } else {
            logger.info("Resumed session.");
            resume();
        }
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame,
            WebSocketFrame clientCloseFrame, boolean closedByServer) {
        sentAuthInfo = false;
        connected = false;
        if (Thread.currentThread().isInterrupted()) {
            Thread thread = new Thread(() -> new HandleDisconnect(this.ydwReg, websocket,
                    serverCloseFrame, clientCloseFrame, closedByServer).handle());
            thread.setName("reconnect-thread");
            thread.start();
        } else {
            new HandleDisconnect(this.ydwReg, websocket, serverCloseFrame, clientCloseFrame,
                    closedByServer).handle();
        }
    }

    @Override
    public void onError(WebSocket websocket, @NotNull WebSocketException cause) {
        if (cause.getCause() instanceof SocketTimeoutException) {
            logger.error("Socket timeout");
        } else if (cause.getCause() instanceof IOException) {
            logger.error("IO error {}", cause.getCause().getMessage());
        } else if (cause.getCause() instanceof ClosedChannelException) {
            logger.error("Closed channel error {}", cause.getCause().getMessage());
        } else {
            logger.error("Unknown error", cause);
        }
    }

    public void onHandelMessage(String message) {
        try {
            JsonNode payload = ydwReg.getMapper().readTree(message);
            onHandel(payload);
        } catch (Exception e) {
            logger.error("Error while handling message", e);
        }
    }

    public void onHandel(JsonNode payload) {
        onEvent(payload);
    }

    public void onEvent(@NotNull JsonNode payload) {
        Optional<Integer> s = Optional.of(payload.get("s").asInt());
        s.ifPresent(integer -> this.seq = integer);


        Optional<JsonNode> d = Optional.of(payload.get("d"));
        Optional<Integer> op = Optional.of(payload.get("op").asInt());
        op.ifPresent(integer -> onOpcode(op.get(), d.get()));

        Optional<String> t = Optional.of(payload.get("t").asText());

        t.ifPresent(text -> {
            EventNames event = EventNames.getEvent(text);
            switch (event) {
                case READY -> {
                    reconnectTimeout = 2;
                    loadingDoneHere = true;
                    List<UnavailableGuild> unavailableGuilds = new ArrayList<>();
                    ArrayNode guilds = (ArrayNode) payload.get("guilds");
                    for (JsonNode guild : guilds) {
                        if (guild.get("unavailable").asBoolean()) {
                            UnavailableGuild unavailableGuild = new UnavailableGuildReg(ydwReg,
                                    guild.get("id").asLong(), guild);
                            unavailableGuilds.add(unavailableGuild);
                        }
                    }

                    List<AvailableGuild> availableGuilds = new ArrayList<>();
                    for (JsonNode guild : guilds) {
                        if (!guild.get("unavailable").asBoolean()) {
                            AvailableGuild availableGuild =
                                    new AvailableGuildReg(ydwReg, guild.get("id").asLong(), guild);
                            availableGuilds.add(availableGuild);
                        }
                    }

                    ydwReg.setApplicationId(payload.get("application").get("id").asLong());


                    ydwReg.setSelfUser(new SelfUserReg(payload.get("user"),
                            payload.get("user").get("id").asLong(), ydwReg));

                    sessionId = payload.get("session_id").asText();

                    resumeGateWayUrl = payload.hasNonNull("resume_gateway_url")
                            ? payload.get("resume_gateway_url").asText()
                            : null;

                    ydwReg.setReady(true);
                    // ydw.getWebSocket().setReconnectTimeoutS(2);
                    ydwReg.handelEvent(new ReadyEvent(ydwReg, unavailableGuilds.size(),
                            availableGuilds.size()));
                }
                case RESUMED -> {
                    loadingDoneHere = true;
                    reconnectTimeout = 2;
                    sentAuthInfo = true;
                    ydwReg.handelEvent(new ResumedEvent(ydwReg, true));
                }
                case RECONNECT -> {
                    ydwReg.handelEvent(new ReconnectEvent(ydwReg, false));
                }
                default -> new OnHandler(ydwReg, text, payload).start();
            }
        });
    }

    public void onOpcode(Integer opcode, JsonNode d) {
        OpCode op = OpCode.fromCode(opcode);
        switch (op) {
            case HEARTBEAT -> sendHeartbeat();
            case HELLO -> {
                logger.debug("Received HELLO");
                int heartbeatInterval = d.get("heartbeat_interval").asInt();
                // Send heartbeat
                sendHeartbeat(heartbeatInterval);
            }
            case HEARTBEAT_ACK -> {
                logger.debug("Received HEARTBEAT_ACK");
                heartbeatsMissed = 0;
                ydwReg.setGatewayPing(System.currentTimeMillis() - heartbeatStartTime);
            }
            case INVALID_SESSION -> {
                logger.debug("Received INVALID_SESSION");
                boolean shouldResume = d.asBoolean();
                logger.debug("Should resume: {}", shouldResume);
                sentAuthInfo = false;

                int reconnectCode = shouldResume ? CloseCode.RECONNECT.getCode()
                        : CloseCode.ZOMBIE_CONNECTION.getCode();

                if (shouldResume)
                    logger.debug("Session invalidated, resuming session");
                else
                    invalidate();

                wsClose(reconnectCode, INVALID_SESSION);
            }
            case RECONNECT -> {
                logger.debug("Received RECONNECT");
                wsClose(CloseCode.RECONNECT.getCode(),
                        "OpCode 7 received hence requesting a reconnect");
            }
            default -> logger.debug("Unhandled opcode: {}", op);
        }
    }

    private void wsClose(int code, String reason) {
        prepareClose();
        if (ws != null) {
            ws.sendClose(code, reason);
        }
    }

    protected void wsClose() {
        prepareClose();
        if (ws != null) {
            ws.sendClose(1000);
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

        Integer d;

        if (seq != null) {
            d = seq;
        } else {
            d = null;
        }

        JsonNode heartbeat = JsonNodeFactory.instance.objectNode()
            .put("op", OpCode.HEARTBEAT.getCode())
            .put("d", d);

        if (heartbeatsMissed >= 2) {
            heartbeatsMissed = 0;
            logger.warn("Heartbeat missed, will attempt to reconnect");
            prepareClose();
            ws.disconnect(CloseCode.RECONNECT.getCode(), "ZOMBIE CONNECTION");
        } else {
            heartbeatsMissed += 1;
            ws.sendText(heartbeat.toString());
            heartbeatStartTime = System.currentTimeMillis();
        }
    }

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


        heartbeatThread = scheduler.scheduleAtFixedRate(() -> {
            try {
                if (connected)
                    sendHeartbeat();
                logger.info("Sending heartbeat");
            } catch (Exception e) {
                logger.error("Error sending heartbeat", e);
            }
        }, 0, heartbeatInterval, TimeUnit.MILLISECONDS);
    }



    public void identify() {

        ObjectNode d = JsonNodeFactory.instance.objectNode()
            .put("token", token)
            .put("intents", intent)
            .set("properties",
                    JsonNodeFactory.instance.objectNode()
                        .put("os", System.getProperty("os.name"))
                        .put("browser", "YDL")
                        .put("device", "YDL"));

        ObjectNode presence = JsonNodeFactory.instance.objectNode();

        if (activity != null) {
            ArrayNode activities = JsonNodeFactory.instance.arrayNode();
            activities.add(JsonNodeFactory.instance.objectNode()
                .put("name", activity.getName())
                .put("type", activity.getActivity()));
            presence.set("activities", activities);
        }

        if (status != null) {
            presence.put("status", status);
        }

        if (largeThreshold != null) {
            d.put("large_threshold", largeThreshold);
        }

        JsonNode json = JsonNodeFactory.instance.objectNode().put("op", 2).set("d", d);
        sentAuthInfo = true;
        ws.sendText(json.toString());
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


    protected void invalidate() {
        resumeGateWayUrl = null;
        sessionId = null;
        sentAuthInfo = false;
    }

    public WebSocketManager setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    protected WebSocketManager awaitConfirmation() throws InterruptedException {
        // TODO: implement this
        return this;
    }

    protected class ConnectEvent extends SetUpSystem {
        @Override
        boolean isReconnect() {
            return false;
        }

        @Override
        void handle(boolean lastInQueue) {
            connect();

            if (lastInQueue) {
                return;
            }

            try {
                awaitConfirmation();
            } catch (InterruptedException e) {
                logger.error("Error waiting for reconnect confirmation", e);
                wsClose();
            }
        }
    }
}
