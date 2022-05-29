package io.github.realyusufismail.websocket.handle;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.EventNames;
import io.github.realyusufismail.websocket.handle.handles.*;
import io.github.realyusufismail.websocket.handle.handles.channel.ChannelCreateHandler;
import io.github.realyusufismail.websocket.handle.handles.channel.ChannelDeleteHandler;
import io.github.realyusufismail.websocket.handle.handles.channel.ChannelPinsUpdateHandler;
import io.github.realyusufismail.websocket.handle.handles.channel.ChannelUpdateHandler;
import io.github.realyusufismail.websocket.handle.handles.thread.ThreadCreateHandler;
import io.github.realyusufismail.websocket.handle.handles.thread.ThreadDeleteHandler;
import io.github.realyusufismail.websocket.handle.handles.thread.ThreadUpdateHandler;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydlreg.YDLReg;

import java.util.Optional;

public class OnHandler {
    private final YDLReg ydl;
    private final JsonNode json;
    private final String event;

    public OnHandler(YDLReg ydl, String event, JsonNode json) {
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
            case READY -> new ReadyHandler(json, ydl).start();
            case RESUMED -> ydl.getLogger().info("Resumed");
            case RECONNECT -> ydl.getLogger().info("Reconnected");
            case INVALID_SESSION -> new InvalidSessionHandler(json, ydl).start();
            case APPLICATION_COMMAND_PERMISSIONS_UPDATE -> new ApplicationCommandPermissionsUpdateHandler(
                    json, ydl).start();
            case CHANNEL_CREATE -> new ChannelCreateHandler(json, ydl).start();
            case CHANNEL_UPDATE -> new ChannelUpdateHandler(json, ydl).start();
            case CHANNEL_DELETE -> new ChannelDeleteHandler(json, ydl).start();
            case CHANNEL_PINS_UPDATE -> new ChannelPinsUpdateHandler(json, ydl).start();
            case THREAD_CREATE -> new ThreadCreateHandler(json, ydl).start();
            case THREAD_UPDATE -> new ThreadUpdateHandler(json, ydl).start();
            case THREAD_DELETE -> new ThreadDeleteHandler(json, ydl).start();
        }
    }
}
