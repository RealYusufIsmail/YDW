package io.github.realyusufismail.websocket.event;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class Event {
    protected final JsonNode json;

    public Event(JsonNode json) {
        this.json = json;
    }

    public abstract void start();
}
