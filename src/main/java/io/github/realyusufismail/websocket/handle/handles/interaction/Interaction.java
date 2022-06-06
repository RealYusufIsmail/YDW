package io.github.realyusufismail.websocket.handle.handles.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import io.github.realyusufismail.ydw.YDW;

public class Interaction extends Handle {
    protected Interaction(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {

    }
}
