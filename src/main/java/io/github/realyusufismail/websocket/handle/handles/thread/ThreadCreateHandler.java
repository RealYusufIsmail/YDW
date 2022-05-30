package io.github.realyusufismail.websocket.handle.handles.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;

public class ThreadCreateHandler extends Handle {
    public ThreadCreateHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}
