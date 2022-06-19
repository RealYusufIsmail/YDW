package io.github.realyusufismail.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
import io.github.realyusufismail.ydwreg.rest.RestApiHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.ClosedChannelException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebSocketManager extends WebSocketAdapter implements WebSocketListener {

    private final Logger logger = LoggerFactory.getLogger(WebSocketManager.class);

    // Create a WebSocketFactory instance.
    static WebSocket ws;

    ObjectMapper mapper = new ObjectMapper();

    // The sequence number, used for resuming sessions and heartbeats.
    private Integer seq = null;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Used to determine if any heart beats hve been missed.
    private int missedHeartbeats = 0;

    // The bots token.
    private final String token;

    // The gateway intents
    private final int intent;

    // The sequence number, used for resuming sessions and heartbeats.
    // The status of the bot e.g. online, idle, dnd, invisible etc.
    private String status;
    private Integer largeThreshold;
    // The activity of the bot e.g. playing, streaming, listening, watching etc.
    private ActivityConfig activity;

    // The session id. This is basically a key that stores the past activity of the bot.
    private volatile String sessionId = null;
    // Used to indicate that the bot has connected to the gateway.
    private boolean connected = false;

    // The thread used for the heartbeat. Needed in cases such as disconnect.
    private volatile Future<?> heartbeatThread;

    private final YDWReg ydwReg;

    private boolean isSessionAvailable = false;



    public WebSocketManager(YDW ydw, String token, Integer intent, String status,
            int largeThreshold, ActivityConfig activity) throws IOException, WebSocketException {
        this.ydwReg = (YDWReg) ydw;
        this.token = token;
        this.intent = intent;
        this.status = status;
        this.largeThreshold = largeThreshold;
        this.activity = activity;
        // Create a WebSocketFactory instance.
        ws = new WebSocketFactory().createSocket(YDWInfo.DISCORD_GATEWAY_LINK);
        ws.addHeader("Accept-Encoding", "gzip");
        ws.addListener(this);
        ws.connect();
    }

    public WebSocketManager(YDW ydw, String token, @NotNull GateWayIntent intent, String status,
            int largeThreshold, ActivityConfig activity) throws IOException, WebSocketException {
        this(ydw, token, intent.getValue(), status, largeThreshold, activity);
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    @Override
    public void onTextMessage(WebSocket websocket, String message) throws Exception {
        onHandelMessage(message);
    }

    public void onHandelMessage(String message) throws Exception {
        try {
            JsonNode payload = mapper.readTree(message);
            // TODO: remember to remove this when done testing
            System.out.println(payload.toPrettyString());
            onHandel(payload);
        } catch (Exception e) {
            logger.error("Error while handling message", e);
        }
    }

    public void onHandel(JsonNode payload) throws Exception {
        onEvent(payload);
    }

    public void onEvent(@NotNull JsonNode payload) {
        Optional<Integer> s = Optional.of(payload.get("s").asInt());
        s.ifPresent(integer -> this.seq = integer);


        Optional<JsonNode> d = Optional.of(payload.get("d"));
        Optional<Integer> op = Optional.of(payload.get("op").asInt());
        op.ifPresent(integer -> onOpcode(op.get(), d.get()));

        Optional<String> t = Optional.of(payload.get("t").asText());
        logger.debug("Received event: {}", t.orElse(""));
        t.ifPresent(text -> new OnHandler(ydwReg, text, payload).start());
    }

    public void onOpcode(Integer opcode, JsonNode d) {
        OpCode op = OpCode.fromCode(opcode);
        switch (op) {
            case HEARTBEAT -> sendHeartbeat();
            case HELLO -> {
                logger.debug("Received HELLO");
                int heartbeatInterval = d.get("heartbeat_interval").asInt();
                logger.info("Received heartbeat interval of {}ms", heartbeatInterval);
                // Send heartbeat
                sendHeartbeat(heartbeatInterval);
            }
            case HEARTBEAT_ACK -> {
                logger.debug("Received HEARTBEAT_ACK");
                logger.debug("Heartbeat acknowledged");
                missedHeartbeats = 0;
            }
            case INVALID_SESSION -> {
                logger.debug("Received INVALID_SESSION");
                boolean shouldResume = d.asBoolean();
                if (shouldResume)
                    logger.debug("Session invalidated, resuming session");

                int closeCode = shouldResume ? 4900 : 1000;
                ws.sendClose(closeCode, "Session invalidated");
            }
            case RECONNECT -> {
                logger.debug("Received RECONNECT");
                ws.sendClose(4999, "OpCode 7 received hence requesting a reconnect");
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

        Integer d;

        if (seq != null) {
            d = seq;
        } else {
            d = null;
        }


        JsonNode heartbeat = JsonNodeFactory.instance.objectNode()
            .put("op", OpCode.HEARTBEAT.getCode())
            .put("d", d);


        ws.sendText(heartbeat.toString());
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
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
            throws Exception {
        connected = true;
        ydwReg.setApiStatus(YDW.ApiStatus.CONNECTING_TO_WEBSOCKET);
        if (sessionId == null) {
            identify();
        } else {
            resume();
        }
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
        ydwReg.setApiStatus(YDW.ApiStatus.WEBSOCKET_DISCONNECTED);
        CloseCode closeCode = null;
        int rawCloseCode = 1005;
        isSessionAvailable = false;
        // Done to make sure no more heartbeats are sent
        if (heartbeatThread != null) {
            heartbeatThread.cancel(false);
            heartbeatThread = null;
        }

        if (closedByServer && serverCloseFrame != null) {
            rawCloseCode = serverCloseFrame.getCloseCode();
            closeCode = CloseCode.fromCode(rawCloseCode);

            if (closeCode == CloseCode.RATE_LIMITED) {
                logger.error("'{}'", closeCode.getReason());
            } else {
                logger.error("'{}'", closeCode.getReason());
            }
        } else if (clientCloseFrame != null) {
            rawCloseCode = clientCloseFrame.getCloseCode();
            closeCode = CloseCode.fromCode(rawCloseCode);
            if (rawCloseCode == 1000) {
                isSessionAvailable = true;
            }
        }

        boolean closeCodeIsReconnect = closeCode == null || closeCode.isReconnect();
        if (!closeCodeIsReconnect) {
            logger.error("The WebSocket was closed by a non-reconnectable close code.");
        } else {

            if (isSessionAvailable)
                session();
            try {
                onReconnect(rawCloseCode);
            } catch (Exception e) {
                logger.error("Error reconnecting", e);
            }
        }
    }

    private void onReconnect(int closeCode) {
        if (sessionId == null) {
            logger.warn("Session ID is null, reconnecting...");
        } else {
            resume();
        }
    }

    private void session() {
        sessionId = null;
    }

    @Override
    public void onError(WebSocket websocket, @NotNull WebSocketException cause) throws Exception {
        if (cause.getCause() instanceof SocketTimeoutException) {
            logger.error("Socket timeout");
        } else if (cause.getCause() instanceof IOException) {
            logger.error("IO error");
        } else if (cause.getCause() instanceof ClosedChannelException) {
            logger.error("Closed channel");
        } else {
            logger.error("Unknown error", cause);
        }
    }

    public int getGatewayIntents() {
        return intent;
    }
}
