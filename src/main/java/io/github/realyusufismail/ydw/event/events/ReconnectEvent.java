package io.github.realyusufismail.ydw.event.events;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;

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
