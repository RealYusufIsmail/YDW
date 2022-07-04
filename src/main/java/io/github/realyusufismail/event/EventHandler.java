package io.github.realyusufismail.event;

import io.github.realyusufismail.ydw.event.Event;
import io.github.realyusufismail.ydwreg.YDWReg;

import java.util.function.Consumer;

public class EventHandler {
    private final YDWReg ydw;
    private final Client client;
    // private final Executor executor;

    public EventHandler(YDWReg ydwReg, Client client) {
        this.ydw = ydwReg;
        this.client = client;
    }

    public void handle(Event event) {
        // still thinking about how to handle this
        // TODO: implement this
    }

    public <EventClass extends Event> Client on(Class<EventClass> event,
            Consumer<EventClass> consumer) {
        return client;
    }
}
