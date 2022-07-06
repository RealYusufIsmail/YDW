package io.github.realyusufismail.ydw.event.events;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;
import io.github.realyusufismail.ydwreg.YDWReg;

public class ApiStatusChangeEvent extends Event {
    private final YDW.ApiStatus oldStatus;
    private final YDW.ApiStatus newStatus;

    public ApiStatusChangeEvent(YDWReg ydw, YDW.ApiStatus oldStatus, YDW.ApiStatus newStatus) {
        super(ydw);
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        ydw.getClient().eventUpdate(oldStatus, newStatus);
    }

    public YDW.ApiStatus getOldStatus() {
        return oldStatus;
    }

    public YDW.ApiStatus getNewStatus() {
        return newStatus;
    }
}
