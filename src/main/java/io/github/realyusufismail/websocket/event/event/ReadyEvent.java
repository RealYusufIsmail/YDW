package io.github.realyusufismail.websocket.event.event;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.websocket.event.Event;

public class ReadyEvent extends Event {

    public ReadyEvent(JsonNode json) {
        super(json);
    }

    @Override
    public void start() {
        String sessionId = json.get("session_id").asText();
        WebSocketManager.setSessionId(sessionId);

    }


}
