package io.github.realyusufismail.websocket.event;

import yusufsdiscordbot.ydl.YDL;

public abstract class Event {
    private final YDL ydl;

    public Event(YDL ydl) {
        this.ydl = ydl;
    }

    public YDL getYDL() {
        return ydl;
    }
}
