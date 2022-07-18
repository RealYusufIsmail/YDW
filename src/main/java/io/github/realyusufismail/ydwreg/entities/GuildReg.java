/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.entities;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.action.Action;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.emoji.Emoji;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydw.entities.guild.SystemChannelFlags;
import io.github.realyusufismail.ydw.entities.guild.WelcomeScreen;
import io.github.realyusufismail.ydw.entities.guild.channel.NewsChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.StageChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.VoiceChannel;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.action.ActionReg;
import io.github.realyusufismail.ydwreg.entities.emoji.EmojiReg;
import io.github.realyusufismail.ydwreg.entities.guild.GuildFeatures;
import io.github.realyusufismail.ydwreg.entities.guild.RoleReg;
import io.github.realyusufismail.ydwreg.entities.guild.WelcomeScreenReg;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerReg;
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
    private final Long ownerId;
    private final Long afkChannelId;
    private final Integer afkTimeout;
    private final Boolean isWidgetEnabled;
    private final String permissions;
    private final Long widgetChannelId;
    private final Integer verificationLevel;
    private final Integer defaultMessageNotifications;
    private final Integer explicitContentFilter;
    private final Integer mfaLevel;
    private final Long applicationId;
    private final Long systemChannelId;
    private final SystemChannelFlags systemChannelFlags;
    private final Long rulesChannelId;
    private final ZonedDateTime joinedAt;
    private final Boolean isLarge;
    private final Boolean isUnavailable;
    private final Integer memberCount;
    private final List<Role> roles = new ArrayList<>();
    private final List<Emoji> emoji = new ArrayList<>();
    private final EnumSet<GuildFeatures> features = EnumSet.noneOf(GuildFeatures.class);
    private final List<Sticker> stickers = new ArrayList<>();
    private final List<TextChannel> textChannels = new ArrayList<>();
    private final List<NewsChannel> newsChannels = new ArrayList<>();
    private final List<StageChannel> stageChannels = new ArrayList<>();
    private final List<VoiceChannel> voiceChannels = new ArrayList<>();
    private final Member selfMember;

    public GuildReg(@NotNull JsonNode guildJ, long guildId, @NotNull YDW ydw) {
        this.ydw = ydw;
        this.id = guildId;

        this.name = guildJ.get("name").asText();
        this.icon = guildJ.get("icon").asText();
        this.iconHash = guildJ.hasNonNull("icon_hash") ? guildJ.get("icon_hash").asText() : null;
        this.splash = guildJ.hasNonNull("splash") ? guildJ.get("splash").asText() : null;
        this.discoverySplash =
                guildJ.hasNonNull("discovery_splash") ? guildJ.get("discovery_splash").asText()
                        : null;
        this.owner = guildJ.hasNonNull("owner") ? guildJ.get("owner").asBoolean() : null;
        this.permissions =
                guildJ.hasNonNull("permissions") ? guildJ.get("permissions").asText() : null;
        this.afkChannelId =
                guildJ.hasNonNull("afk_channel_id") ? guildJ.get("afk_channel_id").asLong() : null;
        this.afkTimeout =
                guildJ.hasNonNull("afk_timeout") ? guildJ.get("afk_timeout").asInt() : null;
        this.isWidgetEnabled =
                guildJ.hasNonNull("widget_enabled") ? guildJ.get("widget_enabled").asBoolean()
                        : null;
        this.widgetChannelId =
                guildJ.hasNonNull("widget_channel_id") ? guildJ.get("widget_channel_id").asLong()
                        : null;
        this.verificationLevel =
                guildJ.hasNonNull("verification_level") ? guildJ.get("verification_level").asInt()
                        : null;
        this.defaultMessageNotifications = guildJ.hasNonNull("default_message_notifications")
                ? guildJ.get("default_message_notifications").asInt()
                : null;
        this.explicitContentFilter = guildJ.hasNonNull("explicit_content_filter")
                ? guildJ.get("explicit_content_filter").asInt()
                : null;
        this.mfaLevel = guildJ.hasNonNull("mfa_level") ? guildJ.get("mfa_level").asInt() : null;
        this.applicationId =
                guildJ.hasNonNull("application_id") ? guildJ.get("application_id").asLong() : null;
        this.systemChannelId =
                guildJ.hasNonNull("system_channel_id") ? guildJ.get("system_channel_id").asLong()
                        : null;
        this.systemChannelFlags = guildJ.hasNonNull("system_channel_flags")
                ? SystemChannelFlags.fromValue(guildJ.get("system_channel_flags").asInt())
                : null;
        this.rulesChannelId =
                guildJ.hasNonNull("rules_channel_id") ? guildJ.get("rules_channel_id").asLong()
                        : null;
        this.maxPresences =
                guildJ.hasNonNull("max_presences") ? guildJ.get("max_presences").asInt() : null;
        this.vanityUrlCode =
                guildJ.hasNonNull("vanity_url_code") ? guildJ.get("vanity_url_code").asText()
                        : null;
        this.description =
                guildJ.hasNonNull("description") ? guildJ.get("description").asText() : null;
        this.banner = guildJ.hasNonNull("banner") ? guildJ.get("banner").asText() : null;
        this.premiumTier =
                guildJ.hasNonNull("premium_tier") ? guildJ.get("premium_tier").asInt() : null;
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
        this.nsfwLevel = guildJ.hasNonNull("nsfw_level") ? guildJ.get("nsfw_level").asInt() : null;
        this.isPremiumProgressBarEnabled =
                guildJ.hasNonNull("premium_tier_name") ? guildJ.get("premium_tier_name").asBoolean()
                        : null;
        this.maxMembers =
                guildJ.hasNonNull("max_members") ? guildJ.get("max_members").asInt() : null;
        this.ownerId = guildJ.hasNonNull("owner_id") ? guildJ.get("owner_id").asLong() : null;
        this.joinedAt = guildJ.hasNonNull("joined_at")
                ? ZonedDateTime.parse(guildJ.get("joined_at").asText())
                : null;
        this.isLarge = guildJ.hasNonNull("large") ? guildJ.get("large").asBoolean() : null;
        this.isUnavailable =
                guildJ.hasNonNull("unavailable") ? guildJ.get("unavailable").asBoolean() : null;
        this.selfMember = ydw.getSelfUser() != null
                ? getGuildCaller().getMember(guildId, ydw.getSelfUser().getId())
                : null;

        final ArrayNode roles = (ArrayNode) guildJ.get("roles");
        final ArrayNode emojis = (ArrayNode) guildJ.get("emojis");
        final ArrayNode features = (ArrayNode) guildJ.get("features");
        final ArrayNode stickers = (ArrayNode) guildJ.get("stickers");

        if (guildJ.hasNonNull("roles")) {
            for (JsonNode roleJ : roles) {
                Role role = new RoleReg(roleJ, ydw, roleJ.get("id").asLong());
                this.roles.add(role);
            }
        }

        if (guildJ.hasNonNull("emojis")) {
            for (JsonNode emojiJ : emojis) {
                Emoji emoji = new EmojiReg(emojiJ, emojiJ.get("id").asLong(), ydw);
                this.emoji.add(emoji);
            }
        }

        if (guildJ.hasNonNull("features")) {
            for (JsonNode featureJ : features) {
                GuildFeatures feature = GuildFeatures.getFeature(featureJ.asText());
                this.features.add(feature);
            }
        }

        if (guildJ.hasNonNull("stickers")) {
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

    @Override
    public @NotNull List<Member> getMembers() {
        return Collections
            .unmodifiableList(ydw.getRest().getGuildCaller().getGuildMembers(this.getIdLong()));
    }

    @Override
    public @NotNull List<Channel> getChannels() {
        return Collections
            .unmodifiableList(ydw.getRest().getGuildCaller().getChannels(this.getIdLong()));
    }

    @NotNull
    @Override
    public Channel getChannel(long channelIdLong) {
        return ydw.getRest().getGuildCaller().getChannel(this.getIdLong(), channelIdLong);
    }

    @Nullable
    @Override
    public Optional<Member> getBot() {
        return Optional.ofNullable(selfMember);
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

    @Override
    public Member getSelfMember() {
        return selfMember;
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
