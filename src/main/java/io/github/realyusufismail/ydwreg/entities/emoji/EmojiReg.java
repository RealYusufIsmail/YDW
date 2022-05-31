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

package io.github.realyusufismail.ydwreg.entities.emoji;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.emoji.Emoji;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.entities.guild.RoleReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmojiReg implements Emoji {
    private final YDW ydw;
    private final long id;

    private final String name;
    private final List<Role> roles = new ArrayList<>();
    @Nullable
    private final User user;
    private final Boolean requireColons;
    private final Boolean managed;
    private final Boolean animated;
    private final Boolean available;

    public EmojiReg(@NotNull JsonNode emoji, long id, @NotNull YDW ydw) {
        this.ydw = ydw;
        this.id = id;

        this.name = emoji.hasNonNull("name") ? emoji.get("name").asText() : null;
        this.user = emoji.hasNonNull("user")
                ? new UserReg(emoji.get("user"), emoji.get("user").get("id").asLong(), ydw)
                : null;
        this.requireColons =
                emoji.hasNonNull("require_colons") ? emoji.get("require_colons").asBoolean() : null;
        this.managed = emoji.hasNonNull("managed") ? emoji.get("managed").asBoolean() : null;
        this.animated = emoji.hasNonNull("animated") ? emoji.get("animated").asBoolean() : null;
        this.available = emoji.hasNonNull("available") ? emoji.get("available").asBoolean() : null;

        if (emoji.hasNonNull("roles")) {
            for (JsonNode role : emoji.get("roles")) {
                this.roles.add(new RoleReg(role, ydw, role.get("id").asLong()));
            }
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
