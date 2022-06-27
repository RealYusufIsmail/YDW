package io.github.realyusufismail.event;

public interface UpdateEvent<YDW, V> {

    YDW getEntity();

    V getOldValue();

    V getNewValue();

    String getFieldName();
}
