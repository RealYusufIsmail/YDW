package io.github.realyusufismail.ydwreg.handle.handles.integration;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class IntegrationDeleteHandler extends Handle {
    public IntegrationDeleteHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {

    }
}