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
package io.github.realyusufismail.ydwreg.handle.handles.invite;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.Application;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.invite.InviteTargetType;
import io.github.realyusufismail.ydw.event.events.invite.InviteCreateEvent;
import io.github.realyusufismail.ydwreg.application.ApplicationReg;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.handle.Handle;

import java.time.ZonedDateTime;
import java.util.Optional;

public class InviteCreateHandler extends Handle {

    public InviteCreateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        Channel channel = ydw.getChannel(json.get("channel_id").asLong());
        String code = json.get("code").asText();
        ZonedDateTime createdAt = ZonedDateTime.parse(json.get("created_at").asText());
        Guild guild = ydw.getGuild(json.get("guild_id").asLong());
        Optional<User> inviter = Optional
            .ofNullable(new UserReg(json.get("inviter"), json.get("inviter").asLong(), ydw));
        int maxAge = json.get("max_age").asInt();
        int maxUses = json.get("max_uses").asInt();
        Optional<InviteTargetType> targetType =
                Optional.ofNullable(InviteTargetType.fromValue(json.get("target_type").asInt()));
        Optional<User> targetUser = Optional.ofNullable(
                new UserReg(json.get("target_user"), json.get("target_user").asLong(), ydw));
        Optional<Application> targetApplication =
                Optional.ofNullable(new ApplicationReg(json.get("target_application"),
                        json.get("target_application").asLong(), ydw));
        boolean isTemporary = json.get("temporary").asBoolean();
        int uses = json.get("uses").asInt();

        ydw.handelEvent(new InviteCreateEvent(ydw, channel, code, createdAt, guild, inviter.get(),
                maxAge, maxUses, targetType.get(), targetUser.get(), targetApplication.get(),
                isTemporary, uses));
    }
}
