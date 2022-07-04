package io.github.realyusufismail.event;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;
import io.github.realyusufismail.ydwreg.YDWReg;

public class EventUpdater {
    private final YDWReg ydw;
    private final Client client;

    public EventUpdater(YDWReg ydw, Client client) {
        this.ydw = ydw;
        this.client = client;
    }

    public static abstract class UpdateEvent<E, V> extends Event {

        public UpdateEvent(YDW ydw) {
            super(ydw);
        }

        public abstract E getEntity();

        public abstract V getOldValue();

        public abstract V getNewValue();

        public abstract String getFieldName();
    }


}
