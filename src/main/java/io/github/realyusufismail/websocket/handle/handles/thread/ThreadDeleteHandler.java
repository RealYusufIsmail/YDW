package io.github.realyusufismail.websocket.handle.handles.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;

public class ThreadDeleteHandler extends Handle {
    public ThreadDeleteHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}
