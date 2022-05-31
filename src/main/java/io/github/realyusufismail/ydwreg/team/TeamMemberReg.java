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

package io.github.realyusufismail.ydwreg.team;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.team.MembershipState;
import io.github.realyusufismail.ydw.team.TeamMember;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TeamMemberReg implements TeamMember {
    private final YDW ydw;

    @NotNull
    private final Integer membershipState;
    private final List<String> permissions = new ArrayList<>();
    @NotNull
    private final Long teamId;
    @NotNull
    private final User user;

    public TeamMemberReg(@NotNull JsonNode team, @NotNull YDW ydw) {
        this.ydw = ydw;

        this.membershipState = team.get("membership_state").asInt();
        this.teamId = team.get("team_id").asLong();
        this.user = new UserReg(team.get("user"), team.get("user").get("id").asLong(), ydw);

        for (JsonNode permission : team.get("permissions")) {
            this.permissions.add(permission.asText());
        }
    }


    @Override
    public YDW getYDW() {
        return ydw;
    }

    @NotNull
    @Override
    public MembershipState getMembershipState() {
        return MembershipState.getMembershipState(membershipState);
    }

    @NotNull
    @Override
    public List<String> getPermissions() {
        return permissions;
    }

    @NotNull
    @Override
    public SnowFlake getTeamId() {
        return SnowFlake.of(teamId);
    }

    @NotNull
    @Override
    public User getUser() {
        return user;
    }
}
