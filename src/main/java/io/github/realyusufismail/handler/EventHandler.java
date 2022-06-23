package io.github.realyusufismail.handler;

import io.github.realyusufismail.ydw.event.BasicEvent;

@FunctionalInterface
public interface EventHandler {
    void onEvent(BasicEvent event);
}
