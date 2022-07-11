package io.github.realyusufismail.ydwreg.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.events.ResumedEvent;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class ResumedHandler extends Handle {
    public ResumedHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        ydw.getWebSocket().setReconnectTimeoutS(2);
        ydw.handelEvent(new ResumedEvent(ydw, true));
    }
}
