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

package io.github.realyusufismail.ydwreg.entities.guild.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.NewsChannel;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.channel.OverwriteReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsChannelReg extends ChannelReg implements NewsChannel {
    private final YDW ydw;
    private final Long id;

    private final Guild guild;
    private final String name;
    private final Integer position;
    private final List<Overwrite> permissionOverwrites = new ArrayList<>();
    private final Boolean nsfw;
    private final String topic;
    private final Long parentId;
    private final Integer defaultAutoArchiveDuration;

    public NewsChannelReg(@NotNull JsonNode json, long id, @NotNull YDW ydw) {
        super(json, id, ydw);
        this.ydw = ydw;
        this.id = id;

        this.guild =
                json.hasNonNull("guild_id") ? ydw.getGuild(json.get("guild_id").asLong()) : null;
        this.name = json.hasNonNull("name") ? json.get("name").asText() : null;
        this.position = json.hasNonNull("position") ? json.get("position").asInt() : null;
        this.nsfw = json.hasNonNull("nsfw") ? json.get("nsfw").asBoolean() : null;
        this.topic = json.hasNonNull("topic") ? json.get("topic").asText() : null;
        this.parentId = json.hasNonNull("parent_id") ? json.get("parent_id").asLong() : null;
        this.defaultAutoArchiveDuration = json.hasNonNull("default_auto_archive_duration")
                ? json.get("default_auto_archive_duration").asInt()
                : null;

        if (json.hasNonNull("permission_overwrites")) {
            json.get("permission_overwrites").forEach(permissionOverwrite -> {
                permissionOverwrites.add(new OverwriteReg(permissionOverwrite,
                        permissionOverwrite.get("id").asLong(), ydw));
            });
        }
    }

    @Override
    public YDWReg getYDW() {
        return (YDWReg) ydw;
    }

    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @NotNull
    @Override
    public ChannelType getType() {
        return NewsChannel.super.getType();
    }

    @Override
    public Optional<Integer> getPosition() {
        return Optional.ofNullable(position);
    }

    @Override
    public List<Overwrite> getPermissionOverwrites() {
        return permissionOverwrites;
    }

    @Override
    public Optional<Boolean> isNSFW() {
        return Optional.ofNullable(nsfw);
    }

    @Override
    public Optional<String> getTopic() {
        return Optional.ofNullable(topic);
    }

    @Override
    public Optional<SnowFlake> getParentId() {
        return Optional.ofNullable(parentId).map(SnowFlake::of);
    }

    @Override
    public Optional<Integer> getDefaultAutoArchiveDuration() {
        return Optional.ofNullable(defaultAutoArchiveDuration);
    }

    @Override
    public Optional<Category> getCategory() {
        return getParentId().map(id -> ydw.getCategory(id.getId()));
    }

    @NotNull
    public Long getIdLong() {
        return id;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(id, o.getIdLong());
    }
}
