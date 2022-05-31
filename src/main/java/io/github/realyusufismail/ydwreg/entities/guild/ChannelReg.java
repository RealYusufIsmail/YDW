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

package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMember;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.entities.channel.OverwriteReg;
import io.github.realyusufismail.ydwreg.entities.channel.thread.ThreadMemberReg;
import io.github.realyusufismail.ydwreg.entities.channel.thread.ThreadMetadataReg;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChannelReg implements Channel {
    private final long id;

    private final YDW ydw;

    // Channel Structure
    @NotNull
    private final ChannelType type;
    private final Guild guild;
    private final Integer position;
    private final List<Overwrite> permissionOverwrites = new ArrayList<>();
    private final String name;
    private final String topic;
    private final Boolean nsfw;
    @Nullable
    private final Message lastMessage;
    private final Integer bitrate;
    private final Integer userLimit;
    private final Integer rateLimitPerUser;
    private final List<User> recipients = new ArrayList<>();
    private final String icon;
    @Nullable
    private final User owner;
    private final Long applicationId;
    private final Long parentId;
    private final ZonedDateTime lastPinTimestamp;
    private final String rtcRegion;
    private final Integer videoQualityMode;
    private final Integer messageCount;
    private final Integer memberCount;
    @Nullable
    private final ThreadMetadata threadMetadata;
    @Nullable
    private final ThreadMember member;
    private final Integer defaultAutoArchiveDuration;
    @Nullable
    private final Permission[] permissions;
    @Nullable
    private final MessageFlags[] flags;

    public ChannelReg(@NotNull JsonNode channelJ, long id, @NotNull YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.type = ChannelType.valueOf(channelJ.get("type").asInt());
        this.guild =
                channelJ.has("guild_id") ? ydw.getGuild(channelJ.get("guild_id").asLong()) : null;
        this.position = channelJ.has("position") ? channelJ.get("position").asInt() : null;
        this.name = channelJ.has("name") ? channelJ.get("name").asText() : null;
        this.topic = channelJ.has("topic") ? channelJ.get("topic").asText() : null;
        this.nsfw = channelJ.has("nsfw") ? channelJ.get("nsfw").asBoolean() : null;
        this.lastMessage =
                channelJ.has("last_message_id")
                        ? ydw.getRest()
                            .getChannelCaller()
                            .getMessage(this.id, channelJ.get("last_message_id").asLong())
                        : null;
        this.bitrate = channelJ.has("bitrate") ? channelJ.get("bitrate").asInt() : null;
        this.userLimit = channelJ.has("user_limit") ? channelJ.get("user_limit").asInt() : null;
        this.rateLimitPerUser =
                channelJ.has("rate_limit_per_user") ? channelJ.get("rate_limit_per_user").asInt()
                        : null;
        this.icon = channelJ.has("icon") ? channelJ.get("icon").asText() : null;
        this.owner =
                channelJ.has("owner_id") ? ydw.getUser(channelJ.get("owner_id").asLong()) : null;
        this.applicationId =
                channelJ.has("application_id") ? channelJ.get("application_id").asLong() : null;
        this.parentId = channelJ.has("parent_id") ? channelJ.get("parent_id").asLong() : null;
        this.lastPinTimestamp = channelJ.has("last_pin_timestamp")
                ? ZonedDateTime.parse(channelJ.get("last_pin_timestamp").asText())
                : null;
        this.rtcRegion = channelJ.has("rtc_region") ? channelJ.get("rtc_region").asText() : null;
        this.videoQualityMode =
                channelJ.has("video_quality_mode") ? channelJ.get("video_quality_mode").asInt()
                        : null;
        this.messageCount =
                channelJ.has("message_count") ? channelJ.get("message_count").asInt() : null;
        this.memberCount =
                channelJ.has("member_count") ? channelJ.get("member_count").asInt() : null;
        this.threadMetadata =
                channelJ.has("thread_metadata")
                        ? new ThreadMetadataReg(channelJ.get("thread_metadata"),
                                channelJ.get("thread_metadata").get("id").asLong(), ydw)
                        : null;
        this.member =
                channelJ.has("member")
                        ? new ThreadMemberReg(channelJ.get("member"),
                                channelJ.get("member").get("id").asLong(), ydw)
                        : null;
        this.defaultAutoArchiveDuration = channelJ.has("default_auto_archive_duration")
                ? channelJ.get("default_auto_archive_duration").asInt()
                : null;
        this.permissions = channelJ.has("permissions")
                ? Permission.getPermissions(channelJ.get("permissions").asInt())
                : null;
        this.flags = channelJ.has("flags") ? MessageFlags.fromValues(channelJ.get("flags").asInt())
                : null;

        if (channelJ.has("permission_overwrites")) {
            for (JsonNode permission : channelJ.get("permission_overwrites")) {
                permissionOverwrites
                    .add(new OverwriteReg(permission, permission.get("id").asLong(), ydw));
            }
        }

        if (channelJ.has("recipients")) {
            for (JsonNode recipient : channelJ.get("recipients")) {
                recipients.add(new UserReg(recipient, recipient.get("id").asLong(), ydw));
            }
        }

    }

    /**
     * @return The core long of this api.
     */
    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @Override
    public @NotNull ChannelType getType() {
        return type;
    }

    @NotNull
    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @NotNull
    @Override
    public Optional<Integer> getPosition() {
        return Optional.ofNullable(position);
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
    public Optional<String> getTopic() {
        return Optional.ofNullable(topic);
    }

    @NotNull
    @Override
    public Optional<Boolean> isNSFW() {
        return Optional.ofNullable(nsfw);
    }

    @Override
    public @NotNull Optional<Message> getLastMessage() {
        return Optional.ofNullable(lastMessage);
    }

    @NotNull
    @Override
    public Optional<Integer> getBitrate() {
        return Optional.ofNullable(bitrate);
    }

    @NotNull
    @Override
    public Optional<Integer> getUserLimit() {
        return Optional.ofNullable(userLimit);
    }

    @NotNull
    @Override
    public Optional<Integer> getRateLimitPerUser() {
        return Optional.ofNullable(rateLimitPerUser);
    }

    @NotNull
    @Override
    public List<User> getRecipients() {
        return recipients;
    }

    @NotNull
    @Override
    public Optional<String> getIcon() {
        return Optional.ofNullable(icon);
    }

    @NotNull
    @Override
    public Optional<User> getOwner() {
        return Optional.ofNullable(owner);
    }

    @Override
    public @NotNull Optional<SnowFlake> getApplicationId() {
        return Optional.ofNullable(applicationId).map(SnowFlake::of);
    }

    @Override
    public @NotNull Optional<SnowFlake> getParentId() {
        return Optional.ofNullable(parentId).map(SnowFlake::of);
    }

    @NotNull
    @Override
    public Optional<ZonedDateTime> getLastPinTimestamp() {
        return Optional.ofNullable(lastPinTimestamp);
    }

    @NotNull
    @Override
    public Optional<String> getRTCRegion() {
        return Optional.ofNullable(rtcRegion);
    }

    @NotNull
    @Override
    public Optional<Integer> getVideoQualityMode() {
        return Optional.ofNullable(videoQualityMode);
    }

    @NotNull
    @Override
    public Optional<Integer> getMessageCount() {
        return Optional.ofNullable(messageCount);
    }

    @NotNull
    @Override
    public Optional<Integer> getMemberCount() {
        return Optional.ofNullable(memberCount);
    }

    @Override
    public @NotNull Optional<ThreadMetadata> getThreadMetadata() {
        return Optional.ofNullable(threadMetadata);
    }

    @Override
    public @NotNull Optional<ThreadMember> getMember() {
        return Optional.ofNullable(member);
    }

    @NotNull
    @Override
    public Optional<Integer> getDefaultAutoArchiveDuration() {
        return Optional.ofNullable(defaultAutoArchiveDuration);
    }

    @Override
    public @NotNull Optional<Permission[]> getPermissions() {
        return Optional.ofNullable(permissions);
    }

    @NotNull
    @Override
    public Optional<MessageFlags[]> getFlags() {
        return Optional.ofNullable(flags);
    }
}
