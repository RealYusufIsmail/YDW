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
package io.github.realyusufismail.ydw.entities;

import io.github.realyusufismail.ydw.entities.channel.AssignableChannel;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.channel.*;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.action.MessageActionReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface Channel extends SnowFlake, GenericEntity, AssignableChannel {
    @NotNull
    ChannelType getType();

    /**
     * Gets the channel as a text channel.
     *
     * @return The channel as a text channel.
     */
    default Optional<TextChannel> asTextChannel() {
        return as(TextChannel.class);
    }

    /**
     * Gets the channel as a category.
     *
     * @return The channel as a category.
     */
    default Optional<Category> asCategory() {
        return as(Category.class);
    }

    /**
     * Gets the channel as a guild channel.
     *
     * @return The channel as a guild channel.
     */
    default Optional<GuildChannel> asGuildChannel() {
        return as(GuildChannel.class);
    }

    /**
     * Gets the channel as a news channel.
     *
     * @return The channel as a news channel.
     */
    default Optional<NewsChannel> asNewsChannel() {
        return as(NewsChannel.class);
    }

    /**
     * Gets the channel as a thread channel.
     *
     * @return The channel as a thread channel.
     */
    default Optional<ThreadChannel> asThread() {
        return as(ThreadChannel.class);
    }

    /**
     * Gets the channel as a voice channel.
     *
     * @return The channel as a voice channel.
     */
    default Optional<VoiceChannel> asVoiceChannel() {
        return as(VoiceChannel.class);
    }

    /**
     * Gets the channel as a stage channel.
     *
     * @return The channel as a stage channel.
     */
    default Optional<StageChannel> asStageChannel() {
        return as(StageChannel.class);
    }
}
