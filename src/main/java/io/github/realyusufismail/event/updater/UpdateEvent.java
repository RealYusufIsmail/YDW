package io.github.realyusufismail.event.updater;

public class UpdateEvent {
    private Object oldEvent;
    private Object newEvent;

    public UpdateEvent(Object oldEvent, Object newEvent) {
        this.oldEvent = oldEvent;
        this.newEvent = newEvent;
    }

    public <YDW, V> void update(IEventUpdate<YDW, V> eventUpdate) {}
}
