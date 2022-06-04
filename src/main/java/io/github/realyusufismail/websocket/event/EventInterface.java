package io.github.realyusufismail.websocket.event;

public interface EventInterface<T> {
    T onEvent(T event);

}
