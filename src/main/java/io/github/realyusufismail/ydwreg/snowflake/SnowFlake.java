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
            
package io.github.realyusufismail.ydwreg.snowflake;

import org.jetbrains.annotations.NotNull;

public interface SnowFlake {
    static @NotNull SnowFlake of(@NotNull String string) {
        return new SnowFlakeReg(Long.parseUnsignedLong(string));
    }

    static @NotNull SnowFlake of(@NotNull Long id) {
        return new SnowFlakeReg(id);
    }

    /**
     * @return The core string of this api.
     */
    default String getId() {
        return Long.toUnsignedString(getIdLong());
    }

    /**
     * @return The core long of this api.
     */
    @NotNull
    Long getIdLong();}


record SnowFlakeReg(long id) implements SnowFlake {

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
