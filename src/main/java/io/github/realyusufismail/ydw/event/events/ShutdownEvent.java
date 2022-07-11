package io.github.realyusufismail.ydw.event.events;

import io.github.realyusufismail.websocket.core.CloseCode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;
import org.joda.time.DateTime;

public class ShutdownEvent extends Event {
    private final DateTime shutdownTime;
    private final CloseCode closeCode;

    public ShutdownEvent(YDW ydw, DateTime shutdownTime, CloseCode closeCode) {
        super(ydw);
        this.shutdownTime = shutdownTime;
        this.closeCode = closeCode;
    }

    public DateTime getShutdownTime() {
        return shutdownTime;
    }

    public CloseCode getCloseCode() {
        return closeCode;
    }
}
