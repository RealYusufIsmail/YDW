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

package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.guild.role.RoleTags;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Optional;

public interface Role extends SnowFlake, GenericEntity {
    int DEFAULT_COLOR_RAW = Color.darkGray.getRGB();

    String getName();

    Integer getColour();

    Boolean isHoisted();

    Optional<String> getIcon();

    Optional<String> getUnicodeEmoji();

    Integer getPosition();

    @NotNull
    Permission[] getPermissions();

    Boolean isManaged();

    Boolean isMentionable();

    @NotNull
    Optional<RoleTags> getRoleTags();
}
