package io.github.realyusufismail.ydw.event.events;

import io.github.realyusufismail.event.updater.IEventUpdate;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;

public class GatewayPingEvent extends Event implements IEventUpdate<YDW, Long> {

    private final long oldPing;
    private final long newPing;

    public GatewayPingEvent(YDW ydw, long oldPing) {
        super(ydw);

        this.newPing = ydw.getGatewayPing();
        this.oldPing = oldPing;
    }

    public long getOldPing() {
        return oldPing;
    }

    public long getNewPing() {
        return newPing;
    }

    @Override
    public Long getOldValue() {
        return oldPing;
    }

    @Override
    public Long getNewValue() {
        return newPing;
    }

}
