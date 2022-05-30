package io.github.realyusufismail.websocket.handle.handles.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;

public class ChannelDeleteHandler extends Handle {
    public ChannelDeleteHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}
