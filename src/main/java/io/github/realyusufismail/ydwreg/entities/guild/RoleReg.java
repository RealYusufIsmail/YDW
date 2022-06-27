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
