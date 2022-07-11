package io.github.realyusufismail.ydwreg.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.events.HelloEvent;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class HelloHandler extends Handle {
    public HelloHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        ydw.handelEvent(new HelloEvent(ydw));
    }
}
