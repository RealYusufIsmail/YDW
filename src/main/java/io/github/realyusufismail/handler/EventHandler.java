package io.github.realyusufismail.handler;

import io.github.realyusufismail.websocket.event.BasicEvent;

@FunctionalInterface
public interface EventHandler {
    void onEvent(BasicEvent event);
}
