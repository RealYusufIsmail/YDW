/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.apache.commons.collections4.iterators.ObjectArrayIterator;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class CacheSetter<T> implements ICacheSetter<T> {
    private final TLongObjectMap<T> cache;
    private final Function<T, String> nameFunction;
    private final Class<T> clazz;

    public CacheSetter(Class<T> clazz, Function<T, String> nameFunction) {
        cache = new TLongObjectHashMap<>();
        this.clazz = clazz;
        this.nameFunction = nameFunction;
    }

    @Override
    public TLongObjectMap<T> getCacheMap() {
        return cache;
    }

    @Override
    public T get(long key) {
        return cache.get(key);
    }

    @Override
    public T remove(long key) {
        return cache.remove(key);
    }


    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public List<T> getCacheByName(String name) {
        if (cache.isEmpty()) {
            return Collections.emptyList();
        }

        if (nameFunction == null) {
            throw new IllegalStateException("nameFunction is null");
        }

        List<T> list = new ArrayList<>();
        forEach(value -> {
            String name1 = nameFunction.apply(value);
            if (name1 != null && name1.equals(name)) {
                list.add(value);
            }
        });
        return list;
    }

    @Override
    public List<T> toList() {
        if (cache.isEmpty()) {
            return Collections.emptyList();
        }

        List<T> list = new ArrayList<>();
        list = new ArrayList<>(cache.size());
        forEach(list::add);
        return Collections.unmodifiableList(list);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new ObjectArrayIterator<>(cache.values((T[]) Array.newInstance(clazz, 0)));
    }

    public void forEach(Consumer<? super T> action) {
        for (T value : cache.valueCollection()) {
            action.accept(value);
        }
    }
}
