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
package io.github.realyusufismail.ydwreg.util;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Used to check if a specified value can be cast to a specified type.
 * 
 * @param <E> The class of which to check if the value can be cast to.
 */
public interface Assignable<E> {
    /**
     * Gets the value as the specified type.
     *
     * @param type The type to get the value as.
     */
    default <T extends E> Optional<T> as(Class<T> type) {
        Objects.requireNonNull(type, "type must not be null");
        return type.isAssignableFrom(this.getClass()) ? Optional.of(type.cast(this))
                : Optional.empty();
    }

    /**
     * Gets the value as the specified type.
     *
     * @param type The type to get the value as.
     */
    default <T extends E> T asOrElse(Class<T> type, T defaultValue) {
        Objects.requireNonNull(type, "type must not be null");
        return type.isAssignableFrom(this.getClass()) ? type.cast(this) : defaultValue;
    }

    /**
     * Gets the value as the specified type.
     *
     * @param type The type to get the value as.
     */
    default <T extends E> T asOrElse(Class<T> type, Supplier<T> defaultValue) {
        Objects.requireNonNull(type, "type must not be null");
        return type.isAssignableFrom(this.getClass()) ? type.cast(this) : defaultValue.get();
    }
}
