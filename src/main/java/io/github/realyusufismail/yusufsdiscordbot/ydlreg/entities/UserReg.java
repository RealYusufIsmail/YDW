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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.Guild;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.User;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.YDLReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.user.PremiumTypes;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.user.UserFlags;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserReg implements User {
    private final YDL ydl;
    private final long id;

    private final String username;
    private final String discriminator;
    private final String avatar;
    private final Boolean isBot;
    private final Boolean isSystem;
    private final Boolean isMfaEnabled;
    private final String banner;
    private final Integer accentColor;
    private final String locale;
    private final Boolean isVerified;
    private final String email;
    private final UserFlags flags;
    private final PremiumTypes premiumType;
    private final UserFlags publicFlags;
    private final List<Guild> guilds = new ArrayList<>();

    public UserReg(@NotNull JsonNode user, long userId, YDL ydl) {
        this.ydl = ydl;
        this.id = userId;

        this.username = user.get("username").asText();
        this.discriminator = user.get("discriminator").asText();
        this.avatar = user.get("avatar").asText();
        this.isBot = user.hasNonNull("bot") && user.get("bot").asBoolean();
        this.isSystem = user.hasNonNull("system") && user.get("system").asBoolean();
        this.isMfaEnabled = user.hasNonNull("mfa_enabled") && user.get("mfa_enabled").asBoolean();
        this.banner = user.hasNonNull("banner") ? user.get("banner").asText() : null;
        this.accentColor =
                user.hasNonNull("accent_color") ? user.get("accent_color").asInt() : null;
        this.locale = user.hasNonNull("locale") ? user.get("locale").asText() : null;
        this.isVerified = user.hasNonNull("verified") && user.get("verified").asBoolean();
        this.email = user.hasNonNull("email") ? user.get("email").asText() : null;
        this.flags = user.hasNonNull("flags") ? UserFlags.getFlag(user.get("flags").asInt()) : null;
        this.premiumType = user.hasNonNull("premium_type")
                ? PremiumTypes.getNitroType(user.get("premium_type").asInt())
                : null;
        this.publicFlags = user.hasNonNull("public_flags")
                ? UserFlags.getFlag(user.get("public_flags").asInt())
                : null;
        guilds.addAll(ydl.getRest().getUseCaller().getGuilds());
    }


    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getDiscriminator() {
        return discriminator;
    }

    @Override
    public @NotNull String getAvatar() {
        return avatar;
    }

    @Override
    public Optional<Boolean> isBot() {
        return Optional.ofNullable(isBot);
    }

    @Override
    public Optional<Boolean> isSystem() {
        return Optional.ofNullable(isSystem);
    }

    @Override
    public Optional<Boolean> isMFAEnabled() {
        return Optional.ofNullable(isMfaEnabled);
    }

    @Override
    public @NotNull Optional<String> getBanner() {
        return Optional.ofNullable(banner);
    }

    @Override
    public Optional<Integer> getAccentColor() {
        return Optional.of(Color.decode("#" + accentColor).getRGB());
    }

    @Override
    public Optional<String> getLocale() {
        return Optional.ofNullable(locale);
    }

    @Override
    public Optional<Boolean> isVerified() {
        return Optional.ofNullable(isVerified);
    }

    @Override
    public @NotNull Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    @Override
    public @NotNull Optional<UserFlags> getFlags() {
        return Optional.ofNullable(flags);
    }

    @Override
    public @NotNull Optional<PremiumTypes> getPremiumType() {
        return Optional.ofNullable(premiumType);
    }

    @Override
    public @NotNull Optional<UserFlags> getPublicFlags() {
        return Optional.ofNullable(publicFlags);
    }

    @Override
    public List<Guild> getGuilds() {
        return guilds;
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
