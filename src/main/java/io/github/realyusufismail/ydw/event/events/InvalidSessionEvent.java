package io.github.realyusufismail.ydw.event.events;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;

public class InvalidSessionEvent extends Event {
    private final boolean resumeable;

    public InvalidSessionEvent(YDW ydw, boolean resumeable) {
        super(ydw);
        this.resumeable = resumeable;
    }

    public Boolean isResumeable() {
        return resumeable;
    }
}
