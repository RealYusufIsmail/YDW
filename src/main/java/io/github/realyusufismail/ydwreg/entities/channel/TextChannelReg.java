/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
 */

package io.github.realyusufismail.ydwreg.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.channel.TextChannel;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.guild.ChannelReg;
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
    private final Message lastMessage;
    private final Integer rateLimitPerUser;
    private final Guild guild;
    private final Integer position;
    private final Long parentId;

    public TextChannelReg(@NotNull JsonNode messageJson, long id, @NotNull YDW ydw) {
        super(messageJson, id, ydw);
        this.ydw = ydw;
        this.textChannelId = id;

        this.guild =
                messageJson.has("guild_id") ? ydw.getGuild(messageJson.get("guild_id").asLong())
                        : null;
        this.name = messageJson.has("name") ? messageJson.get("name").asText() : null;
        this.topic = messageJson.has("topic") ? messageJson.get("topic").asText() : null;
        this.nsfw = messageJson.has("nsfw") ? messageJson.get("nsfw").asBoolean() : null;
        this.lastMessage = messageJson.has("last_message_id")
                ? ydw.getRest()
                    .getChannelCaller()
                    .getMessage(this.textChannelId, messageJson.get("last_message_id").asLong())
                : null;
        this.position = messageJson.has("position") ? messageJson.get("position").asInt() : null;
        this.rateLimitPerUser = messageJson.has("rate_limit_per_user")
                ? messageJson.get("rate_limit_per_user").asInt()
                : null;
        this.parentId = messageJson.has("parent_id") ? messageJson.get("parent_id").asLong() : null;

        if (messageJson.has("permission_overwrites")) {
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
    public Optional<Message> getLastMessage() {
        return Optional.ofNullable(lastMessage);
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

    @NotNull
    @Override
    public Long getIdLong() {
        return textChannelId;
    }
}
