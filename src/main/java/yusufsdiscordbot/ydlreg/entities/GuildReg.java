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

package yusufsdiscordbot.ydlreg.entities;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.Guild;
import yusufsdiscordbot.ydl.entities.emoji.Emoji;
import yusufsdiscordbot.ydl.entities.guild.*;
import yusufsdiscordbot.ydl.entities.sticker.Sticker;
import yusufsdiscordbot.ydl.entities.voice.VoiceState;
import yusufsdiscordbot.ydlreg.YDLReg;
import yusufsdiscordbot.ydlreg.action.Action;
import yusufsdiscordbot.ydlreg.action.reg.ActionReg;
import yusufsdiscordbot.ydlreg.entities.emoji.EmojiReg;
import yusufsdiscordbot.ydlreg.entities.guild.*;
import yusufsdiscordbot.ydlreg.entities.sticker.StickerReg;
import yusufsdiscordbot.ydlreg.entities.voice.VoiceStateReg;
import yusufsdiscordbot.ydlreg.rest.callers.GuildCaller;
import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;
import yusufsdiscordbot.ydlreg.util.Verify;

import java.time.ZonedDateTime;
import java.util.*;

// TODO go through this and make sure it works and change some method in oder so they use rest
// instead of web socket.
public class GuildReg implements Guild {
    private final YDL ydl;
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
    private final WelcomeScreen welcomeScreen;
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
    private final List<VoiceState> voiceStates = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Channel> channels = new ArrayList<>();
    private final List<Sticker> stickers = new ArrayList<>();
    GuildCaller restApi = getYDL().getRest().getGuildRestApi();

    public GuildReg(JsonNode guildJ, long guildId, YDL ydl) {
        this.ydl = ydl;
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
                ? new WelcomeScreenReg(guildJ.get("welcome_screen"), ydl, guildId)
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
                Role role = new RoleReg(roleJ, ydl, roleJ.get("id").asLong());
                this.roles.add(role);
            }
        }

        if (guildJ.has("emojis")) {
            for (JsonNode emojiJ : emojis) {
                Emoji emoji = new EmojiReg(emojiJ, emojiJ.get("id").asLong(), ydl);
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
                VoiceState voiceState = new VoiceStateReg(voiceStateJ, ydl);
                this.voiceStates.add(voiceState);
            }
        }

        if (guildJ.has("members")) {
            for (JsonNode memberJ : members) {
                Member member = new MemberReg(memberJ, ydl);
                this.members.add(member);
            }
        }

        if (guildJ.has("channels")) {
            for (JsonNode channelJ : channels) {
                Channel channel = new ChannelReg(channelJ, channelJ.get("id").asLong(), ydl);
                this.channels.add(channel);
            }
        }

        if (guildJ.has("stickers")) {
            for (JsonNode stickerJ : stickers) {
                Sticker sticker = new StickerReg(stickerJ, stickerJ.get("id").asLong(), ydl);
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

    @Override
    public Optional<Boolean> isWidgetEnabled() {
        return Optional.ofNullable(isWidgetEnabled);
    }

    @Override
    public @NotNull Optional<SnowFlake> getWidgetChannelIdLong() {
        return Optional.of(SnowFlake.of(widgetChannelId));
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

    @Override
    public Optional<Integer> getMaxPresences() {
        return Optional.ofNullable(maxPresences);
    }

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

    @Override
    public Optional<Integer> getMaxVideoChannelUsers() {
        return Optional.ofNullable(maxVideoChannelUsers);
    }

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

    @Override
    public Member getBot() {
        return null;
    }

    @Override
    public Boolean isPremiumProgressBarEnabled() {
        return isPremiumProgressBarEnabled;
    }

    @Override
    public @NotNull Action ban(String userId, int deleteMessageDays, String reason) {
        Verify.checkAmount(deleteMessageDays, 7);
        var request = restApi.ban(this.getIdLong(), userId, deleteMessageDays, reason);
        return new ActionReg(request, ydl);
    }

    @Override
    public @NotNull Action ban(long userId, int deleteMessageDays, String reason) {
        Verify.checkAmount(deleteMessageDays, 7);
        var request = restApi.ban(this.getIdLong(), userId, deleteMessageDays, reason);
        return new ActionReg(request, ydl);
    }

    @Override
    public Boolean isBanned(long userId) {
        return restApi.isBanned(this.getIdLong(), userId);
    }

    @Override
    public Boolean isBanned(String userId) {
        return restApi.isBanned(this.getIdLong(), userId);
    }

    @Override
    public @NotNull Action unBanUser(String userId) {
        var request = restApi.unBan(this.getIdLong(), userId);
        return new ActionReg(request, ydl);
    }

    @Override
    public @NotNull Action unBanUser(long userId) {
        var request = restApi.unBan(this.getIdLong(), userId);
        return new ActionReg(request, ydl);
    }

    @Override
    public @NotNull Action kick(long userId) {
        var request = restApi.kickMember(this.getIdLong(), userId);
        return new ActionReg(request, ydl);
    }

    @Override
    public @NotNull Action kick(String userId) {
        var request = restApi.kickMember(this.getIdLong(), userId);
        return new ActionReg(request, ydl);
    }

    @Override
    public @Nullable Member getMemberById(String memberId) {
        return restApi.getMember(this.getIdLong(), memberId);
    }

    @Override
    public @NotNull Member getMemberById(long id) {
        return restApi.getMember(this.getIdLong(), id);
    }

    @Override
    public Sticker getStickerById(long id) {
        return ydl.getRest().getStickerCaller().getSticker(this.getIdLong(), id);
    }

    /**
     * @return The core long of this api.
     */
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public YDLReg getYDL() {
        return (YDLReg) ydl;
    }

}
