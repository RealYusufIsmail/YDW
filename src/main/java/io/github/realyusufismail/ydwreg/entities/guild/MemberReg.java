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
            
package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MemberReg implements Member {
    private final YDW ydw;

    @Nullable
    private final User user;
    private final String nick;
    private final String avatar;
    private final List<Role> roles = new ArrayList<>();
    private final ZonedDateTime joinedAt;
    private final ZonedDateTime premiumSince;
    @NotNull
    private final Boolean deaf;
    @NotNull
    private final Boolean mute;
    private final Boolean pending;
    private final String permissions;
    private final ZonedDateTime timedOutUntil;


    public MemberReg(@NotNull JsonNode member, @NotNull YDW ydw) {
        this.ydw = ydw;

        this.user = member.hasNonNull("user")
                ? new UserReg(member.get("user"), member.get("user").get("id").asLong(), ydw)
                : null;
        this.nick = member.hasNonNull("nick") ? member.get("nick").asText() : null;
        this.avatar = member.hasNonNull("avatar") ? member.get("avatar").asText() : null;
        this.joinedAt = ZonedDateTime.parse(member.get("joined_at").asText());
        this.premiumSince = member.hasNonNull("premium_since")
                ? ZonedDateTime.parse(member.get("premium_since").asText())
                : null;
        this.deaf = member.get("deaf").asBoolean();
        this.mute = member.get("mute").asBoolean();
        this.pending = member.hasNonNull("pending") ? member.get("pending").asBoolean() : null;
        this.permissions =
                member.hasNonNull("permissions") ? member.get("permissions").asText() : null;
        this.timedOutUntil = member.hasNonNull("timed_out_until")
                ? ZonedDateTime.parse(member.get("timed_out_until").asText())
                : null;

        if (member.hasNonNull("roles")) {
            for (JsonNode role : member.get("roles")) {
                roles.add(new RoleReg(role, ydw, role.asLong()));
            }
        }
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return user.getIdLong();
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @NotNull
    @Override
    public Optional<User> getUser() {
        return Optional.ofNullable(user);
    }

    @NotNull
    @Override
    public Optional<String> getNickname() {
        return Optional.ofNullable(nick);
    }

    @NotNull
    @Override
    public Optional<String> getAvatar() {
        return Optional.ofNullable(avatar);
    }

    @Override
    public @NotNull List<Role> getRoles() {
        return roles;
    }

    @Override
    public ZonedDateTime getJoinedAt() {
        return joinedAt;
    }

    @NotNull
    @Override
    public Optional<ZonedDateTime> getPremiumSince() {
        return Optional.ofNullable(premiumSince);
    }

    @Override
    public Boolean isDeafened() {
        return deaf;
    }

    @Override
    public Boolean isMuted() {
        return mute;
    }

    @NotNull
    @Override
    public Optional<Boolean> isPending() {
        return Optional.ofNullable(pending);
    }

    @Override
    public String getPermissions() {
        return permissions;
    }

    @NotNull
    @Override
    public Optional<ZonedDateTime> getTimeoutEnd() {
        return Optional.ofNullable(timedOutUntil);
    }

    @NotNull
    @Override
    public Boolean memberHasPermission(@NotNull Permission permission) {
        return getPermissions().contains(permission.getValueAsString());
    }
}
