package io.github.realyusufismail.ydw.event.events;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;

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
