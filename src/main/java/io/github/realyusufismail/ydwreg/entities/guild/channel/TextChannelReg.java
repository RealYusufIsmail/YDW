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
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.entities.channel.OverwriteReg;
import io.github.realyusufismail.ydwreg.handle.EventCache;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TextChannelReg extends GeneralTextChannelReg implements TextChannel {
    private final long id;
    private final YDW ydw;

    private List<Overwrite> permissionOverwrites = new ArrayList<>();
    private Boolean nsfw;
    private String name;
    private String topic;
    private long lastMessageId;
    private int rateLimitPerUser;
    private final long guildId;
    private int position;
    private int defaultAutoArchiveDuration;
    private Category category;

    public TextChannelReg(@NotNull JsonNode messageJson, long id, @NotNull YDW ydw) {
        this(null, messageJson, id, ydw);
    }

    public TextChannelReg(GuildReg guildReg, @NotNull JsonNode messageJson, long id,
            @NotNull YDW ydw) {
        super(ydw, id);
        this.ydw = ydw;
        this.id = id;

        this.guildId = messageJson.get("guild_id").asLong();

        this.name = messageJson.get("name").asText();
        this.topic = messageJson.get("topic").asText();
        this.nsfw = messageJson.get("nsfw").asBoolean();
        this.lastMessageId = messageJson.get("last_message_id").asLong();

        this.position = messageJson.get("position").asInt();
        this.rateLimitPerUser = messageJson.get("rate_limit_per_user").asInt();
        this.defaultAutoArchiveDuration = messageJson.get("default_auto_archive_duration").asInt();
        this.category = messageJson.hasNonNull("parent_id")
                ? ydw.getChannel(Category.class, messageJson.get("parent_id").asLong())
                : null;

        if (messageJson.hasNonNull("permission_overwrites")) {
            for (JsonNode permission : messageJson.get("permission_overwrites")) {
                permissionOverwrites
                    .add(new OverwriteReg(permission, permission.get("id").asLong(), ydw));
            }
        }

        boolean playbackCache = false;
        // cache
        TextChannelReg channel = (TextChannelReg) getYDW().getTextChannelCache().get(id);
        if (channel == null) {
            if (guildReg == null)
                guildReg = (GuildReg) getYDW().getGuildCache().get(guildId);

            SnowFlakeCache<TextChannel> guildTextView = guildReg.getTextChannelCache(),
                    textView = getYDW().getTextChannelCache();

            channel = this;
            guildTextView.put(id, channel);
            playbackCache = textView.put(id, channel) == null;
        }

        if (playbackCache)
            getYDW().getEventCache().playbackCache(EventCache.CacheType.CHANNEL, id);
    }

    @Nullable
    @Override
    public YDWReg getYDW() {
        return (YDWReg) ydw;
    }

    @NotNull
    @Override
    public List<Overwrite> getPermissionOverwrites() {
        return permissionOverwrites;
    }

    @Override
    public int getRateLimitPerUser() {
        return rateLimitPerUser;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public SnowFlake getLastMessageId() {
        return SnowFlake.of(lastMessageId);
    }

    @Override
    public int getDefaultAutoArchiveDuration() {
        return defaultAutoArchiveDuration;
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

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(id, o.getIdLong());
    }

    // setters
    public void setPermissionOverwrites(List<Overwrite> permissionOverwrites) {
        this.permissionOverwrites = permissionOverwrites;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setLastMessageId(long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public void setRateLimitPerUser(int rateLimitPerUser) {
        this.rateLimitPerUser = rateLimitPerUser;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDefaultAutoArchiveDuration(int defaultAutoArchiveDuration) {
        this.defaultAutoArchiveDuration = defaultAutoArchiveDuration;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
