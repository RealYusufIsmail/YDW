package io.github.realyusufismail.ydw.websocket.event;

import io.github.realyusufismail.ydw.ydl.YDL;

public abstract class Event {
    private final YDL ydl;

    public Event(YDL ydl) {
        this.ydl = ydl;
    }

    public YDL getYDL() {
        return ydl;
    }
}
