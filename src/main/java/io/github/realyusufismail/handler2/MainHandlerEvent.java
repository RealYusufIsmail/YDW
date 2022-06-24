package io.github.realyusufismail.handler2;

import io.github.realyusufismail.ydw.event.BasicEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class MainHandlerEvent implements EvenRegister {
    private final CopyOnWriteArrayList<EvenRegister> listeners = new CopyOnWriteArrayList<>();

    @Override
    public <Event extends BasicEvent> void register(Class<Event> eventClass, Consumer<Event> consumer) {
        listeners.add((EvenRegister) eventClass.cast(consumer));
    }

    @Override
    public <Event extends BasicEvent> void unRegister(Class<Event> eventClass, Consumer<Event> consumer) {
        listeners.remove((EvenRegister) eventClass.cast(consumer));
    }

    @Override
    public void send(@NotNull BasicEvent consume) {
        for (EvenRegister listener : listeners) {
            try {
                listener.send(consume);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
