package io.github.realyusufismail.handler;

import io.github.realyusufismail.websocket.event.BasicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CopyOnWriteArrayList;

public class EventSystem implements EventSender {

    private final CopyOnWriteArrayList<EventHandler> listeners = new CopyOnWriteArrayList<>();

    public EventSystem() {}

    @Override
    public void register(Object consumer) {
        if (!(consumer instanceof EventHandler)) {
            throw new IllegalArgumentException("Consumer must implement EventHandler");
        }

        listeners.add((EventHandler) consumer);
    }

    @Override
    public void unregister(Object consumer) {
        if (!(consumer instanceof EventHandler)) {
            throw new IllegalArgumentException("Consumer must implement EventHandler");
        }

        listeners.remove((EventHandler) consumer);
    }

    @Override
    public void send(@NotNull BasicEvent consumer) {
        for (EventHandler listener : listeners) {
            try {
                listener.onEvent(consumer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
