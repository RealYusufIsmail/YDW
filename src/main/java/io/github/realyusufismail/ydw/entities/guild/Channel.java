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

import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.ydw.action.Action;
import io.github.realyusufismail.ydw.action.MessageAction;
import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.channel.threads.ThreadMember;
import io.github.realyusufismail.ydw.entities.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    Optional<SnowFlake> getApplicationId();

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

    Optional<ChannelCategory> getCategory();

    @CheckReturnValue
    @NotNull
    MessageAction sendMessage(String message);

    @CheckReturnValue
    @NotNull
    MessageAction sendEmbedMessage(EmbedBuilder embedBuilder);

    @NotNull
    Message getMessage(@NotNull String messageId);

    @NotNull
    Message getMessage(long messageId);

    @Nullable
    Action deleteMessage(@NotNull String messageId);

    @Nullable
    Action deleteMessage(long messageId);

    @Nullable
    Action deleteMessages(int min, int max);
}
