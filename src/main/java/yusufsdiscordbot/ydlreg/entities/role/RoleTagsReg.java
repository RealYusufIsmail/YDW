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

package yusufsdiscordbot.ydlreg.entities.role;

import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.guild.role.RoleTags;

public class RoleTagsReg implements RoleTags {
    private final JsonNode roles;
    private final YDL ydl;

    public RoleTagsReg(JsonNode roles, YDL ydl) {
        this.roles = roles;
        this.ydl = ydl;
    }

    @Override
    public @NotNull SnowFlake getBotIdLong() {
        return SnowFlake.of(roles.get("bot_id").asText());
    }

    @Override
    public @NotNull SnowFlake getIntegrationIdLong() {
        return SnowFlake.of(roles.get("integration_id").asText());
    }

    @Override
    public boolean isPremiumSubscriberRole() {
        return JsonNull.INSTANCE.isJsonNull();
    }

    @Override
    public YDL getYDL() {
        return ydl;
    }
}
