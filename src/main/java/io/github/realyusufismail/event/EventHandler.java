package io.github.realyusufismail.event;

import io.github.realyusufismail.ydw.event.Event;
import io.github.realyusufismail.ydwreg.YDWReg;

public class EventHandler {
    private final YDWReg ydw;

    public EventHandler(YDWReg ydwReg) {
        this.ydw = ydwReg;
    }

    public void handle(Event event) {
        //still thinking about how to handle this
        //TODO: implement this
    }
}
