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
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.NewsChannel;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.entities.channel.OverwriteReg;
import io.github.realyusufismail.ydwreg.handle.EventCache;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsChannelReg extends ChannelReg implements NewsChannel {
    private final YDW ydw;
    private final long id;
    private final long guildId;
    private String name;
    private int position;
    private List<Overwrite> permissionOverwrites = new ArrayList<>();
    private boolean nsfw;
    private String topic;
    private Category category;
    private int defaultAutoArchiveDuration;
    private long lastMessageId;

    public NewsChannelReg(@NotNull JsonNode json, long id, @NotNull YDW ydw) {
        this(null, json, id, ydw);
    }

    public NewsChannelReg(GuildReg guildReg, @NotNull JsonNode json, long id, @NotNull YDW ydw) {
        super(json, id, ydw);
        this.ydw = ydw;
        this.id = id;

        this.guildId = json.get("guild_id").asLong();
        this.name = json.get("name").asText();
        this.position = json.get("position").asInt();
        this.nsfw = json.get("nsfw").asBoolean();
        this.topic = json.get("topic").asText();
        this.category = json.hasNonNull("parent_id")
                ? ydw.getChannel(Category.class, json.get("parent_id").asLong())
                : null;
        this.defaultAutoArchiveDuration = json.get("default_auto_archive_duration").asInt();
        this.lastMessageId = json.get("last_message_id").asLong();

        if (json.hasNonNull("permission_overwrites")) {
            json.get("permission_overwrites").forEach(permissionOverwrite -> {
                permissionOverwrites.add(new OverwriteReg(permissionOverwrite,
                        permissionOverwrite.get("id").asLong(), ydw));
            });
        }


        boolean playbackCache = false;
        // cache
        NewsChannelReg channel = (NewsChannelReg) getYDW().getNewsChannelCache().get(id);
        if (channel == null) {
            if (guildReg == null)
                guildReg = (GuildReg) getYDW().getGuildCache().get(guildId);

            SnowFlakeCache<NewsChannel> guildNewsView = guildReg.getNewsChannelCache(),
                    newsView = getYDW().getNewsChannelCache();

            channel = this;
            guildNewsView.put(id, channel);
            playbackCache = newsView.put(id, channel) == null;
        }

        if (playbackCache)
            getYDW().getEventCache().playbackCache(EventCache.CacheType.CHANNEL, id);
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

    @Override
    public List<Overwrite> getPermissionOverwrites() {
        return permissionOverwrites;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public SnowFlake lastMessageId() {
        return SnowFlake.of(lastMessageId);
    }

    @Override
    public int getDefaultAutoArchiveDuration() {
        return defaultAutoArchiveDuration;
    }

    @NotNull
    public Long getIdLong() {
        return id;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(id, o.getIdLong());
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPermissionOverwrites(List<Overwrite> permissionOverwrites) {
        this.permissionOverwrites = permissionOverwrites;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDefaultAutoArchiveDuration(int defaultAutoArchiveDuration) {
        this.defaultAutoArchiveDuration = defaultAutoArchiveDuration;
    }
}
