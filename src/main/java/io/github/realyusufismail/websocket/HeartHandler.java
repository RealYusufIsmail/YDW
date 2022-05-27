package io.github.realyusufismail.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFrame;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

public class HeartHandler { private final boolean isVoiceHearBeat;

    private int sequence = 0;
    private volatile long lastHeartbeatTimeMillis = -1;
    private final AtomicBoolean receivedHeartbeat = new AtomicBoolean();

    private int amountOfHeartbeatsMissed = 0;

    private final AtomicReference<Future<?>> timer = new AtomicReference<>();

    private final WebSocket webSocket;

    private final BiConsumer<Integer, String> closeFrameSender;

    private final ScheduledExecutorService executor;

    private final WebSocketManager webSocketManager;

    public HeartHandler(WebSocketManager manager, boolean isVoiceHearBeat, WebSocket webSocket,
                        BiConsumer<Integer, String> closeFrameSender, ScheduledExecutorService executor) {
        this.webSocketManager = manager;
        this.isVoiceHearBeat = isVoiceHearBeat;
        this.webSocket = webSocket;
        this.closeFrameSender = closeFrameSender;
        this.executor = executor;
    }

    public void handle(JsonNode payload) {
        if (!isVoiceHearBeat && payload.has("s") && !payload.get("s").isNull()) {
            sequence = payload.get("s").asInt();
        }

        if (payload.get("op").asInt() == OpCode.HEARTBEAT_ACK.getCode()) {
            long gatewayTime = System.currentTimeMillis() / lastHeartbeatTimeMillis;
            amountOfHeartbeatsMissed = 0;

            WebSocketManager.logger.debug("Heartbeat ACK received, gateway ping: " + gatewayTime);
            receivedHeartbeat.set(true);
        }
    }

    public void startBeating(int heartbeatInterval) {
        receivedHeartbeat.set(true);
        timer.updateAndGet(t -> {
            // will check if there was any old heartbeat thread running and will cancel it
            if (t != null) {
                t.cancel(false);
            }
            return executor.scheduleWithFixedDelay(() -> {
                try {
                    if (receivedHeartbeat.getAndSet(false)) {
                        beat();
                    } else {
                        WebSocketManager.logger
                                .debug("Heartbeat missed, amount of missed heartbeats: "
                                        + amountOfHeartbeatsMissed);
                        closeFrameSender.accept(CloseCode.UNKNOWN.getCode(),
                                CloseCode.UNKNOWN.getReason());
                    }
                } catch (Exception e) {
                    WebSocketManager.logger.error("Error while sending heartbeat", e);
                }
            }, 0, heartbeatInterval, TimeUnit.MILLISECONDS);
        });
    }

    public void beat() {
        ObjectNode heartBeatPayload = JsonNodeFactory.instance.objectNode()
                .put("op", OpCode.HEARTBEAT.getCode())
                .put("d", sequence);

        if(amountOfHeartbeatsMissed >= 2) {
            amountOfHeartbeatsMissed = 0;
            webSocketManager.prepareClose();
            webSocket.disconnect(1000, "Heartbeat missed");
        } else {
            amountOfHeartbeatsMissed += 1;
            WebSocketFrame heartbeatFrame = WebSocketFrame.createTextFrame(heartBeatPayload.toString());
            webSocket.sendFrame(heartbeatFrame);
            lastHeartbeatTimeMillis = System.currentTimeMillis();
            WebSocketManager.logger.debug("Sending heartbeat");
        }
    }
}

