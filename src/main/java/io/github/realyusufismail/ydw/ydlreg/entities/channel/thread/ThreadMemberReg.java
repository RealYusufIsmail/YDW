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

package io.github.realyusufismail.ydw.ydlreg.entities.channel.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.User;
import io.github.realyusufismail.ydw.ydl.entities.guild.channel.threads.ThreadMember;
import io.github.realyusufismail.ydw.ydlreg.Flags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.Optional;

public class ThreadMemberReg implements ThreadMember {
    @NotNull
    private final Long id;
    private final YDL ydl;

    @Nullable
    private final User user;
    private final ZonedDateTime joinedAt;
    private final Flags[] flags;


    public ThreadMemberReg(@NotNull JsonNode json, long id, @NotNull YDL ydl) {
        this.id = id;
        this.ydl = ydl;

        this.user = json.hasNonNull("user_id") ? ydl.getUser(json.get("user_id").asLong()) : null;
        this.joinedAt = ZonedDateTime.parse(json.get("join_timestamp").asText());
        this.flags = Flags.getFlags(json.get("flags").asInt());
    }


    /**
     * @return The core long of this api.
     */
    @NotNull
    @Override
    public Long getIdLong() {
        return Optional.ofNullable(id).orElse(0L);
    }

    @Override
    public YDL getYDL() {
        return ydl;
    }

    @Override
    public @NotNull Optional<User> getUser() {
        return Optional.ofNullable(user);
    }

    @Override
    public ZonedDateTime getJoinTimeStamp() {
        return joinedAt;
    }

    @Override
    public Flags[] getFlags() {
        return flags;
    }
}