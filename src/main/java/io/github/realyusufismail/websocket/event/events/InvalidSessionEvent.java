package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.websocket.event.BasicEvent;
import io.github.realyusufismail.websocket.event.EventExtender;
import io.github.realyusufismail.ydw.YDW;

public class InvalidSessionEvent extends EventExtender {
    public InvalidSessionEvent(YDW ydw) {
        super(ydw);
    }

}
