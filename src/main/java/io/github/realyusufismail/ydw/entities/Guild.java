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

package io.github.realyusufismail.ydw.entities;


import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.ydw.action.Action;
import io.github.realyusufismail.ydw.entities.emoji.Emoji;
import io.github.realyusufismail.ydw.entities.guild.*;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.entities.voice.VoiceState;
import io.github.realyusufismail.ydwreg.entities.guild.GuildFeatures;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public interface Guild extends SnowFlake, GenericEntity {
    String getName();

    String getIcon();

    Optional<String> getIconHash();

    String getSplash();

    String getDiscoverySplash();

    Optional<Boolean> isOwner();

    /**
     * @return The owner id.
     */
    @NotNull
    SnowFlake getOwnerIdLong();

    Optional<String> getPermissions();

    @NotNull
    SnowFlake getAfkChannelIdLong();

    Integer getAfkTimeOut();

    Optional<Boolean> isWidgetEnabled();

    @NotNull
    Optional<SnowFlake> getWidgetChannelIdLong();

    Integer getVerificationLevel();

    Integer getDefaultMessageNotifications();

    Integer getExplicitContentFilter();

    Integer getMfALevel();

    @NotNull
    SnowFlake getApplicationIdLong();

    @NotNull
    SnowFlake getSystemChannelIdLong();

    SystemChannelFlags getSystemChannelFlags();

    @NotNull
    SnowFlake getRulesChannelIdLong();

    ZonedDateTime getJoinedAt();

    Boolean isLarge();

    Boolean isUnavailable();

    Integer getMemberCount();

    Optional<Integer> getMaxPresences();

    Optional<Integer> getMaxMembers();

    String getVanityUrlCode();

    String getDescription();

    String getBanner();

    Integer getPremiumTier();

    Optional<Integer> getPremiumSubscriptionCount();

    String getPreferredLocale();

    @NotNull
    SnowFlake getPublicUpdateChannelIdLong();

    Optional<Integer> getMaxVideoChannelUsers();

    Optional<Integer> getApproximatePresenceCount();

    @NotNull
    Optional<WelcomeScreen> getWelcomeScreen();

    Integer getNSFWLevel();


    Boolean isPremiumProgressBarEnabled();

    Optional<List<Sticker>> getStickers();

    @NotNull
    List<Role> getRoles();

    @NotNull
    List<Emoji> getEmojis();

    EnumSet<GuildFeatures> getFeatures();

    List<VoiceState> getVoiceStates();

    @NotNull
    List<Member> getMembers();

    @NotNull
    List<Channel> getChannels();

    Member getBot();

    @CheckReturnValue
    @NotNull
    default Action ban(String userId) {
        return ban(userId, 0, null);
    }

    @CheckReturnValue
    @NotNull
    default Action ban(String userId, int deleteMessageDays) {
        return ban(userId, deleteMessageDays, null);
    }

    @CheckReturnValue
    @NotNull
    Action ban(String userId, int deleteMessageDays, String reason);

    @CheckReturnValue
    @NotNull
    default Action ban(long userId) {
        return ban(userId, 0, null);
    }

    @CheckReturnValue
    @NotNull
    default Action ban(long userId, int deleteMessageDays) {
        return ban(userId, deleteMessageDays, null);
    }

    @CheckReturnValue
    @NotNull
    Action ban(long userId, int deleteMessageDays, String reason);

    @CheckReturnValue
    default @NotNull Action ban(@NotNull Member member) {
        return ban(member.getId());
    }

    @CheckReturnValue
    default @NotNull Action ban(@NotNull Member member, int deleteMessageDays) {
        return ban(member.getId(), deleteMessageDays);
    }

    @CheckReturnValue
    default @NotNull Action ban(@NotNull Member member, int deleteMessageDays, String reason) {
        return ban(member.getId(), deleteMessageDays, reason);
    }

    @CheckReturnValue
    default @NotNull Action ban(@NotNull User user) {
        return ban(user.getId());
    }

    @CheckReturnValue
    default @NotNull Action ban(@NotNull User user, int deleteMessageDays) {
        return ban(user.getId(), deleteMessageDays);
    }

    @CheckReturnValue
    default @NotNull Action ban(@NotNull User user, int deleteMessageDays, String reason) {
        return ban(user.getId(), deleteMessageDays, reason);
    }

    Boolean isBanned(long userId);

    Boolean isBanned(String userId);

    default Boolean isBanned(@NotNull User user) {
        return isBanned(user.getId());
    }

    @CheckReturnValue
    @NotNull
    Action unBanUser(String userId);

    @CheckReturnValue
    @NotNull
    Action unBanUser(long userId);

    @CheckReturnValue
    default @NotNull Action unBanUser(@NotNull User user) {
        return unBanUser(user.getId());
    }

    @CheckReturnValue
    @NotNull
    default Action kick(@NotNull Member user) {
        return kick(user.getId());
    }

    @CheckReturnValue
    @NotNull
    Action kick(long userId);

    @CheckReturnValue
    @NotNull
    Action kick(String userId);

    @Nullable
    Member getMemberById(String id);

    @NotNull
    Member getMemberById(long id);

    /**
     * Gets a specific sticker in the guild.
     *
     * @param id The long id of the sticker.
     * @return The sticker.
     */
    Sticker getStickerById(long id);

    /**
     * Gets a specific sticker in the guild.
     *
     * @param id The String id of the sticker.
     * @return The sticker.
     */
    default Sticker getStickerById(@NotNull String id) {
        return getStickerById(Long.parseLong(id));
    }
}
