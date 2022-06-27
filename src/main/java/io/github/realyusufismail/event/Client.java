package io.github.realyusufismail.event;

import io.github.realyusufismail.ydw.event.Event;
import io.realyusufismail.elastic.api.EventEmitter;
import io.realyusufismail.elastic.api.ExecutionParameters;
import io.realyusufismail.elastic.api.IModule;

import java.util.function.Consumer;

public class Client implements IModule {
    @Override
    public void execute(ExecutionParameters parameters) {
        EventEmitter eventEmitter = parameters.getEventEmitter();

    }

    public <EventClass extends Event> Client on(Class<EventClass> event,
                                                Consumer<EventClass> consumer) {
        return this;
    }
}
