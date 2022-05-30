package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.websocket.event.Event;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;

public class ReadyEvent extends Event {
    public ReadyEvent(YDL ydl) {
        super(ydl);
    }
}
