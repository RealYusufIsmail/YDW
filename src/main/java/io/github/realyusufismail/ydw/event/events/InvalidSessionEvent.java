package io.github.realyusufismail.ydw.event.events;

import io.github.realyusufismail.ydw.event.EventExtender;
import io.github.realyusufismail.ydw.YDW;

public class InvalidSessionEvent extends EventExtender {
    public InvalidSessionEvent(YDW ydw) {
        super(ydw);
    }

}
