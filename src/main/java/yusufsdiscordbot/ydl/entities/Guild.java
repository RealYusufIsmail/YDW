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

package yusufsdiscordbot.ydl.entities;


import com.google.errorprone.annotations.CheckReturnValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yusufsdiscordbot.ydl.action.Action;
import yusufsdiscordbot.ydl.entities.emoji.Emoji;
import yusufsdiscordbot.ydl.entities.guild.*;
import yusufsdiscordbot.ydl.entities.sticker.Sticker;
import yusufsdiscordbot.ydl.entities.voice.VoiceState;
import yusufsdiscordbot.ydlreg.entities.guild.GuildFeatures;
import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

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
    default Sticker getStickerById(String id) {
        return getStickerById(Long.parseLong(id));
    }
}
