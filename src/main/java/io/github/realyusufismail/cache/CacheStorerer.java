/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.cache;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used to store the cache.
 * 
 * @param <E> The entity type i.e` the object that is being stored.
 */
public class CacheStorerer<E> implements Iterable<E> {
    private final TLongObjectMap<E> map;
    private final Class<E> clazz;

    public CacheStorerer(Class<E> clazz) {
        this.clazz = clazz;
        map = new TLongObjectHashMap<>();
    }

    public TLongObjectMap<E> getMap() {
        return map;
    }

    public E get(long key) {
        return map.get(key);
    }

    public E get(String key) {
        return map.get(Long.parseUnsignedLong(key));
    }

    public void remove(SnowFlake key) {
        map.remove(key.getIdLong());
    }

    public boolean containsKey(SnowFlake key) {
        return map.containsKey(key.getIdLong());
    }

    public boolean containsValue(E value) {
        return map.containsValue(value);
    }

    public void clear() {
        map.clear();
    }

    public List<E> getValues() {
        return new ArrayList<>(map.valueCollection());
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>) map.iterator();
    }

    public E put(long value, E e) {
        return map.put(value, e);
    }
}
