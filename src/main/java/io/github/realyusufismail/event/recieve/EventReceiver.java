package io.github.realyusufismail.event.recieve;

import io.github.realyusufismail.ydw.event.Event;

import java.util.ArrayList;
import java.util.List;

public class EventReceiver implements IEventReceiverConfig {
    // Null as there is no default value for this parameter
    public Event event = null;

    public List<IEventReceiver> eventReceivers;

    public EventReceiver() {
        eventReceivers = new ArrayList<>();
    }

    public void receive(Event event) {
        this.event = event;

        for (IEventReceiver eventReceiver : eventReceivers) {
            eventReceiver.onEvent(event);
        }
    }

    @Override
    public void addEventReceiver(Object eventReceiver) {
        if (eventReceiver instanceof IEventReceiver) {
            eventReceivers.add((IEventReceiver) eventReceiver);
        } else {
            throw new IllegalArgumentException("EventReceiver must be instance of IEventReceiver");
        }
    }

    @Override
    public void removeEventReceiver(Object eventReceiver) {
        if (eventReceiver instanceof IEventReceiver) {
            eventReceivers.remove((IEventReceiver) eventReceiver);
        } else {
            throw new IllegalArgumentException("EventReceiver must be instance of IEventReceiver");
        }
    }
}
