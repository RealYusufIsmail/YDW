package io.github.realyusufismail.websocket.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import io.github.realyusufismail.ydw.YDW;

public class ResumedHandler extends Handle {
    public ResumedHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        ydw.setResumed(true);
    }
}
