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

package io.github.realyusufismail.ydw.ydlreg.team;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.User;
import io.github.realyusufismail.ydw.ydl.team.MembershipState;
import io.github.realyusufismail.ydw.ydl.team.TeamMember;
import io.github.realyusufismail.ydw.ydlreg.entities.UserReg;
import io.github.realyusufismail.ydw.ydlreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TeamMemberReg implements TeamMember {
    private final YDL ydl;

    @NotNull
    private final Integer membershipState;
    private final List<String> permissions = new ArrayList<>();
    @NotNull
    private final Long teamId;
    @NotNull
    private final User user;

    public TeamMemberReg(@NotNull JsonNode team, @NotNull YDL ydl) {
        this.ydl = ydl;

        this.membershipState = team.get("membership_state").asInt();
        this.teamId = team.get("team_id").asLong();
        this.user = new UserReg(team.get("user"), team.get("user").get("id").asLong(), ydl);

        for (JsonNode permission : team.get("permissions")) {
            this.permissions.add(permission.asText());
        }
    }


    @Override
    public YDL getYDL() {
        return ydl;
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
