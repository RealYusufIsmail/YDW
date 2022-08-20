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
package io.github.realyusufismail.ydw.entities.guild.channel.cache;

import io.github.realyusufismail.cache.SnowFlakeCache;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.*;
import org.jetbrains.annotations.NotNull;

public interface GuildChannelCache {
    SnowFlakeCache<Category> getCategoryCache();

    SnowFlakeCache<TextChannel> getTextChannelCache();

    SnowFlakeCache<ThreadChannel> getThreadChannelCache();

    SnowFlakeCache<NewsChannel> getNewsChannelCache();

    SnowFlakeCache<StageChannel> getStageChannelCache();

    SnowFlakeCache<VoiceChannel> getVoiceChannelCache();

    /**
     * Used to get a {@link Category category} by its id.
     * 
     * @param id The id of the category.
     * @return The category with the given id.
     */
    default Category getCategory(long id) {
        return getCategoryCache().getCacheById(id);
    }

    /**
     * Used to get a {@link Category category} by its id.
     * 
     * @param id The id of the category.
     * @return The category with the given id.
     */
    default Category getCategory(@NotNull String id) {
        return getCategory(Long.parseLong(id));
    }

    /**
     * Used to get a {@link TextChannel textChannel} by its id.
     * 
     * @param channelId The id of the textChannel.
     * @return The textChannel with the given id.
     */
    default TextChannel getTextChannel(long channelId) {
        return getTextChannelCache().getCacheById(channelId);
    }

    /**
     * Used to get a {@link TextChannel textChannel} by its id.
     * 
     * @param channelId The id of the textChannel.
     * @return The textChannel with the given id.
     */
    default TextChannel getTextChannel(@NotNull String channelId) {
        return getTextChannel(Long.parseLong(channelId));
    }

    /**
     * Used to get a {@link ThreadChannel threadChannel} by its id.
     * 
     * @param channelId The id of the threadChannel.
     * @return The threadChannel with the given id.
     */
    default ThreadChannel getThreadChannel(long channelId) {
        return getThreadChannelCache().getCacheById(channelId);
    }

    /**
     * Used to get a {@link ThreadChannel threadChannel} by its id.
     * 
     * @param channelId The id of the threadChannel.
     * @return The threadChannel with the given id.
     */
    default ThreadChannel getThreadChannel(@NotNull String channelId) {
        return getThreadChannel(Long.parseLong(channelId));
    }

    /**
     * Used to get a {@link NewsChannel newsChannel} by its id.
     * 
     * @param channelId The id of the newsChannel.
     * @return The newsChannel with the given id.
     */
    default NewsChannel getNewsChannel(long channelId) {
        return getNewsChannelCache().getCacheById(channelId);
    }

    /**
     * Used to get a {@link NewsChannel newsChannel} by its id.
     * 
     * @param channelId The id of the newsChannel.
     * @return The newsChannel with the given id.
     */
    default NewsChannel getNewsChannel(@NotNull String channelId) {
        return getNewsChannel(Long.parseLong(channelId));
    }

    /**
     * Used to get a {@link StageChannel stageChannel} by its id.
     * 
     * @param channelId The id of the stageChannel.
     * @return The stageChannel with the given id.
     */
    default StageChannel getStageChannel(long channelId) {
        return getStageChannelCache().getCacheById(channelId);
    }

    /**
     * Used to get a {@link StageChannel stageChannel} by its id.
     * 
     * @param channelId The id of the stageChannel.
     * @return The stageChannel with the given id.
     */
    default StageChannel getStageChannel(@NotNull String channelId) {
        return getStageChannel(Long.parseLong(channelId));
    }

    /**
     * Used to get a {@link VoiceChannel voiceChannel} by its id.
     * 
     * @param channelId The id of the voiceChannel.
     * @return The voiceChannel with the given id.
     */
    default VoiceChannel getVoiceChannel(long channelId) {
        return getVoiceChannelCache().getCacheById(channelId);
    }

    /**
     * Used to get a {@link VoiceChannel voiceChannel} by its id.
     * 
     * @param channelId The id of the voiceChannel.
     * @return The voiceChannel with the given id.
     */
    default VoiceChannel getVoiceChannel(@NotNull String channelId) {
        return getVoiceChannel(Long.parseLong(channelId));
    }

    /**
     * Used to get a {@link GuildChannel guildChannel} by its id.
     * 
     * @param channelId The id of the guildChannel.
     * @return The guildChannel with the given id.
     */
    default GuildChannel getGuildChannelById(long channelId) {
        GuildChannel channel = getCategory(channelId);

        if (channel == null) {
            channel = getTextChannel(channelId);
        }

        if (channel == null) {
            channel = getThreadChannel(channelId);
        }

        if (channel == null) {
            channel = getNewsChannel(channelId);
        }

        if (channel == null) {
            channel = getStageChannel(channelId);
        }

        if (channel == null) {
            channel = getVoiceChannel(channelId);
        }

        return channel;
    }

    /**
     * Used to get a {@link GuildChannel guildChannel} by its id.
     * 
     * @param channelId The id of the guildChannel.
     * @return The guildChannel with the given id.
     */
    default GuildChannel getGuildChannelById(@NotNull String channelId) {
        return getGuildChannelById(Long.parseLong(channelId));
    }

    /**
     * Used to get a {@link GuildChannel guildChannel} by its type and id.
     *
     * @param type The type of the guildChannel.
     * @param id The id of the guildChannel.
     * @return The guildChannel with the given id.
     */
    default GuildChannel getGuildChannelById(@NotNull ChannelType type, long id) {
        return switch (type) {
            case CATEGORY -> getCategory(id);
            case TEXT -> getTextChannel(id);
            case NEWS_THREAD, PRIVATE_THREAD, PUBLIC_THREAD -> getThreadChannel(id);
            case NEWS -> getNewsChannel(id);
            case STAGE -> getStageChannel(id);
            case VOICE -> getVoiceChannel(id);
            default -> null;
        };
    }
}
