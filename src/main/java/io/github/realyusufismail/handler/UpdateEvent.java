package io.github.realyusufismail.handler;

import io.github.realyusufismail.websocket.event.BasicEvent;

/**
 * Used when updating an event.
 * @param <E> the entity type
 * @param <V> the value type
 */
public interface UpdateEvent<E, V> extends BasicEvent {
    /**
     * The entity.
     */
    E getEntity();

    /**
     * The old value.
     */
    V getOldValue();

    /**
     * The new value.
     */
    V getNewValue();

    /**
     * Gives you the name of the updated field.
     * 
     * @return the name of the updated field
     */
    String getFieldName();

    /**
     * The class of the entity.
     */
    default Class<E> getEntityClass() {
        return (Class<E>) getEntity().getClass();
    }
}
