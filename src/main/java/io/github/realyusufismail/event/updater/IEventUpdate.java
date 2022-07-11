package io.github.realyusufismail.event.updater;

public interface IEventUpdate<YDW, V> {

    YDW getYDW();

    V getOldValue();

    V getNewValue();
}
