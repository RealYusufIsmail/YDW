package io.github.realyusufismail.websocket.handle.handles.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import yusufsdiscordbot.ydl.YDL;

public class ChannelPinsUpdateHandler extends Handle {
    public ChannelPinsUpdateHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}
