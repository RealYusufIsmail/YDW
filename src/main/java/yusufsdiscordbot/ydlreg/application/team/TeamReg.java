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

package yusufsdiscordbot.ydlreg.application.team;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.application.team.Team;
import yusufsdiscordbot.ydl.application.team.TeamMember;
import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;
import yusufsdiscordbot.ydlreg.util.Verify;

import java.util.ArrayList;
import java.util.List;

public class TeamReg implements Team {
    private final JsonNode team;
    private final YDL ydl;

    public TeamReg(JsonNode team, YDL ydl) {
        this.team = team;
        this.ydl = ydl;
    }

    @Override
    public @NotNull String getIcon() {
        var icon = team.get("icon").asText();
        Verify.checkIfNull(icon, "Icon is null");
        return icon;
    }

    @Override
    public @NotNull List<TeamMember> getMembers() {
        return new ArrayList<>(JsonUtils.stream(team.get("members").getAsArrayNode())
            .map(o -> new TeamMemberReg(o.getAsJsonNode(), ydl))
            .toList());
    }

    @Override
    public String getName() {
        return team.get("name").asText();
    }

    @Override
    public @NotNull SnowFlake getOwnerIdLong() {
        return SnowFlake.of(team.get("owner_id").asText());
    }

    /**
     * @return The core long of this api.
     */
    @Override
    public Long getIdLong() {
        return team.get("id").asLong();
    }

    /**
     * Called when the bot is ready.
     *
     * @return The YDL instance.
     */
    @Override
    public @Nullable YDL getYDL() {
        return ydl;
    }
}
