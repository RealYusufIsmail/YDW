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

import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.function.Consumer;

public class SortedSnowflakeCache<E extends SnowFlake & Comparable<? super E>>
        extends SnowFlakeCache<E> {

    protected final Comparator<E> comparator;


    public SortedSnowflakeCache(Class<E> clazz, Comparator<E> comparator) {
        super(clazz);
        this.comparator = comparator;
    }

    public void forEach(@NotNull Consumer<? super E> action) {
        iterator().forEachRemaining(action);
    }

    public void forEachUnordered(@NotNull Consumer<? super E> action) {
        super.forEach(action);
    }
}
