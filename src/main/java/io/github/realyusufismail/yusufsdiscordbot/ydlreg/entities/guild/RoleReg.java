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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Role;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.role.RoleTags;
import io.github.realyusufismail.yusufsdiscordbot.ydl.perm.Permission;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.role.RoleTagsReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RoleReg implements Role {
    private final YDL ydl;
    private final long id;

    private final String name;
    @NotNull
    private final Integer color;
    @NotNull
    private final Boolean hoist;
    private final String icon;
    private final String unicodeEmoji;
    @NotNull
    private final Integer position;
    @NotNull
    private final Permission[] permissions;
    @NotNull
    private final Boolean managed;
    @NotNull
    private final Boolean mentionable;
    @Nullable
    private final RoleTagsReg tags;

    public RoleReg(@NotNull JsonNode role, YDL ydl, long id) {
        this.ydl = ydl;
        this.id = id;

        this.name = role.get("name").asText();
        this.color = role.get("color").asInt();
        this.hoist = role.get("hoist").asBoolean();
        this.icon = role.hasNonNull("icon") ? role.get("icon").asText() : null;
        this.unicodeEmoji =
                role.hasNonNull("unicode_emoji") ? role.get("unicode_emoji").asText() : null;
        this.position = role.get("position").asInt();
        this.managed = role.get("managed").asBoolean();
        this.mentionable = role.get("mentionable").asBoolean();
        this.tags = role.hasNonNull("tags") ? new RoleTagsReg(role.get("tags"), ydl) : null;

        var perms = "permissions";
        if (role.has(perms)) {
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
    public YDL getYDL() {
        return ydl;
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
