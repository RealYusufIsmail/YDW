package io.github.realyusufismail.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.neovisionaries.ws.client.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebSocketManager2 extends WebSocketAdapter implements WebSocketListener {

    public static final Logger logger = LoggerFactory.getLogger(WebSocketManager.class);

    // Create a WebSocketFactory instance.
    WebSocket ws;

    ObjectMapper mapper = new ObjectMapper();

    //The sequence number, used for resuming sessions and heartbeats.
    private Integer seq = null;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    //Used to determine if any heart beats hve been missed.
    private int missedHeartbeats = 0;

    //The bots token.
    private final String token;

    //The gateway intents
    private final int intent;

    //The session id. This is basically a key that stores the past activity of the bot.
    private volatile String sessionId = null;

    private final HeartHandler heartHandler;

    public WebSocketManager2(String token, int intent) throws IOException, WebSocketException {
        this.token = token;
        this.intent = intent;
        heartHandler = new HeartHandler(null, false, ws, this::close, scheduler);
        try {
            WebSocketFactory socketFactory = new WebSocketFactory();
            if (socketFactory.getSocketTimeout() > 0)
                socketFactory.setSocketTimeout(Math.max(1000, socketFactory.getSocketTimeout()));
            else
                socketFactory.setSocketTimeout(10000);
            ws = socketFactory.createSocket("wss://gateway.discord.gg/?v=9&encoding=etf");
            ws.setDirectTextMessage(true);
            ws.addHeader("Accept-Encoding", "gzip").addListener(this).connect();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private void close(int code, String reason) {
        prepareClose();
        if (ws != null)
            ws.sendClose(code, reason);
    }

    @Override
    public void onTextMessage(WebSocket websocket, byte[] message) throws Exception {
        JsonNode payload = mapper.readTree(message);
        onHandle(payload);
    }

    public void onHandle(JsonNode payload) {
        onOpcode(payload.get("op").asInt(), payload);
    }

    public void onEvent(JsonNode payload) {
        if(payload.hasNonNull("t")) {
            String t = payload.get("t").asText();
            if(t.equals("READY")) {
                sessionId = payload.get("d").get("session_id").asText();
            }
        }
    }

    public void onOpcode(int op, JsonNode payload) {

        if(payload.hasNonNull("s")) {
            //sequence number, used for resuming sessions and heartbeats
            seq = payload.get("s").asInt();
        }

        Optional<OpCode> opCode = OpCode.fromOptionalCode(op);

        if (!opCode.isPresent()) {
            logger.error(
                    "It seems like the gateway is sending us a message with an unknown opcode. The opcode is '{}' and the payload is '{}'",
                    op, payload);
            return;
        }

        switch (opCode.get()) {
            case DISPATCH -> {
                onEvent(payload);
            }
            case HEARTBEAT -> heartHandler.beat();
            case HELLO -> hello(payload.get("d"));
            case HEARTBEAT_ACK -> {
                logger.debug("Heartbeat acknowledged");
                missedHeartbeats = 0;
            }
            case INVALIDATE_SESSION -> {
                boolean shouldResume = payload.get("d").asBoolean();
                if(shouldResume)
                    logger.debug("Session invalidated, resuming session");

                int closeCode = shouldResume ? 4900 : 1000;

                prepareClose();
                ws.sendClose(closeCode, "Session invalidated");
            }
            case RECONNECT -> opReconnect();
            default -> logger.debug("Unhandled opcode: {}", op);
        }
    }

    private void hello(@NotNull JsonNode d) {
        logger.debug("Received OpCode: {}", OpCode.HELLO);
        int heartBeatInterval = d.get("d").get("heartbeat_interval").asInt();
        heartHandler.startBeating(heartBeatInterval);
    }

    private void opReconnect() {
        close(4900, "Reconnecting...");
    }

    /**
     * This was taken from JDA's WebSocket. <a href="https://github.com/DV8FromTheWorld/JDA/blob/023a6c9de489d2e487d44b294614bb4911597817/src/main/java/net/dv8tion/jda/internal/requests/WebSocketClient.java#L671">WebSocketClient.java</a>
     */
    void prepareClose() {
        try {
            if (ws != null) {
                Socket rawSocket = ws.getSocket();
                if (rawSocket != null) // attempt to set a 10 second timeout for the close frame
                    rawSocket.setSoTimeout(10000); // this has no affect if the socket is already stuck in a read call
            }
        }
        catch (SocketException ignored) {}
    }


    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        prepareClose();
        if(sessionId == null)
            identify();
        else {
            resume();
        }
    }

    public void identify() {
        JsonNode json = JsonNodeFactory.instance.objectNode()
                .put("op", OpCode.IDENTIFY.getCode())
                .set("d", JsonNodeFactory.instance.objectNode()
                        .put("token", token)
                        .put("intents", intent)
                        .set("properties", JsonNodeFactory.instance.objectNode()
                                .put("$os", "macOS")
                                .put("$browser", "YDL")
                                .put("$device", "YDL")));

        ws.sendText(json.toString());
        logger.info("Connected to Discord Gateway");
    }

    public void resume() {
        JsonNode json = JsonNodeFactory.instance.objectNode()
                .put("op", OpCode.RESUME.getCode())
                .set("d", JsonNodeFactory.instance.objectNode()
                        .put("token", token)
                        .put("session_id", sessionId)
                        .put("seq", seq));

        ws.sendText(json.toString());
        logger.info("Reconnected");
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
        logger.info("Disconnected from Discord Gateway");

        CloseCode code = null;
        if(serverCloseFrame != null)
            code = CloseCode.fromCode(serverCloseFrame.getCloseCode());
        if(clientCloseFrame != null)
            code = CloseCode.fromCode(clientCloseFrame.getCloseCode());

        logger.error("Disconnected from Discord Gateway: " + code);
    }
}
