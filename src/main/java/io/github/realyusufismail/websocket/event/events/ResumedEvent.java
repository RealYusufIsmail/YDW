package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.websocket.event.BasicEvent;
import io.github.realyusufismail.websocket.event.EventExtender;
import io.github.realyusufismail.ydw.YDW;

public class ResumedEvent extends EventExtender {
    private final boolean resumed;

    public ResumedEvent(YDW ydw) {
        super(ydw);

        this.resumed = ydw.isResumed();
    }

    public Boolean hasResumed() {
        return resumed;
    }
}
