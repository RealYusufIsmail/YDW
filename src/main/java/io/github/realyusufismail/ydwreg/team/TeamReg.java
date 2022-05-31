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
