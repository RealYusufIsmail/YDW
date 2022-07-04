package io.github.realyusufismail.event;

import io.github.realyusufismail.ydwreg.YDWReg;

public class EventRunner {
    private final YDWReg ydw;
    private final Client client;

    public EventRunner(YDWReg ydw, Client client) {
        this.ydw = ydw;
        this.client = client;
    }
}
