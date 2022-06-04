package io.github.realyusufismail.websocket.event;

import io.github.realyusufismail.websocket.event.events.InvalidSessionEvent;
import io.github.realyusufismail.websocket.event.events.ReadyEvent;

public interface EventTest<T> {
    T onEvent(T event);

}
