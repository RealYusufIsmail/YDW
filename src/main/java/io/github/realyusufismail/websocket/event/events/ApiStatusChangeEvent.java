package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.handler.UpdateEvent;
import io.github.realyusufismail.websocket.event.EventExtender;
import io.github.realyusufismail.ydw.YDW;

public class ApiStatusChangeEvent extends EventExtender implements UpdateEvent<YDW, YDW.ApiStatus> {
    private final YDW.ApiStatus oldStatus;
    private final YDW.ApiStatus newStatus;

    public ApiStatusChangeEvent(YDW ydw, YDW.ApiStatus oldStatus, YDW.ApiStatus newStatus) {
        super(ydw);
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }



    @Override
    public YDW getEntity() {
        return getYDW();
    }

    @Override
    public YDW.ApiStatus getOldValue() {
        return oldStatus;
    }

    @Override
    public YDW.ApiStatus getNewValue() {
        return newStatus;
    }

    public YDW.ApiStatus getOldStatus() {
        return oldStatus;
    }

    public YDW.ApiStatus getNewStatus() {
        return newStatus;
    }

    @Override
    public String getFieldName() {
        return "status";
    }
}
