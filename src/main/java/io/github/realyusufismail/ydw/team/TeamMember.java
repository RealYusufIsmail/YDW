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
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TeamMember extends GenericEntity {
    /**
     * The state of the member's membership in the team.
     *
     * @return The state of the member's membership in the team.
     */
    @NotNull
    MembershipState getMembershipState();

    /**
     * The member's permissions in the team.
     *
     * @return The member's permissions in the team.
     */
    List<String> getPermissions();

    /**
     * The id of the parent team.
     *
     * @return The id of the parent team.
     */
    @NotNull
    SnowFlake getTeamId();

    /**
     * The user that is a member of the team.
     *
     * @return The user that is a member of the team.
     */
    @NotNull
    User getUser();
}
