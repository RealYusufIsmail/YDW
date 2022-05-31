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

package io.github.realyusufismail.ydwreg.entities.channel.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMember;
import io.github.realyusufismail.ydwreg.Flags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.Optional;

public class ThreadMemberReg implements ThreadMember {
    @NotNull
    private final Long id;
    private final YDW ydw;

    @Nullable
    private final User user;
    private final ZonedDateTime joinedAt;
    private final Flags[] flags;


    public ThreadMemberReg(@NotNull JsonNode json, long id, @NotNull YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.user = json.hasNonNull("user_id") ? ydw.getUser(json.get("user_id").asLong()) : null;
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
    public YDW getYDW() {
        return ydw;
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
