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
package io.github.realyusufismail.ydwreg.entities.guild.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.cache.SnowFlakeCache;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.handle.EventCache;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CategoryReg extends ChannelReg implements Category {
    private final long id;

    private String name;
    private Category category;
    private boolean nsfw;
    private int position;
    private final long guildId;

    public CategoryReg(@NotNull JsonNode channelJ, long id, @NotNull YDW ydw) {
        this(null, channelJ, id, ydw);
    }

    public CategoryReg(GuildReg guildReg, @NotNull JsonNode channelJ, long id, @NotNull YDW ydw) {
        super(channelJ, id, ydw);
        this.id = id;

        this.name = channelJ.get("name").asText();
        this.category = channelJ.hasNonNull("parent_id")
                ? ydw.getChannel(Category.class, channelJ.get("parent_id").asLong())
                : null;
        this.nsfw = channelJ.get("nsfw").asBoolean();
        this.position = channelJ.get("position").asInt();
        this.guildId = channelJ.get("guild_id").asLong();

        boolean playbackCache = false;
        // cache
        CategoryReg channel = (CategoryReg) getYDW().getCategoryCache().get(id);
        if (channel == null) {
            if (guildReg == null)
                guildReg = (GuildReg) getYDW().getGuildCache().get(guildId);

            SnowFlakeCache<Category> guildCategoryView = guildReg.getCategoryCache(),
                    categoryView = getYDW().getCategoryCache();

            channel = this;
            guildCategoryView.put(id, channel);
            playbackCache = categoryView.put(id, channel) == null;
        }

        if (playbackCache)
            getYDW().getEventCache().playbackCache(EventCache.CacheType.CHANNEL, id);
    }


    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(id, o.getIdLong());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Guild getGuild() {
        return ydw.getGuild(guildId);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<Category> getCategory() {
        return Optional.ofNullable(category);
    }

    @Override
    public boolean isNSFW() {
        return nsfw;
    }

    @Override
    public int getPosition() {
        return position;
    }
}
