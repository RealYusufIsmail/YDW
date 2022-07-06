package io.github.realyusufismail.event.updater;

public class UpdateEvent {
    private final Object oldEvent;
    private final Object newEvent;

    public UpdateEvent(Object oldEvent, Object newEvent) {
        this.oldEvent = oldEvent;
        this.newEvent = newEvent;
    }

    public <YDW, V> void update(IEventUpdate<YDW, V> eventUpdate) {}
}
