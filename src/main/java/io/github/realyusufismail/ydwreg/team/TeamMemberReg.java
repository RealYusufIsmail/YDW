/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
