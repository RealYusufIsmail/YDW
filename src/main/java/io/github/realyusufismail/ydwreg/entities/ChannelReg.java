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

package io.github.realyusufismail.ydwreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.action.Action;
import io.github.realyusufismail.ydw.action.MessageAction;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.action.MessageActionReg;
import io.github.realyusufismail.ydwreg.entities.channel.OverwriteReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.thread.ThreadMetadataReg;
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
    private final Long lastMessageId;
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
    private final Member member;
    private final Integer defaultAutoArchiveDuration;
    private final Permission[] permissions;
    private final MessageFlags[] flags;
    private final Category category;

    public ChannelReg(@NotNull JsonNode channelJ, long id, @NotNull YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.type = ChannelType.getChannelType(channelJ.get("type").asInt());
        this.guild =
                channelJ.hasNonNull("guild_id") ? ydw.getGuild(channelJ.get("guild_id").asLong())
                        : null;
        this.position = channelJ.hasNonNull("position") ? channelJ.get("position").asInt() : null;
        this.name = channelJ.hasNonNull("name") ? channelJ.get("name").asText() : null;
        this.topic = channelJ.hasNonNull("topic") ? channelJ.get("topic").asText() : null;
        this.nsfw = channelJ.hasNonNull("nsfw") ? channelJ.get("nsfw").asBoolean() : null;
        this.lastMessageId =
                channelJ.hasNonNull("last_message_id") ? channelJ.get("last_message_id").asLong()
                        : null;
        this.bitrate = channelJ.hasNonNull("bitrate") ? channelJ.get("bitrate").asInt() : null;
        this.userLimit =
                channelJ.hasNonNull("user_limit") ? channelJ.get("user_limit").asInt() : null;
        this.rateLimitPerUser = channelJ.hasNonNull("rate_limit_per_user")
                ? channelJ.get("rate_limit_per_user").asInt()
                : null;
        this.icon = channelJ.hasNonNull("icon") ? channelJ.get("icon").asText() : null;
        this.owner =
                channelJ.hasNonNull("owner_id") ? ydw.getUser(channelJ.get("owner_id").asLong())
                        : null;
        this.applicationId =
                channelJ.hasNonNull("application_id") ? channelJ.get("application_id").asLong()
                        : null;
        this.parentId =
                channelJ.hasNonNull("parent_id") ? channelJ.get("parent_id").asLong() : null;
        this.lastPinTimestamp = channelJ.hasNonNull("last_pin_timestamp")
                ? ZonedDateTime.parse(channelJ.get("last_pin_timestamp").asText())
                : null;
        this.rtcRegion =
                channelJ.hasNonNull("rtc_region") ? channelJ.get("rtc_region").asText() : null;
        this.videoQualityMode = channelJ.hasNonNull("video_quality_mode")
                ? channelJ.get("video_quality_mode").asInt()
                : null;
        this.messageCount =
                channelJ.hasNonNull("message_count") ? channelJ.get("message_count").asInt() : null;
        this.memberCount =
                channelJ.hasNonNull("member_count") ? channelJ.get("member_count").asInt() : null;
        this.threadMetadata =
                channelJ.hasNonNull("thread_metadata")
                        ? new ThreadMetadataReg(channelJ.get("thread_metadata"),
                                channelJ.get("thread_metadata").get("id").asLong(), ydw)
                        : null;
        this.member =
                channelJ.hasNonNull("member") ? new MemberReg(channelJ.get("member"), ydw) : null;
        this.defaultAutoArchiveDuration = channelJ.hasNonNull("default_auto_archive_duration")
                ? channelJ.get("default_auto_archive_duration").asInt()
                : null;
        this.permissions = channelJ.hasNonNull("permissions")
                ? Permission.getPermissions(channelJ.get("permissions").asInt())
                : null;
        this.flags = channelJ.hasNonNull("flags")
                ? MessageFlags.fromValues(channelJ.get("flags").asInt())
                : null;
        this.category = channelJ.hasNonNull("parent_id")
                ? ydw.getCategory(channelJ.get("parent_id").asLong())
                : null;

        if (channelJ.hasNonNull("permission_overwrites")) {
            for (JsonNode permission : channelJ.get("permission_overwrites")) {
                permissionOverwrites
                    .add(new OverwriteReg(permission, permission.get("id").asLong(), ydw));
            }
        }

        if (channelJ.hasNonNull("recipients")) {
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
    public Optional<SnowFlake> getLastMessageId() {
        return Optional.ofNullable(lastMessageId).map(SnowFlake::of);
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

    @NotNull
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
        return Optional.ofNullable(applicationId).map(SnowFlake::of);
    }

    @Override
    public Optional<SnowFlake> getParentId() {
        return Optional.ofNullable(parentId).map(SnowFlake::of);
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
    public Optional<ThreadMetadata> getThreadMetadata() {
        return Optional.ofNullable(threadMetadata);
    }

    @Override
    public Optional<Member> getMember() {
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Integer> getDefaultAutoArchiveDuration() {
        return Optional.ofNullable(defaultAutoArchiveDuration);
    }

    @Override
    public Optional<Permission[]> getPermissions() {
        return Optional.ofNullable(permissions);
    }

    @Override
    public Optional<MessageFlags[]> getFlags() {
        return Optional.ofNullable(flags);
    }

    @Override
    public Optional<Category> getCategory() {
        return Optional.ofNullable(category);
    }

    @Override
    public void sendMessage(String message) {
        ydw.getRest().getChannelCaller().sendMessage(this.id, message);
    }

    @Override
    public void sendEmbedMessage(EmbedBuilder embedBuilder) {
        ydw.getRest().getChannelCaller().sendEmbedMessage(this.id, embedBuilder);
    }

    @NotNull
    @Override
    public Message getMessage(@NotNull String messageId) {
        return null;
    }

    @NotNull
    @Override
    public Message getMessage(long messageId) {
        return null;
    }

    @Nullable
    @Override
    public Action deleteMessage(@NotNull String messageId) {
        return null;
    }

    @Nullable
    @Override
    public Action deleteMessage(long messageId) {
        return null;
    }

    @Nullable
    @Override
    public Action deleteMessages(int min, int max) {
        return null;
    }
}
