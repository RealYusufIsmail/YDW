package io.github.realyusufismail.event.recieve;

import io.github.realyusufismail.ydw.event.Event;

public interface IEventReceiver {
    void onEvent(Event event);
}
