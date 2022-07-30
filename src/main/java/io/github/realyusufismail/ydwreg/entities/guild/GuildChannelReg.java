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
package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import io.github.realyusufismail.ydwreg.util.Verify;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GuildChannelReg implements GuildChannel {
    private final YDWReg ydw;
    private final Long id;

    private final List<Overwrite> permissionOverwrites = new ArrayList<>();
    private final Boolean nsfw;
    private final String name;
    private final String topic;
    private final Long lastMessageId;
    private final Integer rateLimitPerUser;
    private final Guild guild;
    private final Integer position;
    private final Long parentId;
    private final Integer defaultAutoArchiveDuration;
    private final ChannelType type;

    public GuildChannelReg(@NotNull JsonNode messageJson, long id, @NotNull YDW ydw) {
        this(null, messageJson, id, ydw);
    }

    public GuildChannelReg(@Nullable GuildReg guildReg, @NotNull JsonNode messageJson, long id,
            @NotNull YDW ydw) {
        this.ydw = (YDWReg) ydw;
        this.id = id;

        this.guild = Verify.hasNonNull(guildReg) ? guildReg : null;
        this.name = messageJson.get("name").asText();
        this.position = messageJson.get("position").asInt();
        this.nsfw = messageJson.get("nsfw").asBoolean();
        this.topic = messageJson.get("topic").asText();
        this.lastMessageId = messageJson.get("last_message_id").asLong();
        this.rateLimitPerUser = messageJson.get("rate_limit_per_user").asInt();
        this.parentId = messageJson.get("parent_id").asLong();
        this.defaultAutoArchiveDuration = messageJson.get("default_auto_archive_days").asInt();
        this.type = ChannelType.getChannelType(messageJson.get("type").asInt());
    }

    @Override
    public List<Overwrite> getPermissionOverwrites() {
        return permissionOverwrites;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isNSFW() {
        return nsfw;
    }

    @Override
    public Integer getPosition() {
        return position;
    }

    @Override
    public Integer getRateLimitPerUser() {
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
    public SnowFlake getParentId() {
        return SnowFlake.of(parentId);
    }

    @Override
    public Integer getDefaultAutoArchiveDuration() {
        return defaultAutoArchiveDuration;
    }

    @Override
    public Category getCategory() {
        return ydw.getCategory(getParentId().getId());
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    @NotNull
    @Override
    public ChannelType getType() {
        return type;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return 0;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Nullable
    @Override
    public YDWReg getYDW() {
        return ydw;
    }
}
