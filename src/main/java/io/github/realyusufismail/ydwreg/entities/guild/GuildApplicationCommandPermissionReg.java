/*
 *
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *
 * distributed under the License is distributed on an "AS IS" BASIS,
 *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 *
 * limitations under the License.
 *
 *
 */

package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.perm.ApplicationCommandPermission;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.guild.GuildApplicationCommandPermission;
import io.github.realyusufismail.ydwreg.application.commands.perm.ApplicationCommandPermissionReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GuildApplicationCommandPermissionReg implements GuildApplicationCommandPermission {
    private final long id;

    private final long applicationId;
    private final Guild guild;
    private final List<ApplicationCommandPermission> permission = new ArrayList<>();

    public GuildApplicationCommandPermissionReg(@NotNull JsonNode json, long id, @NotNull YDW ydw) {
        this.id = id;

        this.applicationId = json.get("application_id").asLong();
        this.guild = ydw.getGuild(json.get("guild_id").asLong());

        JsonNode permissions = json.get("permissions");
        for (JsonNode permission : permissions) {
            this.permission.add(new ApplicationCommandPermissionReg(permission, permission.get("id").asLong()));
        }
    }


    @Override
    public SnowFlake getApplicationId() {
        return SnowFlake.of(applicationId);
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    @Override
    public List<ApplicationCommandPermission> getPermissions() {
        return permission;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
