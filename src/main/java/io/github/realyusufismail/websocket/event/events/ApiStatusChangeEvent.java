package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.websocket.event.Event;
import io.github.realyusufismail.ydw.YDW;

public class ApiStatusChangeEvent extends Event {
    private YDW.ApiStatus oldStatus;
    private YDW.ApiStatus newStatus;

    public ApiStatusChangeEvent(YDW ydw) {
        super(ydw);
    }

    public YDW.ApiStatus getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(YDW.ApiStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public YDW.ApiStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(YDW.ApiStatus newStatus) {
        this.newStatus = newStatus;
    }
}
