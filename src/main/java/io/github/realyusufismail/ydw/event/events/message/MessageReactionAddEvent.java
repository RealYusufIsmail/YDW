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
package io.github.realyusufismail.ydw.event.events.message;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.event.Event;
import org.jetbrains.annotations.Nullable;

public class MessageReactionAddEvent extends Event {
    private final User user;
    private final Channel channel;
    private final Message message;
    private final Guild guild;
    private final Member member;

    public MessageReactionAddEvent(YDW ydw, User user, Channel channel, Message message,
            @Nullable Guild guild, @Nullable Member member) {
        super(ydw);
        this.user = user;
        this.channel = channel;
        this.message = message;
        this.guild = guild;
        this.member = member;
    }

    public User getUser() {
        return user;
    }

    public Channel getChannel() {
        return channel;
    }

    public Message getMessage() {
        return message;
    }

    @Nullable
    public Guild getGuild() {
        return guild;
    }

    @Nullable
    public Member getMember() {
        return member;
    }
}
