package io.github.realyusufismail.event;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.realyusufismail.elastic.api.EventEmitter;
import io.realyusufismail.elastic.api.ExecutionParameters;
import io.realyusufismail.elastic.api.IModule;

import java.util.function.Consumer;

public class Client implements IModule {

    private final YDWReg ydw;
    private final EventHandler eventHandler;
    private final EventUpdater eventUpdater;
    private final EventRunner eventRunner;

    public Client(YDWReg ydw) {
        this.ydw = ydw;
        this.eventHandler = new EventHandler(ydw, this);
        this.eventUpdater = new EventUpdater(ydw, this);
        this.eventRunner = new EventRunner(ydw, this);
    }

    @Override
    public void execute(ExecutionParameters parameters) {
        EventEmitter eventEmitter = parameters.getEventEmitter();

    }


    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public EventUpdater getEventUpdater() {
        return eventUpdater;
    }

    public EventRunner getEventRunner() {
        return eventRunner;
    }
}
