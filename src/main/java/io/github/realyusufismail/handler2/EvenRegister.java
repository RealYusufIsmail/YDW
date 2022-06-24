package io.github.realyusufismail.handler2;

import io.github.realyusufismail.ydw.event.BasicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface EvenRegister {
    <Event extends BasicEvent> void register(Class<Event> eventClass, Consumer<Event> consumer);

    <Event extends BasicEvent> void unRegister(Class<Event> eventClass, Consumer<Event> consumer);

    void send(@NotNull BasicEvent consume);
}
