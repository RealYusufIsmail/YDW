package io.github.realyusufismail.websocket;

import io.github.realyusufismail.websocket.event.Event;
import io.github.realyusufismail.websocket.event.EventTest;
import io.github.realyusufismail.websocket.event.events.ReadyEvent;

public class Client {

    //Event and then lambda expression
    public <EventName extends Event> Client onEvent(EventName event, EventTest<EventName> eventTest) {
        return this;
    }

}
