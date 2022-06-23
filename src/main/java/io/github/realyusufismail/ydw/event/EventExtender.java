package io.github.realyusufismail.ydw.event;

import io.github.realyusufismail.ydw.YDW;

public abstract class EventExtender implements BasicEvent {

    private final YDW ydw;

    public EventExtender(YDW ydw) {
        this.ydw = ydw;
    }

    @Override
    public YDW getYDW() {
        return null;
    }
}
