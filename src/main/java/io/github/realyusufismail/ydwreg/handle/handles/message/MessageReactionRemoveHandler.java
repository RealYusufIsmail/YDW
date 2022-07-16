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
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.emoji.Emoji;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.event.events.message.MessageReactionRemoveEvent;
import io.github.realyusufismail.ydwreg.entities.emoji.EmojiReg;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydwreg.handle.Handle;

import java.util.Optional;

public class MessageReactionRemoveHandler extends Handle {

    public MessageReactionRemoveHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        User user = ydw.getUser(json.get("user_id").asLong());
        Channel channel = ydw.getChannel(json.get("channel_id").asLong());
        Message message = channel.getMessage(json.get("message_id").asLong());
        Optional<Guild> guild = Optional.ofNullable(ydw.getGuild(json.get("guild_id").asLong()));
        Optional<Member> member = Optional.ofNullable(new MemberReg(json.get("member"), ydw));
        Emoji emoji = new EmojiReg(json.get("emoji"), json.get("emoji").get("id").asLong(), ydw);

        ydw.handelEvent(new MessageReactionRemoveEvent(ydw, channel, message, guild.get(), user,
                emoji, member.get()));
    }
}
