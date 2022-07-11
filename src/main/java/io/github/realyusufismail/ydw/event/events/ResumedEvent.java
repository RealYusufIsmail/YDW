package io.github.realyusufismail.ydw.event.events;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;

public class ResumedEvent extends Event {
    private final boolean resumed;

    public ResumedEvent(YDW ydw, boolean resumed) {
        super(ydw);

        this.resumed = resumed;
    }

    public Boolean hasResumed() {
        return resumed;
    }
}
