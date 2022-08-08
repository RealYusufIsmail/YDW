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
package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydw.entities.guild.role.RoleTags;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.entities.role.RoleTagsReg;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RoleReg implements Role {
    private final YDW ydw;
    private final long id;

    private final String name;
    private final Integer color;
    private final Boolean hoist;
    private final String icon;
    private final String unicodeEmoji;
    private final Integer position;
    private final Permission[] permissions;
    private final Boolean managed;
    private final Boolean mentionable;
    private final RoleTagsReg tags;

    public RoleReg(@NotNull JsonNode role, YDW ydw, long id) {
        this.ydw = ydw;
        this.id = id;

        this.name = role.hasNonNull("name") ? role.get("name").asText() : null;
        this.color = role.hasNonNull("color") ? role.get("color").asInt() : null;
        this.hoist = role.hasNonNull("hoist") ? role.get("hoist").asBoolean() : null;
        this.icon = role.hasNonNull("icon") ? role.get("icon").asText() : null;
        this.unicodeEmoji =
                role.hasNonNull("unicode_emoji") ? role.get("unicode_emoji").asText() : null;
        this.position = role.hasNonNull("position") ? role.get("position").asInt() : null;
        this.managed = role.hasNonNull("managed") ? role.get("managed").asBoolean() : null;
        this.mentionable =
                role.hasNonNull("mentionable") ? role.get("mentionable").asBoolean() : null;
        this.tags = role.hasNonNull("tags") ? new RoleTagsReg(role.get("tags"), ydw) : null;

        var perms = "permissions";
        if (role.hasNonNull(perms)) {
            this.permissions = new Permission[role.get(perms).size()];
            for (int i = 0; i < role.get(perms).size(); i++) {
                this.permissions[i] = Permission.getPermission(role.get(perms).get(i).asInt());
            } // for
        } else {
            this.permissions = new Permission[0];
        }
    }


    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getColour() {
        return color;
    }

    @Override
    public Boolean isHoisted() {
        return hoist;
    }

    @NotNull
    @Override
    public Optional<String> getIcon() {
        return Optional.ofNullable(icon);
    }

    @NotNull
    @Override
    public Optional<String> getUnicodeEmoji() {
        return Optional.ofNullable(unicodeEmoji);
    }

    @Override
    public Integer getPosition() {
        return position;
    }

    @Override
    public @NotNull Permission[] getPermissions() {
        return permissions;
    }

    @Override
    public Boolean isManaged() {
        return managed;
    }

    @Override
    public Boolean isMentionable() {
        return mentionable;
    }

    @Override
    public @NotNull Optional<RoleTags> getRoleTags() {
        return Optional.ofNullable(tags);
    }
}
