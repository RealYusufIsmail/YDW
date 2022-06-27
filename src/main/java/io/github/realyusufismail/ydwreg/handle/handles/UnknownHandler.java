package io.github.realyusufismail.ydwreg.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class UnknownHandler extends Handle {
    public UnknownHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        ydw.getLogger().error("Unknown event: " + json.toString());
    }
}
