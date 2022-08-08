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
package io.github.realyusufismail.ydw.event.events.invite;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.Application;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.invite.InviteTargetType;
import io.github.realyusufismail.ydw.event.Event;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.Optional;

public class InviteCreateEvent extends Event {

    private final Channel channel;
    private final String code;
    private final ZonedDateTime createdAt;
    private final Guild guild;
    private final User inviter;
    private final int maxAge;
    private final int maxUses;
    private final InviteTargetType targetType;
    private final User targetUser;
    private final Application targetApplication;
    private final boolean isTemporary;
    private final int uses;

    public InviteCreateEvent(YDW ydw, Channel channel, String code, ZonedDateTime createdAt,
            Guild guild, @Nullable User inviter, int maxAge, int maxUses,
            @Nullable InviteTargetType targetType, @Nullable User targetUser,
            @Nullable Application targetApplication, boolean isTemporary, int uses) {
        super(ydw);
        this.channel = channel;
        this.code = code;
        this.createdAt = createdAt;
        this.guild = guild;
        this.inviter = inviter;
        this.maxAge = maxAge;
        this.maxUses = maxUses;
        this.targetType = targetType;
        this.targetUser = targetUser;
        this.targetApplication = targetApplication;
        this.isTemporary = isTemporary;
        this.uses = uses;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getCode() {
        return code;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Guild getGuild() {
        return guild;
    }

    public Optional<User> getInviter() {
        return Optional.ofNullable(inviter);
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public Optional<InviteTargetType> getTargetType() {
        return Optional.ofNullable(targetType);
    }

    public Optional<User> getTargetUser() {
        return Optional.ofNullable(targetUser);
    }

    public Optional<Application> getTargetApplication() {
        return Optional.ofNullable(targetApplication);
    }

    public boolean isTemporary() {
        return isTemporary;
    }

    public int getUses() {
        return uses;
    }
}
