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

package io.github.realyusufismail.ydw.ydlreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.User;
import io.github.realyusufismail.ydw.ydl.entities.guild.Member;
import io.github.realyusufismail.ydw.ydl.entities.guild.Role;
import io.github.realyusufismail.ydw.ydl.perm.Permission;
import io.github.realyusufismail.ydw.ydlreg.entities.UserReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MemberReg implements Member {
    private final YDL ydl;

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


    public MemberReg(@NotNull JsonNode member, @NotNull YDL ydl) {
        this.ydl = ydl;

        this.user = member.hasNonNull("user")
                ? new UserReg(member.get("user"), member.get("user").get("id").asLong(), ydl)
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
                roles.add(new RoleReg(role, ydl, role.get("id").asLong()));
            }
        }
    }

    @Override
    public Long getIdLong() {
        return user.getIdLong();
    }

    @Override
    public YDL getYDL() {
        return ydl;
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
