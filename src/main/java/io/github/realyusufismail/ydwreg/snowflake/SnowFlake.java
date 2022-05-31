/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
 */

package io.github.realyusufismail.ydwreg.snowflake;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    @Nullable
    Long getIdLong();}


record SnowFlakeReg(long id) implements SnowFlake {

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
