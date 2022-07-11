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

    public boolean isDM() {
        return this == DM || this == GROUP_DM;
    }
}
