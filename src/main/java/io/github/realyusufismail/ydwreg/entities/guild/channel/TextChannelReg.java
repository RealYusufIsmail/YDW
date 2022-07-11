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
            
package io.github.realyusufismail.ydwreg.entities.guild.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.channel.OverwriteReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO : once rest api is done start creating the rest api for this
public class TextChannelReg extends ChannelReg implements TextChannel {
    private final YDW ydw;

    private final long textChannelId;

    private final List<Overwrite> permissionOverwrites = new ArrayList<>();
    private final Boolean nsfw;
    private final String name;
    private final String topic;
    private final Long lastMessageId;
    private final Integer rateLimitPerUser;
    private final Guild guild;
    private final Integer position;
    private final Long parentId;

    public TextChannelReg(@NotNull JsonNode messageJson, long id, @NotNull YDW ydw) {
        super(messageJson, id, ydw);
        this.ydw = ydw;
        this.textChannelId = id;

        this.guild = messageJson.hasNonNull("guild_id")
                ? ydw.getGuild(messageJson.get("guild_id").asLong())
                : null;
        this.name = messageJson.hasNonNull("name") ? messageJson.get("name").asText() : null;
        this.topic = messageJson.hasNonNull("topic") ? messageJson.get("topic").asText() : null;
        this.nsfw = messageJson.hasNonNull("nsfw") ? messageJson.get("nsfw").asBoolean() : null;
        this.lastMessageId = messageJson.hasNonNull("last_message_id")
                ? messageJson.get("last_message_id").asLong()
                : null;
        this.position =
                messageJson.hasNonNull("position") ? messageJson.get("position").asInt() : null;
        this.rateLimitPerUser = messageJson.hasNonNull("rate_limit_per_user")
                ? messageJson.get("rate_limit_per_user").asInt()
                : null;
        this.parentId =
                messageJson.hasNonNull("parent_id") ? messageJson.get("parent_id").asLong() : null;

        if (messageJson.hasNonNull("permission_overwrites")) {
            for (JsonNode permission : messageJson.get("permission_overwrites")) {
                permissionOverwrites
                    .add(new OverwriteReg(permission, permission.get("id").asLong(), ydw));
            }
        }
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

    @NotNull
    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @NotNull
    @Override
    public Optional<Boolean> isNSFW() {
        return Optional.ofNullable(nsfw);
    }

    @NotNull
    @Override
    public Optional<Integer> getPosition() {
        return Optional.ofNullable(position);
    }

    @NotNull
    @Override
    public Optional<Integer> getRateLimitPerUser() {
        return Optional.ofNullable(rateLimitPerUser);
    }

    @NotNull
    @Override
    public Optional<String> getTopic() {
        return Optional.ofNullable(topic);
    }

    @NotNull
    @Override
    public Optional<SnowFlake> getLastMessageId() {
        return Optional.ofNullable(lastMessageId).map(SnowFlake::of);
    }

    @NotNull
    @Override
    public Optional<SnowFlake> getParentId() {
        return Optional.ofNullable(parentId).map(SnowFlake::of);
    }

    @NotNull
    @Override
    public Optional<Integer> getDefaultAutoArchiveDuration() {
        return Optional.empty();
    }

    @NotNull
    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @Override
    public Optional<Category> getCategory() {
        return getParentId().map(id -> ydw.getCategory(id.getId()));
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return textChannelId;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(textChannelId, o.getIdLong());
    }
}
