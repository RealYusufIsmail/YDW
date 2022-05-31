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

import io.github.realyusufismail.ydwreg.entities.user.PremiumTypes;
import io.github.realyusufismail.ydwreg.entities.user.UserFlags;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface User extends SnowFlake, GenericEntity {
    String getUserName();

    String getDiscriminator();

    String getAvatar();

    Optional<Boolean> isBot();

    Optional<Boolean> isSystem();

    Optional<Boolean> isMFAEnabled();

    Optional<String> getBanner();

    Optional<Integer> getAccentColor();

    Optional<String> getLocale();

    Optional<Boolean> isVerified();

    Optional<String> getEmail();

    @NotNull
    Optional<UserFlags> getFlags();

    @NotNull
    Optional<PremiumTypes> getPremiumType();

    @NotNull
    Optional<UserFlags> getPublicFlags();

    List<Guild> getGuilds();

    default void ifPresent(@NotNull Consumer<User> consumer) {
        consumer.accept(this);
    }
}
