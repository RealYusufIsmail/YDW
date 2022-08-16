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
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.ThreadChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.thread.ThreadMetadataReg;
import io.github.realyusufismail.ydwreg.handle.EventCache;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ThreadChannelReg extends ChannelReg implements ThreadChannel {
    private final long id;

    private String name;
    private final long guildId;
    private final long ownerId;
    private final Category category;
    private long lastMessageId;
    private int messageCount;
    private int memberCount;
    private int rateLimitPerUser;
    private ThreadMetadata metadata;
    private int totalMessagesSent;
    private boolean nsfw;
    private int position;


    public ThreadChannelReg(@NotNull JsonNode json, long id, @NotNull YDW ydw) {
        this(null, json, id, ydw);
    }

    public ThreadChannelReg(GuildReg guildReg, @NotNull JsonNode json, long id, @NotNull YDW ydw) {
        super(json, id, ydw);
        this.id = id;

        this.name = json.get("name").asText();
        guildId = json.get("guild_id").asLong();
        ownerId = json.get("owner_id").asLong();
        category = json.hasNonNull("parent_id")
                ? ydw.getChannel(Category.class, json.get("parent_id").asLong())
                : null;
        this.lastMessageId = json.get("last_message_id").asLong();
        this.messageCount = json.get("message_count").asInt();
        this.memberCount = json.get("member_count").asInt();
        this.rateLimitPerUser = json.get("rate_limit_per_user").asInt();
        this.metadata =
                json.hasNonNull("metadata") ? new ThreadMetadataReg(json.get("metadata")) : null;
        this.totalMessagesSent = json.get("total_messages_sent").asInt();
        this.nsfw = json.get("nsfw").asBoolean();
        this.position = json.get("position").asInt();

        boolean playbackCache = false;
        // cache
        ThreadChannelReg channel = (ThreadChannelReg) getYDW().getThreadChannelCache().get(id);
        if (channel == null) {
            if (guildReg == null)
                guildReg = (GuildReg) getYDW().getGuildCache().get(guildId);

            SnowFlakeCache<ThreadChannel> guildThreadView = guildReg.getThreadChannelCache(),
                    threadView = getYDW().getThreadChannelCache();

            channel = this;
            guildThreadView.put(id, channel);
            playbackCache = threadView.put(id, channel) == null;
        }

        if (playbackCache)
            getYDW().getEventCache().playbackCache(EventCache.CacheType.CHANNEL, id);
    }

    @Override
    public Guild getGuild() {
        return null;
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
    public SnowFlake getOwnerId() {
        return SnowFlake.of(ownerId);
    }

    @Override
    public SnowFlake getLastMessageId() {
        return SnowFlake.of(lastMessageId);
    }

    @Override
    public int getMessageCount() {
        return messageCount;
    }

    @Override
    public int getMemberCount() {
        return memberCount;
    }

    @Override
    public int getRateLimitPerUser() {
        return rateLimitPerUser;
    }

    @Override
    public ThreadMetadata getMetadata() {
        return metadata;
    }

    @Override
    public int getTotalMessagesSent() {
        return totalMessagesSent;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(id, o.getIdLong());
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLastMessageId(long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public void setRateLimitPerUser(int rateLimitPerUser) {
        this.rateLimitPerUser = rateLimitPerUser;
    }

    public void setMetadata(ThreadMetadata metadata) {
        this.metadata = metadata;
    }

    public void setTotalMessagesSent(int totalMessagesSent) {
        this.totalMessagesSent = totalMessagesSent;
    }

    public void setNSFW(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
