package io.github.realyusufismail.ydw.websocket.handle.handles.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.websocket.handle.Handle;
import io.github.realyusufismail.ydw.ydl.YDL;

public class ThreadCreateHandler extends Handle {
    public ThreadCreateHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}
