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
package io.github.realyusufismail.ydwreg.message_components.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.interaction.InteractionType;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.interaction.MessageInteraction;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MessageInteractionReg implements MessageInteraction {
    private final YDW ydw;
    private final long id;

    private final InteractionType type;
    private final String name;
    private final User user;
    private final Member member;

    public MessageInteractionReg(JsonNode message, long id, YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.type = InteractionType.getValue(message.get("type").asInt());
        this.name = message.get("name").asText();
        this.user = new UserReg(message.get("user"), message.get("user").get("id").asLong(), ydw);
        this.member =
                message.hasNonNull("member") ? new MemberReg(message.get("member"), ydw) : null;
    }

    @Nullable
    @Override
    public YDW getYDW() {
        return ydw;
    }

    @Override
    public InteractionType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Optional<Member> getMember() {
        return Optional.ofNullable(member);
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
