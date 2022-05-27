package io.github.realyusufismail.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.neovisionaries.ws.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebSocketManager extends WebSocketAdapter implements WebSocketListener {

    private final Logger logger = LoggerFactory.getLogger(WebSocketManager.class);

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
    private final GateWayIntent intent;

    //The session id. This is basically a key that stores the past activity of the bot.
    private volatile String sessionId = null;



    public WebSocketManager(String token, GateWayIntent intent) throws IOException, WebSocketException {
        this.token = token;
        this.intent = intent;
        String gatewayUrl = "wss://gateway.discord.gg/?v=9&encoding=etf";
        ws = new WebSocketFactory().createSocket(gatewayUrl);
        ws.addHeader("Accept-Encoding", "gzip");
        ws.setDirectTextMessage(true);
        ws.addListener(this);
        ws.connect();
    }

    @Override
    public void onTextMessage(WebSocket websocket, byte[] data) throws Exception {
        JsonNode payload = mapper.readTree(data);

        //opcode
        int opcode = payload.get("op").asInt();
        //event data
        JsonNode d  = payload.get("d");

        onOpcode(opcode, d, payload);
        onEvent(payload);
    }

    public void onEvent(JsonNode payload) {
        if(payload.hasNonNull("t")) {
            String t = payload.get("t").asText();
            if(t.equals("READY")) {
                sessionId = payload.get("d").get("session_id").asText();

            }
        }
    }

    public void onOpcode(int opcode, JsonNode d, JsonNode payload) {

        if(payload.hasNonNull("s")) {
            //sequence number, used for resuming sessions and heartbeats
            seq = payload.get("s").asInt();
        }

        OpCode op = OpCode.fromCode(opcode);
        switch (op) {
            case HEARTBEAT -> sendHeartbeat();
            case HELLO -> {
                int heartbeatInterval = d.get("heartbeat_interval").asInt();
                //Send heartbeat
                sendHeartbeat(heartbeatInterval);
            }
            case HEARTBEAT_ACK -> {
                logger.debug("Heartbeat acknowledged");
                missedHeartbeats = 0;
            }
            case INVALIDATE_SESSION -> {
            }
            default -> logger.debug("Unhandled opcode: {}", op);
        }
    }

    /**
     * <p>
     * The gateway may request a heartbeat from the client in some situations by sending an Opcode 1 Heartbeat.
     * When this occurs,
     * the client should immediately send an Opcode 1 Heartbeat without waiting the remainder of the current interval.
     *  </p>
     * <p>
     *     If a client does not receive a heartbeat ack between its attempts at sending heartbeats,
     *     this may be due to a failed or "zombied" connection. The client should then immediately terminate
     *     the connection with a non-1000 close code, reconnect, and attempt to Resume.
     *     </p>
     */
    public void sendHeartbeat() {
        JsonNode json = JsonNodeFactory.instance.objectNode()
                .put("op", 1)
                .put("d", seq);

        if(missedHeartbeats >= 2) {
            missedHeartbeats = 0;
            prepareClose();
            ws.disconnect(1000, "Heartbeat missed");
        } else {
            missedHeartbeats += 1;
            ws.sendText(json.toString());
        }
    }

    /**
     * After receiving Opcode 10 Hello, the client may begin sending Opcode 1 Heartbeat payloads after heartbeat_interval * jitter milliseconds
     * (where jitter is a random value between 0 and 1), and every heartbeat_interval milliseconds thereafter.
     * You may send heartbeats before this interval elapses, but you should avoid doing so unless necessary.
     * here is already tolerance in the heartbeat_interval that will cover network latency,
     * so you do not need to account for it in your own implementation - waiting the precise interval will suffice.
     * @param heartbeatInterval the interval in milliseconds between heartbeats
     */
    public void sendHeartbeat(int heartbeatInterval) {
        JsonNode json = JsonNodeFactory.instance.objectNode()
                .put("op", 1)
                .put("d", seq);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                ws.sendText(json.toString());
            } catch (Exception e) {
                logger.error("Error sending heartbeat", e);
            }
        }, 0, heartbeatInterval, TimeUnit.MILLISECONDS);
    }


    /**
     * This was taken from JDA's WebSocket. <a href="https://github.com/DV8FromTheWorld/JDA/blob/023a6c9de489d2e487d44b294614bb4911597817/src/main/java/net/dv8tion/jda/internal/requests/WebSocketClient.java#L671">WebSocketClient.java</a>
     */
    private void prepareClose() {
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
        if(sessionId == null) {
            identify();
        } else {
            resume();
        }
    }

    public void identify() {
        JsonNode json = JsonNodeFactory.instance.objectNode()
                .put("op", 2)
                .set("d", JsonNodeFactory.instance.objectNode()
                        .put("token", token)
                        .put("intents", intent.getValue())
                        .set("properties", JsonNodeFactory.instance.objectNode()
                                .put("$os", "mac")
                                .put("$browser", "YDL")
                                .put("$device", "YDL")));

        ws.sendText(json.toString());
        logger.info("Connected");
    }

    public void resume() {
        JsonNode json = JsonNodeFactory.instance.objectNode()
                .put("op", 6)
                .set("d", JsonNodeFactory.instance.objectNode()
                        .put("token", token)
                        .put("session_id", sessionId)
                        .put("seq", seq));


        ws.sendText(json.toString());
        logger.info("Reconnected");
    }
}
