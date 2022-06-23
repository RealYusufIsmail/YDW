package io.github.realyusufismail.handler;

import io.github.realyusufismail.ydw.event.BasicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;

public class EventSenderSystem implements EventSender {

    private EventSender eventSender;
    private final ExecutorService executor;


    public EventSenderSystem(EventSender eventSender, ExecutorService executor) {
        this.eventSender = eventSender;
        this.executor = executor;
    }

    public void setEventSender(EventSender eventSender) {
        this.eventSender = eventSender == null ? new EventSystem() : eventSender;
    }


    @Override
    public void register(Object consumer) {
        this.eventSender.register(consumer);
    }

    @Override
    public void unregister(Object consumer) {
        this.eventSender.unregister(consumer);
    }

    @Override
    public void send(@NotNull BasicEvent consumer) {
        try {
            if (executor != null && !executor.isShutdown())
                executor.submit(() -> eventSender.send(consumer));
            else
                sendHere(consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendHere(@NotNull BasicEvent consumer) {
        try {
            eventSender.send(consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
