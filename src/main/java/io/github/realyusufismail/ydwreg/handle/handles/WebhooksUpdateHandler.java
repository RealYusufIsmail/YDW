package io.github.realyusufismail.ydwreg.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class WebhooksUpdateHandler extends Handle {
    public WebhooksUpdateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {

    }
}
