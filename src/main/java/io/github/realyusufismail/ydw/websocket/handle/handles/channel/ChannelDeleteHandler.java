package io.github.realyusufismail.ydw.websocket.handle.handles.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.websocket.handle.Handle;
import io.github.realyusufismail.ydw.ydl.YDL;

public class ChannelDeleteHandler extends Handle {
    public ChannelDeleteHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}