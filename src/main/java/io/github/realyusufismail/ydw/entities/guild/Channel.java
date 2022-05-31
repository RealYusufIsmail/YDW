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

package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMember;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
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
