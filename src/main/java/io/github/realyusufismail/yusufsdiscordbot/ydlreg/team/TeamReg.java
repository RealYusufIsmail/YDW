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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.team;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.team.Team;
import io.github.realyusufismail.yusufsdiscordbot.ydl.team.TeamMember;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamReg implements Team {

    private final long id;
    private final YDL ydl;

    private final String icon;
    private final List<TeamMember> members = new ArrayList<>();
    private final String name;
    private final Long ownerId;

    public TeamReg(JsonNode team, long id, YDL ydl) {
        this.id = id;
        this.ydl = ydl;

        this.icon = team.get("icon").asText();
        this.name = team.get("name").asText();
        this.ownerId = team.get("owner_id").asLong();

        for (JsonNode member : team.get("members")) {
            this.members.add(new TeamMemberReg(member, ydl));
        }
    }


    @Override
    public YDL getYDL() {
        return ydl;
    }

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

    @Override
    public Long getIdLong() {
        return id;
    }
}
