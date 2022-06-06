package io.github.realyusufismail.websocket.handle.handles.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import io.github.realyusufismail.ydw.YDW;

public class InteractionHandler extends Handle {
    protected InteractionHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {

    }
}
