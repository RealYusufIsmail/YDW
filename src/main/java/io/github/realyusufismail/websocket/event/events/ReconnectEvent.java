package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.websocket.event.Event;
import io.github.realyusufismail.ydw.YDW;

public class ReconnectEvent extends Event {
    private final boolean reconnected;

    public ReconnectEvent(YDW ydw) {
        super(ydw);

        this.reconnected = ydw.hasReconnected();
    }

    public Boolean hasReconnected() {
        return reconnected;
    }
}
