package io.github.realyusufismail.websocket.event;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.event.events.ApplicationCommandPermissionsUpdateEvent;
import io.github.realyusufismail.websocket.event.events.ChannelCreateEvent;
import io.github.realyusufismail.websocket.event.events.InvalidSessionEvent;
import io.github.realyusufismail.websocket.event.events.ReadyEvent;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydlreg.YDLReg;

import java.util.Optional;

public class OnEvent {
    private final YDLReg ydl;
    private final JsonNode json;
    private final String event;

    public OnEvent(YDLReg ydl, String event, JsonNode json) {
        this.ydl = ydl;
        this.json = json;
        this.event = event;
    }

    public void start() {
        Optional<EventNames> eventName = Optional.of(EventNames.getEvent(event));
        eventName.ifPresent(name -> fire(name, json));
    }

    public void fire(@NotNull EventNames event, JsonNode json) {
        switch (event) {
            case READY -> new ReadyEvent(json, ydl).start();
            case RESUMED -> ydl.getLogger().info("Resumed");
            case RECONNECT -> ydl.getLogger().info("Reconnected");
            case INVALID_SESSION -> new InvalidSessionEvent(json, ydl).start();
            case APPLICATION_COMMAND_PERMISSIONS_UPDATE -> new ApplicationCommandPermissionsUpdateEvent(
                    json, ydl).start();
            case CHANNEL_CREATE -> new ChannelCreateEvent(json, ydl).start();
        }
    }
}
