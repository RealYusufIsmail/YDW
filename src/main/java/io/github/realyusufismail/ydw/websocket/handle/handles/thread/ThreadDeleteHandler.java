package io.github.realyusufismail.ydw.websocket.handle.handles.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.websocket.handle.Handle;
import io.github.realyusufismail.ydw.ydl.YDL;

public class ThreadDeleteHandler extends Handle {
    public ThreadDeleteHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}
