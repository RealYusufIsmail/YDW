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

package yusufsdiscordbot.ydl.entities.guild;

import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.entities.GenericEntity;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydl.perm.Permission;
import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface Member extends SnowFlake, GenericEntity {
    Optional<User> getUser();

    Optional<String> getNickname();

    Optional<String> getAvatar();

    @NotNull
    List<Role> getRoles();

    ZonedDateTime getJoinedAt();

    Optional<ZonedDateTime> getPremiumSince();

    Boolean isDeafened();

    Boolean isMuted();

    Optional<Boolean> isPending();

    String getPermissions();

    Optional<ZonedDateTime> getTimeoutEnd();

    Boolean memberHasPermission(Permission permission);
}
