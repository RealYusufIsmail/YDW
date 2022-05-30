package io.github.realyusufismail.ydw.websocket.event.events;

import io.github.realyusufismail.ydw.websocket.event.Event;
import io.github.realyusufismail.ydw.ydl.YDL;

public class ReadyEvent extends Event {
    public ReadyEvent(YDL ydl) {
        super(ydl);
    }
}
