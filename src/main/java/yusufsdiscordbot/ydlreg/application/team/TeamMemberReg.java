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

import api.ydl.client.object.MembershipState;
import api.ydl.snowflake.SnowFlake;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.application.team.TeamMember;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydlreg.entities.UserReg;
import yusufsdiscordbot.ydlreg.json.JsonUtils;

import java.util.List;

public class TeamMemberReg implements TeamMember {
    private final JsonNode team;
    private final YDL ydl;

    public TeamMemberReg(JsonNode team, YDL ydl) {
        this.team = team;
        this.ydl = ydl;
    }

    @Override
    public @NotNull MembershipState getMembershipState() {
        return MembershipState.fromValue(team.get("membership_state").asInt());
    }

    @Override
    public @NotNull List<String> getPermissions() {
        return JsonUtils.stream(team.get("permissions").getAsArrayNode())
            .map(Object::toString)
            .toList();
    }

    @Override
    public @NotNull SnowFlake getTeamIdLong() {
        return SnowFlake.of(team.get("team_id").asText());
    }

    @Override
    public @NotNull User getUser() {
        return new UserReg(team.getAsJsonNode("user"), ydl);
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
