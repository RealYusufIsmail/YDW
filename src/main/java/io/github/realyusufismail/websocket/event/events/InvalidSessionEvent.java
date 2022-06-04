package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.websocket.event.Event;
import io.github.realyusufismail.ydw.YDW;

public class InvalidSessionEvent extends Event {
    public InvalidSessionEvent(YDW ydw) {
        super(ydw);
    }
}
