/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild;

import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.GenericEntity;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.Guild;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.User;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.channel.ChannelType;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.channel.Overwrite;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.channel.threads.ThreadMember;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.yusufsdiscordbot.ydl.perm.Permission;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.message.MessageFlags;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface Channel extends SnowFlake, GenericEntity {
    @NotNull
    ChannelType getType();

    Optional<Guild> getGuild();

    Optional<Integer> getPosition();

    List<Overwrite> getPermissionOverwrites();

    Optional<String> getName();

    Optional<String> getTopic();

    Optional<Boolean> isNSFW();

    @NotNull
    Optional<Message> getLastMessage();

    Optional<Integer> getBitrate();

    Optional<Integer> getUserLimit();

    Optional<Integer> getRateLimitPerUser();

    List<User> getRecipients();

    Optional<String> getIcon();

    Optional<User> getOwner();

    @NotNull
    Optional<SnowFlake> getApplicationId();

    @NotNull
    Optional<SnowFlake> getParentId();

    Optional<ZonedDateTime> getLastPinTimestamp();

    Optional<String> getRTCRegion();

    Optional<Integer> getVideoQualityMode();

    Optional<Integer> getMessageCount();

    Optional<Integer> getMemberCount();

    @NotNull
    Optional<ThreadMetadata> getThreadMetadata();

    @NotNull
    Optional<ThreadMember> getMember();

    Optional<Integer> getDefaultAutoArchiveDuration();

    @NotNull
    Optional<Permission[]> getPermissions();

    Optional<MessageFlags[]> getFlags();
}
