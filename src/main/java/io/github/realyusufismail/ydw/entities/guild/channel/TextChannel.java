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

package io.github.realyusufismail.ydw.entities.guild.channel;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface TextChannel extends SnowFlake, GenericEntity, GuildChannel {

    @Override
    List<Overwrite> getPermissionOverwrites();

    @Override
    Optional<String> getName();

    @Override
    Optional<Boolean> isNSFW();

    @Override
    Optional<Integer> getPosition();

    @Override
    Optional<Integer> getRateLimitPerUser();

    @Override
    Optional<String> getTopic();

    @Override
    Optional<SnowFlake> getLastMessageId();

    @Override
    Optional<SnowFlake> getParentId();

    @Override
    Optional<Integer> getDefaultAutoArchiveDuration();

    @Override
    Optional<Category> getCategory();

    @Override
    Optional<Guild> getGuild();

    @NotNull
    @Override
    default ChannelType getType() {
        return ChannelType.GUILD_TEXT;
    }

}
