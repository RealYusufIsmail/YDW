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
package io.github.realyusufismail.ydw.entities.channel;

import org.jetbrains.annotations.NotNull;

public enum ChannelType {
    /**
     * A text channel within a server.
     */
    TEXT(0),
    /**
     * A direct message between users.
     */
    DM(1, false),
    /**
     * A voice channel within a server.
     */
    VOICE(2),
    /**
     * A direct message between multiple users.
     */
    GROUP_DM(3, false),
    /**
     * An organizational category that contains up to 50 channels.
     */
    CATEGORY(4),
    /**
     * A channel that users can follow and crosspost into their own server.
     */
    NEWS(5),
    /**
     * A temporary sub-channel within a GUILD_NEWS channel.
     */
    NEWS_THREAD(10),
    /**
     * A temporary sub-channel within a GUILD_TEXT channel.
     */
    PUBLIC_THREAD(11),
    /**
     * A temporary sub-channel within a GUILD_TEXT channel that is only viewable by those invited
     * and those with the MANAGE_THREADS permission.
     */
    PRIVATE_THREAD(12),
    /**
     * A voice channel for hosting events with an audience.
     */
    STAGE(13),
    /**
     * The channel in a hub containing the listed servers.
     */
    GUILD_DIRECTORY(14),
    /**
     * (still in development) A channel that can only contain threads.
     */
    GUILD_FORUM(15),
    /**
     * An unknown channel type.
     */
    UNKNOWN(-1);

    private final int value;
    private final boolean isGuildChannel;

    ChannelType(int value, boolean isGuildChannel) {
        this.value = value;
        this.isGuildChannel = isGuildChannel;
    }

    ChannelType(int value) {
        this(value, true);
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
        return isGuildChannel;
    }

    public boolean isDM() {
        return this == DM || this == GROUP_DM;
    }
}
