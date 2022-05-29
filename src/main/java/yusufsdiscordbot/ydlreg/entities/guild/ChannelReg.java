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

package yusufsdiscordbot.ydlreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.Guild;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydl.entities.guild.Channel;
import yusufsdiscordbot.ydl.entities.guild.Message;
import yusufsdiscordbot.ydl.entities.guild.channel.ChannelType;
import yusufsdiscordbot.ydl.entities.guild.channel.Overwrite;
import yusufsdiscordbot.ydl.entities.guild.channel.threads.ThreadMember;
import yusufsdiscordbot.ydl.entities.guild.channel.threads.ThreadMetadata;
import yusufsdiscordbot.ydl.perm.Permission;
import yusufsdiscordbot.ydlreg.entities.UserReg;
import yusufsdiscordbot.ydlreg.entities.channel.OverwriteReg;
import yusufsdiscordbot.ydlreg.entities.channel.thread.ThreadMemberReg;
import yusufsdiscordbot.ydlreg.entities.channel.thread.ThreadMetadataReg;
import yusufsdiscordbot.ydlreg.entities.message.MessageFlags;
import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChannelReg implements Channel {
    private final long id;

    private final YDL ydl;

    // Channel Structure
    private final ChannelType type;
    private final Guild guild;
    private final Integer position;
    private final List<Overwrite> permissionOverwrites = new ArrayList<>();
    private final String name;
    private final String topic;
    private final Boolean nsfw;
    private final Message lastMessage;
    private final Integer bitrate;
    private final Integer userLimit;
    private final Integer rateLimitPerUser;
    private final List<User> recipients = new ArrayList<>();
    private final String icon;
    private final User owner;
    private final Long applicationId;
    private final Long parentId;
    private final ZonedDateTime lastPinTimestamp;
    private final String rtcRegion;
    private final Integer videoQualityMode;
    private final Integer messageCount;
    private final Integer memberCount;
    private final ThreadMetadata threadMetadata;
    private final ThreadMember member;
    private final Integer defaultAutoArchiveDuration;
    private final Permission[] permissions;
    private final MessageFlags[] flags;

    public ChannelReg(JsonNode channelJ, long id, YDL ydl) {
        this.id = id;
        this.ydl = ydl;

        this.type = ChannelType.valueOf(channelJ.get("type").asInt());
        this.guild =
                channelJ.has("guild_id") ? ydl.getGuild(channelJ.get("guild_id").asLong()) : null;
        this.position = channelJ.has("position") ? channelJ.get("position").asInt() : null;
        this.name = channelJ.has("name") ? channelJ.get("name").asText() : null;
        this.topic = channelJ.has("topic") ? channelJ.get("topic").asText() : null;
        this.nsfw = channelJ.has("nsfw") ? channelJ.get("nsfw").asBoolean() : null;
        this.lastMessage =
                channelJ.has("last_message_id")
                        ? ydl.getRest()
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
                channelJ.has("owner_id") ? ydl.getUser(channelJ.get("owner_id").asLong()) : null;
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
                                channelJ.get("thread_metadata").get("id").asLong(), ydl)
                        : null;
        this.member =
                channelJ.has("member")
                        ? new ThreadMemberReg(channelJ.get("member"),
                                channelJ.get("member").get("id").asLong(), ydl)
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
                    .add(new OverwriteReg(permission, permission.get("id").asLong(), ydl));
            }
        }

        if (channelJ.has("recipients")) {
            for (JsonNode recipient : channelJ.get("recipients")) {
                recipients.add(new UserReg(recipient, recipient.get("id").asLong(), ydl));
            }
        }

    }

    /**
     * @return The core long of this api.
     */
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public YDL getYDL() {
        return ydl;
    }

    @Override
    public @NotNull ChannelType getType() {
        return type;
    }

    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
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
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @Override
    public Optional<String> getTopic() {
        return Optional.ofNullable(topic);
    }

    @Override
    public Optional<Boolean> isNSFW() {
        return Optional.ofNullable(nsfw);
    }

    @Override
    public @NotNull Optional<Message> getLastMessage() {
        return Optional.ofNullable(lastMessage);
    }

    @Override
    public Optional<Integer> getBitrate() {
        return Optional.ofNullable(bitrate);
    }

    @Override
    public Optional<Integer> getUserLimit() {
        return Optional.ofNullable(userLimit);
    }

    @Override
    public Optional<Integer> getRateLimitPerUser() {
        return Optional.ofNullable(rateLimitPerUser);
    }

    @Override
    public List<User> getRecipients() {
        return recipients;
    }

    @Override
    public Optional<String> getIcon() {
        return Optional.ofNullable(icon);
    }

    @Override
    public Optional<User> getOwner() {
        return Optional.ofNullable(owner);
    }

    @Override
    public @NotNull Optional<SnowFlake> getApplicationId() {
        return Optional.ofNullable(SnowFlake.of(applicationId));
    }

    @Override
    public @NotNull Optional<SnowFlake> getParentId() {
        return Optional.ofNullable(SnowFlake.of(parentId));
    }

    @Override
    public Optional<ZonedDateTime> getLastPinTimestamp() {
        return Optional.ofNullable(lastPinTimestamp);
    }

    @Override
    public Optional<String> getRTCRegion() {
        return Optional.ofNullable(rtcRegion);
    }

    @Override
    public Optional<Integer> getVideoQualityMode() {
        return Optional.ofNullable(videoQualityMode);
    }

    @Override
    public Optional<Integer> getMessageCount() {
        return Optional.ofNullable(messageCount);
    }

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

    @Override
    public Optional<Integer> getDefaultAutoArchiveDuration() {
        return Optional.ofNullable(defaultAutoArchiveDuration);
    }

    @Override
    public @NotNull Optional<Permission[]> getPermissions() {
        return Optional.ofNullable(permissions);
    }

    @Override
    public Optional<MessageFlags[]> getFlags() {
        return Optional.ofNullable(flags);
    }
}
