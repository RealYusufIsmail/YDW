/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import io.github.realyusufismail.ydw.team.Team;
import io.github.realyusufismail.ydw.team.TeamMember;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamReg implements Team {

    private final long id;
    private final YDW ydw;

    private final String icon;
    private final List<TeamMember> members = new ArrayList<>();
    private final String name;
    @NotNull
    private final Long ownerId;

    public TeamReg(@NotNull JsonNode team, long id, YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.icon = team.get("icon").asText();
        this.name = team.get("name").asText();
        this.ownerId = team.get("owner_id").asLong();

        for (JsonNode member : team.get("members")) {
            this.members.add(new TeamMemberReg(member, ydw));
        }
    }


    @Override
    public YDW getYDW() {
        return ydw;
    }

    @NotNull
    @Override
    public Optional<String> getIcon() {
        return Optional.ofNullable(icon);
    }

    @NotNull
    @Override
    public List<TeamMember> getMembers() {
        return members;
    }

    @Override
    public String getName() {
        return name;
    }

    @NotNull
    @Override
    public SnowFlake getOwnerId() {
        return SnowFlake.of(ownerId);
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
