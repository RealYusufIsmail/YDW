package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.websocket.event.BasicEvent;
import io.github.realyusufismail.websocket.event.EventExtender;
import io.github.realyusufismail.ydw.YDW;

public class ReconnectEvent extends EventExtender {
    private final boolean reconnected;

    public ReconnectEvent(YDW ydw) {
        super(ydw);

        this.reconnected = ydw.hasReconnected();
    }

    public Boolean hasReconnected() {
        return reconnected;
    }
}
