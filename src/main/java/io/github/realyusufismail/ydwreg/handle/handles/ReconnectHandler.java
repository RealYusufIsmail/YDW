package io.github.realyusufismail.ydwreg.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydwreg.handle.Handle;
import io.github.realyusufismail.ydw.YDW;

public class ReconnectHandler extends Handle {
    public ReconnectHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        ydw.setReconnected(true);
    }
}
