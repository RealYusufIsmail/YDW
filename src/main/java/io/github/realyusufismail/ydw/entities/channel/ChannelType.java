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

package io.github.realyusufismail.ydw.entities.channel;

import org.jetbrains.annotations.NotNull;

public enum ChannelType {
    GUILD_TEXT(0),
    DM(1),
    GUILD_VOICE(2),
    GROUP_DM(3),
    GUILD_CATEGORY(4),
    GUILD_NEWS(5),
    GUILD_STORE(6),
    GUILD_NEWS_THREAD(10),
    GUILD_PUBLIC_THREAD(11),
    GUILD_PRIVATE_THREAD(12),
    GUILD_STAGE_VOICE(13),
    UNKNOWN(-1);

    private final int value;

    ChannelType(int value) {
        this.value = value;
    }

    // gets the value
    public static @NotNull ChannelType getChannelType(int value) {
        for (ChannelType type : ChannelType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }

    public boolean isGuild() {
        return this == GUILD_TEXT || this == GUILD_VOICE || this == GUILD_CATEGORY
                || this == GUILD_NEWS || this == GUILD_STORE || this == GUILD_NEWS_THREAD
                || this == GUILD_PUBLIC_THREAD || this == GUILD_PRIVATE_THREAD
                || this == GUILD_STAGE_VOICE;
    }
}
