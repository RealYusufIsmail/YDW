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

package io.github.realyusufismail.ydw.ydlreg.entities.emoji;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.User;
import io.github.realyusufismail.ydw.ydl.entities.emoji.Emoji;
import io.github.realyusufismail.ydw.ydl.entities.guild.Role;
import io.github.realyusufismail.ydw.ydlreg.entities.guild.RoleReg;
import io.github.realyusufismail.ydw.ydlreg.entities.UserReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmojiReg implements Emoji {
    private final YDL ydl;
    private final long id;

    private final String name;
    private final List<Role> roles = new ArrayList<>();
    @Nullable
    private final User user;
    private final Boolean requireColons;
    private final Boolean managed;
    private final Boolean animated;
    private final Boolean available;

    public EmojiReg(@NotNull JsonNode emoji, long id, @NotNull YDL ydl) {
        this.ydl = ydl;
        this.id = id;

        this.name = emoji.hasNonNull("name") ? emoji.get("name").asText() : null;
        this.user = emoji.hasNonNull("user")
                ? new UserReg(emoji.get("user"), emoji.get("user").get("id").asLong(), ydl)
                : null;
        this.requireColons =
                emoji.hasNonNull("require_colons") ? emoji.get("require_colons").asBoolean() : null;
        this.managed = emoji.hasNonNull("managed") ? emoji.get("managed").asBoolean() : null;
        this.animated = emoji.hasNonNull("animated") ? emoji.get("animated").asBoolean() : null;
        this.available = emoji.hasNonNull("available") ? emoji.get("available").asBoolean() : null;

        if (emoji.hasNonNull("roles")) {
            for (JsonNode role : emoji.get("roles")) {
                this.roles.add(new RoleReg(role, ydl, role.get("id").asLong()));
            }
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

    @NotNull
    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @Override
    public @NotNull List<Role> getRoles() {
        return roles;
    }

    @Override
    public @NotNull Optional<User> getUser() {
        return Optional.ofNullable(user);
    }

    @NotNull
    @Override
    public Optional<Boolean> requiresColons() {
        return Optional.ofNullable(requireColons);
    }

    @NotNull
    @Override
    public Optional<Boolean> isManaged() {
        return Optional.ofNullable(managed);
    }

    @NotNull
    @Override
    public Optional<Boolean> isAnimated() {
        return Optional.ofNullable(animated);
    }

    @NotNull
    @Override
    public Optional<Boolean> isAvailable() {
        return Optional.ofNullable(available);
    }
}
