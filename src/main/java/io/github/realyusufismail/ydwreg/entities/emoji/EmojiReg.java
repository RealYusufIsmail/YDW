/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
