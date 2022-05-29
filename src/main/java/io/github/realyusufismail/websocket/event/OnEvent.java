package io.github.realyusufismail.websocket.event;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.event.event.ReadyEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class OnEvent {
    private final JsonNode json;
    private final String event;

    public OnEvent(String event, JsonNode json) {
        this.json = json;
        this.event = event;
    }

    public void start() {
        Optional<EventNames> eventName = Optional.of(EventNames.getEvent(event));
        eventName.ifPresent(name -> fire(name, json));
    }

    public void fire(@NotNull EventNames event, JsonNode json) {
        switch (event) {
            case READY -> new ReadyEvent(json).start();

        }
    }
}
