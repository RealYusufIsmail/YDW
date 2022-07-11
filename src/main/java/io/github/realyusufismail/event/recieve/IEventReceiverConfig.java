package io.github.realyusufismail.event.recieve;

public interface IEventReceiverConfig {
    void addEventReceiver(Object eventReceiver);

    void removeEventReceiver(Object eventReceiver);
}
