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
package io.github.realyusufismail.ydwreg.handle.handles.message;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.Mentions;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.event.events.message.MessageCreateEvent;
import io.github.realyusufismail.ydwreg.entities.MentionsReg;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydwreg.entities.guild.MessageReg;
import io.github.realyusufismail.ydwreg.handle.Handle;

import java.util.Optional;

public class MessageCreateHandler extends Handle {

    public MessageCreateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        Message message = new MessageReg(json, json.get("id").asLong(), ydw);

        // will be null if message is ephemeral
        Optional<Guild> guild = Optional.of(ydw.getGuild(json.get("guild_id").asLong()));

        Optional<Member> member = Optional.of(new MemberReg(json.get("member"), ydw));

        Mentions mentions = new MentionsReg(json.get("mentions"), ydw);

        ydw.handelEvent(new MessageCreateEvent(ydw, message, guild.get(), member.get(), mentions));
    }
}
