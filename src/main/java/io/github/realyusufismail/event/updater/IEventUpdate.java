package io.github.realyusufismail.event.updater;

public interface IEventUpdate<YDW, V> {

    YDW getYDW();

    V getOldValue();

    V getNewValue();

    default void update(YDW ydw, V oldValue, V newValue) {
        new UpdateEvent(oldValue, newValue).update(this);
    }
}
