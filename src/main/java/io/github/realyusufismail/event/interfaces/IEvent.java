package io.github.realyusufismail.event.interfaces;

import io.github.realyusufismail.ydw.event.Event;

@FunctionalInterface
public interface IEvent {
    void onEvent(Event event);
}
