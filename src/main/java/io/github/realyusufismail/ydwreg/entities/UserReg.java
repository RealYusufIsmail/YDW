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
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.user.PremiumTypes;
import io.github.realyusufismail.ydwreg.entities.user.UserFlags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserReg implements User {
    private final YDW ydw;
    private final long id;

    private final String username;
    private final String discriminator;
    private final String avatar;
    @NotNull
    private final Boolean isBot;
    @NotNull
    private final Boolean isSystem;
    @NotNull
    private final Boolean isMfaEnabled;
    private final String banner;
    private final Integer accentColor;
    private final String locale;
    @NotNull
    private final Boolean isVerified;
    private final String email;
    @Nullable
    private final UserFlags flags;
    @Nullable
    private final PremiumTypes premiumType;
    @Nullable
    private final UserFlags publicFlags;
    private final List<Guild> guilds = new ArrayList<>();

    public UserReg(@NotNull JsonNode user, long userId, @NotNull YDW ydw) {
        this.ydw = ydw;
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
        guilds.addAll(ydw.getRest().getUseCaller().getGuilds());
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

    @NotNull
    @Override
    public Optional<Boolean> isBot() {
        return Optional.ofNullable(isBot);
    }

    @NotNull
    @Override
    public Optional<Boolean> isSystem() {
        return Optional.ofNullable(isSystem);
    }

    @NotNull
    @Override
    public Optional<Boolean> isMFAEnabled() {
        return Optional.ofNullable(isMfaEnabled);
    }

    @Override
    public @NotNull Optional<String> getBanner() {
        return Optional.ofNullable(banner);
    }

    @NotNull
    @Override
    public Optional<Integer> getAccentColor() {
        return Optional.of(Color.decode("#" + accentColor).getRGB());
    }

    @NotNull
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

    @NotNull
    @Override
    public List<Guild> getGuilds() {
        return guilds;
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
    public YDWReg getYDW() {
        return (YDWReg) ydw;
    }
}
