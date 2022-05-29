package io.github.realyusufismail.websocket.event.events;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.event.Event;
import yusufsdiscordbot.ydl.YDL;

public class ApplicationCommandPermissionsUpdateEvent extends Event {
    public ApplicationCommandPermissionsUpdateEvent(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}
