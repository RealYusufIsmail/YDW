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

import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydlreg.entities.user.PremiumTypes;
import yusufsdiscordbot.ydlreg.entities.user.UserFlags;

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
