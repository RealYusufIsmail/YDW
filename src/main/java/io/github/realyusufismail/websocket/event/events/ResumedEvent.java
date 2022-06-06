package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.websocket.event.Event;
import io.github.realyusufismail.ydw.YDW;

public class ResumedEvent extends Event {
    private final boolean resumed;

    public ResumedEvent(YDW ydw) {
        super(ydw);

        this.resumed = ydw.isResumed();
    }

    public Boolean hasResumed() {
        return resumed;
    }
}
