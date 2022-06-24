package io.github.realyusufismail.handler;

import io.github.realyusufismail.ydw.event.BasicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class EventHandler implements EvenRegister {
    private EvenRegister eventRegister;
    private final ExecutorService executor;

    public EventHandler(EvenRegister eventRegister, ExecutorService executor) {
        this.eventRegister = eventRegister;
        this.executor = executor;
    }


    @Override
    public <Event extends BasicEvent> void register(Class<Event> eventClass) {
        eventRegister.register(eventClass);
    }

    @Override
    public <Event extends BasicEvent> void unRegister(Class<Event> eventClass) {
        eventRegister.unRegister(eventClass);
    }

    @Override
    public void send(@NotNull BasicEvent event) {
        try {
            if (executor != null && !executor.isShutdown())
                executor.submit(() -> eventRegister.send(event));
            else
                sendHere(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendHere(@NotNull BasicEvent event) {
        try {
            eventRegister.send(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
