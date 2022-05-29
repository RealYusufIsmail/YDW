package io.github.realyusufismail.websocket.handle.handles.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import yusufsdiscordbot.ydl.YDL;

public class ThreadUpdateHandler extends Handle {
    public ThreadUpdateHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}
