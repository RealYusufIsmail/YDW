package io.github.realyusufismail.handler;

import io.github.realyusufismail.ydw.event.BasicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface EvenRegister {
    <Event extends BasicEvent> void register(Class<Event> eventClass);

    <Event extends BasicEvent> void unRegister(Class<Event> eventClass);

    void send(@NotNull BasicEvent consume);
}
