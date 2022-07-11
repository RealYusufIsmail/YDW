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

package io.github.realyusufismail.ydw.team;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface Team extends SnowFlake, GenericEntity {

    /**
     * Gets the hash icon of the team.
     *
     * @return The hash icon of the team.
     */
    Optional<String> getIcon();

    /**
     * Gets the list of members who are in the team.
     *
     * @return The list of members who are in the team. If the list is empty, the team is empty.
     */
    @NotNull
    List<TeamMember> getMembers();

    /**
     * Gets the name of the team.
     *
     * @return The name of the team.
     */
    String getName();

    /**
     * Gets the id of the owner of the team.
     *
     * @return The id of the owner of the team.
     */
    @NotNull
    SnowFlake getOwnerId();
}
