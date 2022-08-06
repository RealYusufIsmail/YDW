package io.github.realyusufismail.cache;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;

/**
 * This class is used to store the cache.
 * @param <K> The key type i.e the snowflake id.
 * @param <E> The entity type i.e the object that is being stored.
 */
public class CacheStorerer<K, E> implements Iterable<E> {
    protected final Map<K, E> map;

    public CacheStorerer(Map<K, E> map) {
        this.map = map;
    }

    public void put(K key, E value) {
        map.put(key, value);
    }

    public E get(K key) {
        return map.get(key);
    }

    public void remove(K key) {
        map.remove(key);
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public boolean containsValue(E value) {
        return map.containsValue(value);
    }

    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return map.values().iterator();
    }
}
