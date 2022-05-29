package io.github.realyusufismail.websocket.event.event;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.websocket.event.Event;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.UnavailableGuild;

import java.util.List;
import java.util.Set;

public class ReadyEvent extends Event {

    public ReadyEvent(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {
        String sessionId = json.get("session_id").asText();
        WebSocketManager.setSessionId(sessionId);
        List<UnavailableGuild> unavailableGuilds;
    }


}
