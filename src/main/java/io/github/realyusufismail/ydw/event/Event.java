package io.github.realyusufismail.ydw.event;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.YDW;

public abstract class Event implements BasicEvent {

    private final YDW ydw;

    public Event(YDW ydw) {
        this.ydw = ydw;
    }

    @Override
    public YDW getYDW() {
        return null;
    }
}
