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
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.action.Action;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.NewsChannel;
import io.github.realyusufismail.ydw.entities.channel.StageChannel;
import io.github.realyusufismail.ydw.entities.channel.TextChannel;
import io.github.realyusufismail.ydw.entities.channel.VoiceChannel;
import io.github.realyusufismail.ydw.entities.emoji.Emoji;
import io.github.realyusufismail.ydw.entities.guild.*;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.entities.voice.VoiceState;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.action.ActionReg;
import io.github.realyusufismail.ydwreg.entities.emoji.EmojiReg;
import io.github.realyusufismail.ydwreg.entities.guild.*;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerReg;
import io.github.realyusufismail.ydwreg.entities.voice.VoiceStateReg;
import io.github.realyusufismail.ydwreg.rest.callers.GuildCaller;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import io.github.realyusufismail.ydwreg.util.Verify;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.*;

// TODO go through this and make sure it works and change some method in oder so they use rest
// instead of web socket.
public class GuildReg implements Guild {
    private final YDW ydw;
    private final long id;
    private final String name;
    private final String icon;
    private final String iconHash;
    private final String splash;
    private final String discoverySplash;
    private final Boolean owner;
    private final Integer maxPresences;
    private final Integer maxMembers;
    private final String vanityUrlCode;
    private final String description;
    private final String banner;
    @NotNull
    private final Integer premiumTier;
    private final Integer premiumSubscriptionCount;
    private final String preferredLocale;
    private final Long publicUpdateChannelId;
    private final Integer maxVideoChannelUsers;
    private final Integer approximatePresenceCount;
    @Nullable
    private final WelcomeScreen welcomeScreen;
    @NotNull
    private final Integer nsfwLevel;
    private final Boolean isPremiumProgressBarEnabled;
    @NotNull
    private final Long ownerId;
    @NotNull
    private final Long afkChannelId;
    @NotNull
    private final Integer afkTimeout;
    private final Boolean isWidgetEnabled;
    private final String permissions;
    private final Long widgetChannelId;
    @NotNull
    private final Integer verificationLevel;
    @NotNull
    private final Integer defaultMessageNotifications;
    @NotNull
    private final Integer explicitContentFilter;
    @NotNull
    private final Integer mfaLevel;
    @NotNull
    private final Long applicationId;
    @NotNull
    private final Long systemChannelId;
    private final SystemChannelFlags systemChannelFlags;
    @NotNull
    private final Long rulesChannelId;
    private final ZonedDateTime joinedAt;
    private final Boolean isLarge;
    private final Boolean isUnavailable;
    private final Integer memberCount;
    private final List<Role> roles = new ArrayList<>();
    private final List<Emoji> emoji = new ArrayList<>();
    private final EnumSet<GuildFeatures> features = EnumSet.noneOf(GuildFeatures.class);
    private final List<VoiceState> voiceStates = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Channel> channels = new ArrayList<>();
    private final List<Sticker> stickers = new ArrayList<>();
    private final List<TextChannel> textChannels = new ArrayList<>();
    private final List<NewsChannel> newsChannels = new ArrayList<>();
    private final List<StageChannel> stageChannels = new ArrayList<>();
    private final List<VoiceChannel> voiceChannels = new ArrayList<>();

    public GuildReg(@NotNull JsonNode guildJ, long guildId, @NotNull YDW ydw) {
        this.ydw = ydw;
        this.id = guildId;

        this.name = guildJ.get("name").asText();
        this.icon = guildJ.get("icon").asText();
        this.iconHash = guildJ.hasNonNull("icon_hash") ? guildJ.get("icon_hash").asText() : null;
        this.splash = guildJ.get("splash").asText();
        this.discoverySplash = guildJ.get("discovery_splash").asText();
        this.owner = guildJ.hasNonNull("owner") ? guildJ.get("owner").asBoolean() : null;
        this.permissions =
                guildJ.hasNonNull("permissions") ? guildJ.get("permissions").asText() : null;
        this.afkChannelId = guildJ.get("afk_channel_id").asLong();
        this.afkTimeout = guildJ.get("afk_timeout").asInt();
        this.isWidgetEnabled =
                guildJ.hasNonNull("widget_enabled") ? guildJ.get("widget_enabled").asBoolean()
                        : null;
        this.widgetChannelId =
                guildJ.hasNonNull("widget_channel_id") ? guildJ.get("widget_channel_id").asLong()
                        : null;
        this.verificationLevel = guildJ.get("verification_level").asInt();
        this.defaultMessageNotifications = guildJ.get("default_message_notifications").asInt();
        this.explicitContentFilter = guildJ.get("explicit_content_filter").asInt();
        this.mfaLevel = guildJ.get("mfa_level").asInt();
        this.applicationId = guildJ.get("application_id").asLong();
        this.systemChannelId = guildJ.get("system_channel_id").asLong();
        this.systemChannelFlags = guildJ.hasNonNull("system_channel_flags")
                ? SystemChannelFlags.fromValue(guildJ.get("system_channel_flags").asInt())
                : null;
        this.rulesChannelId = guildJ.get("rules_channel_id").asLong();
        this.maxPresences =
                guildJ.hasNonNull("max_presences") ? guildJ.get("max_presences").asInt() : null;
        this.vanityUrlCode =
                guildJ.hasNonNull("vanity_url_code") ? guildJ.get("vanity_url_code").asText()
                        : null;
        this.description =
                guildJ.hasNonNull("description") ? guildJ.get("description").asText() : null;
        this.banner = guildJ.hasNonNull("banner") ? guildJ.get("banner").asText() : null;
        this.premiumTier = guildJ.get("premium_tier").asInt();
        this.premiumSubscriptionCount = guildJ.hasNonNull("premium_subscription_count")
                ? guildJ.get("premium_subscription_count").asInt()
                : null;
        this.preferredLocale =
                guildJ.hasNonNull("preferred_locale") ? guildJ.get("preferred_locale").asText()
                        : null;
        this.publicUpdateChannelId = guildJ.hasNonNull("public_update_channel_id")
                ? guildJ.get("public_update_channel_id").asLong()
                : null;
        this.maxVideoChannelUsers = guildJ.hasNonNull("max_video_channel_users")
                ? guildJ.get("max_video_channel_users").asInt()
                : null;
        this.memberCount =
                guildJ.hasNonNull("member_count") ? guildJ.get("member_count").asInt() : null;
        this.approximatePresenceCount = guildJ.hasNonNull("approximate_presence_count")
                ? guildJ.get("approximate_presence_count").asInt()
                : null;
        this.welcomeScreen = guildJ.hasNonNull("welcome_screen")
                ? new WelcomeScreenReg(guildJ.get("welcome_screen"), ydw, guildId)
                : null;
        this.nsfwLevel = guildJ.get("nsfw_level").asInt();
        this.isPremiumProgressBarEnabled =
                guildJ.hasNonNull("premium_tier_name") ? guildJ.get("premium_tier_name").asBoolean()
                        : null;
        this.maxMembers =
                guildJ.hasNonNull("max_members") ? guildJ.get("max_members").asInt() : null;
        this.ownerId = guildJ.get("owner_id").asLong();
        this.joinedAt = guildJ.hasNonNull("joined_at")
                ? ZonedDateTime.parse(guildJ.get("joined_at").asText())
                : null;
        this.isLarge = guildJ.hasNonNull("large") ? guildJ.get("large").asBoolean() : null;
        this.isUnavailable =
                guildJ.hasNonNull("unavailable") ? guildJ.get("unavailable").asBoolean() : null;


        final ArrayNode roles = (ArrayNode) guildJ.get("roles");
        final ArrayNode emojis = (ArrayNode) guildJ.get("emojis");
        final ArrayNode features = (ArrayNode) guildJ.get("features");
        final ArrayNode voiceStates = (ArrayNode) guildJ.get("voice_states");
        final ArrayNode members = (ArrayNode) guildJ.get("members");
        final ArrayNode channels = (ArrayNode) guildJ.get("channels");
        final ArrayNode stickers = (ArrayNode) guildJ.get("stickers");

        if (guildJ.has("roles")) {
            for (JsonNode roleJ : roles) {
                Role role = new RoleReg(roleJ, ydw, roleJ.get("id").asLong());
                this.roles.add(role);
            }
        }

        if (guildJ.has("emojis")) {
            for (JsonNode emojiJ : emojis) {
                Emoji emoji = new EmojiReg(emojiJ, emojiJ.get("id").asLong(), ydw);
                this.emoji.add(emoji);
            }
        }

        if (guildJ.has("features")) {
            for (JsonNode featureJ : features) {
                GuildFeatures feature = GuildFeatures.getFeature(featureJ.asText());
                this.features.add(feature);
            }
        }

        if (guildJ.has("voice_states")) {
            for (JsonNode voiceStateJ : voiceStates) {
                VoiceState voiceState = new VoiceStateReg(voiceStateJ, ydw);
                this.voiceStates.add(voiceState);
            }
        }

        if (guildJ.has("members")) {
            for (JsonNode memberJ : members) {
                Member member = new MemberReg(memberJ, ydw);
                this.members.add(member);
            }
        }

        if (guildJ.has("channels")) {
            for (JsonNode channelJ : channels) {
                Channel channel = new ChannelReg(channelJ, channelJ.get("id").asLong(), ydw);
                this.channels.add(channel);
            }
        }

        if (guildJ.has("stickers")) {
            for (JsonNode stickerJ : stickers) {
                Sticker sticker = new StickerReg(stickerJ, stickerJ.get("id").asLong(), ydw);
                this.stickers.add(sticker);
            }
        }
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public @NotNull String getIcon() {
        return icon;
    }

    @Override
    public @NotNull Optional<String> getIconHash() {
        return Optional.ofNullable(iconHash);
    }

    @Override
    public @NotNull String getSplash() {
        return splash;
    }

    @Override
    public @NotNull String getDiscoverySplash() {
        return discoverySplash;
    }

    @NotNull
    @Override
    public Optional<Boolean> isOwner() {
        return Optional.ofNullable(owner);
    }

    /**
     * @return The owner id.
     */
    @Override
    public @NotNull SnowFlake getOwnerIdLong() {
        return SnowFlake.of(ownerId);
    }

    @NotNull
    @Override
    public Optional<String> getPermissions() {
        return Optional.ofNullable(permissions);
    }

    @Override
    public @NotNull SnowFlake getAfkChannelIdLong() {
        return SnowFlake.of(afkChannelId);
    }

    @Override
    public Integer getAfkTimeOut() {
        return afkTimeout;
    }

    @NotNull
    @Override
    public Optional<Boolean> isWidgetEnabled() {
        return Optional.ofNullable(isWidgetEnabled);
    }

    @Override
    public @NotNull Optional<SnowFlake> getWidgetChannelIdLong() {
        return Optional.ofNullable(widgetChannelId).map(SnowFlake::of);
    }

    @Override
    public Integer getVerificationLevel() {
        return verificationLevel;
    }

    @Override
    public Integer getDefaultMessageNotifications() {
        return defaultMessageNotifications;
    }

    @Override
    public Integer getExplicitContentFilter() {
        return explicitContentFilter;
    }

    @Override
    public Integer getMfALevel() {
        return mfaLevel;
    }

    @Override
    public @NotNull SnowFlake getApplicationIdLong() {
        return SnowFlake.of(applicationId);
    }

    @Override
    public @NotNull SnowFlake getSystemChannelIdLong() {
        return SnowFlake.of(systemChannelId);
    }

    @Override
    public SystemChannelFlags getSystemChannelFlags() {
        return systemChannelFlags;
    }

    @Override
    public @NotNull SnowFlake getRulesChannelIdLong() {
        return SnowFlake.of(rulesChannelId);
    }

    @Override
    public ZonedDateTime getJoinedAt() {
        return joinedAt;
    }

    @Override
    public Boolean isLarge() {
        return isLarge;
    }

    @Override
    public Boolean isUnavailable() {
        return isUnavailable;
    }

    @Override
    public Integer getMemberCount() {
        return memberCount;
    }

    @NotNull
    @Override
    public Optional<Integer> getMaxPresences() {
        return Optional.ofNullable(maxPresences);
    }

    @NotNull
    @Override
    public Optional<Integer> getMaxMembers() {
        return Optional.ofNullable(maxMembers);
    }

    @Override
    public @NotNull String getVanityUrlCode() {
        return vanityUrlCode;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public @NotNull String getBanner() {
        return banner;
    }

    @Override
    public Integer getPremiumTier() {
        return premiumTier;
    }

    @NotNull
    @Override
    public Optional<Integer> getPremiumSubscriptionCount() {
        return Optional.ofNullable(premiumSubscriptionCount);
    }

    @Override
    public String getPreferredLocale() {
        return preferredLocale;
    }

    @Override
    public @NotNull SnowFlake getPublicUpdateChannelIdLong() {
        return SnowFlake.of(publicUpdateChannelId);
    }

    @NotNull
    @Override
    public Optional<Integer> getMaxVideoChannelUsers() {
        return Optional.ofNullable(maxVideoChannelUsers);
    }

    @NotNull
    @Override
    public Optional<Integer> getApproximatePresenceCount() {
        return Optional.ofNullable(approximatePresenceCount);
    }

    @Override
    public @NotNull Optional<WelcomeScreen> getWelcomeScreen() {
        return Optional.ofNullable(welcomeScreen);
    }

    @Override
    public Integer getNSFWLevel() {
        return nsfwLevel;
    }

    @Override
    public @NotNull Optional<List<Sticker>> getStickers() {
        return Optional.ofNullable(stickers);
    }

    @Override
    public @NotNull List<Role> getRoles() {
        return roles;
    }

    @Override
    public @NotNull List<Emoji> getEmojis() {
        return Collections.unmodifiableList(emoji);
    }

    @Override
    public EnumSet<GuildFeatures> getFeatures() {
        return EnumSet.copyOf(features);
    }

    @NotNull
    @Override
    public List<VoiceState> getVoiceStates() {
        return Collections.unmodifiableList(voiceStates);
    }

    @Override
    public @NotNull List<Member> getMembers() {
        return Collections.unmodifiableList(members);
    }

    @Override
    public @NotNull List<Channel> getChannels() {
        return Collections.unmodifiableList(channels);
    }

    @Nullable
    @Override
    public Member getBot() {
        return null;
    }

    @Override
    public Boolean isPremiumProgressBarEnabled() {
        return isPremiumProgressBarEnabled;
    }

    @Override
    public @NotNull Action ban(@NotNull String userId, int deleteMessageDays, String reason) {
        Verify.checkAmount(deleteMessageDays, 7);
        var request = getGuildCaller().ban(this.getIdLong(), userId, deleteMessageDays, reason);
        return new ActionReg(request, ydw);
    }

    @Override
    public @NotNull Action ban(long userId, int deleteMessageDays, String reason) {
        Verify.checkAmount(deleteMessageDays, 7);
        var request = getGuildCaller().ban(this.getIdLong(), userId, deleteMessageDays, reason);
        return new ActionReg(request, ydw);
    }

    @NotNull
    @Override
    public Boolean isBanned(long userId) {
        return getGuildCaller().isBanned(this.getIdLong(), userId);
    }

    @NotNull
    @Override
    public Boolean isBanned(@NotNull String userId) {
        return getGuildCaller().isBanned(this.getIdLong(), userId);
    }

    @Override
    public @NotNull Action unBanUser(@NotNull String userId) {
        var request = getGuildCaller().unBan(this.getIdLong(), userId);
        return new ActionReg(request, ydw);
    }

    @Override
    public @NotNull Action unBanUser(long userId) {
        var request = getGuildCaller().unBan(this.getIdLong(), userId);
        return new ActionReg(request, ydw);
    }

    @Override
    public @NotNull Action kick(long userId) {
        var request = getGuildCaller().kickMember(this.getIdLong(), userId);
        return new ActionReg(request, ydw);
    }

    @Override
    public @NotNull Action kick(@NotNull String userId) {
        var request = getGuildCaller().kickMember(this.getIdLong(), userId);
        return new ActionReg(request, ydw);
    }

    @Override
    public @Nullable Member getMemberById(@NotNull String memberId) {
        return getGuildCaller().getMember(this.getIdLong(), memberId);
    }

    @Override
    public @NotNull Member getMemberById(long id) {
        return getGuildCaller().getMember(this.getIdLong(), id);
    }

    @Override
    public Sticker getStickerById(long id) {
        return ydw.getRest().getStickerCaller().getSticker(this.getIdLong(), id);
    }

    @Override
    public List<TextChannel> getTextChannels() {
        return textChannels;
    }

    @Override
    public List<VoiceChannel> getVoiceChannels() {
        return voiceChannels;
    }

    @Override
    public List<NewsChannel> getNewsChannels() {
        return newsChannels;
    }

    @Override
    public List<StageChannel> getStageChannels() {
        return stageChannels;
    }

    /**
     * @return The core long of this api.
     */
    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Nullable
    @Override
    public YDWReg getYDW() {
        return (YDWReg) ydw;
    }

    @NotNull
    private GuildCaller getGuildCaller() {
        return ydw.getRest().getGuildCaller();
    }
}
