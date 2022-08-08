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
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class InviteDeleteEvent extends Event {
    private Channel channel;
    private Guild guild;
    private String code;

    public InviteDeleteEvent(YDW ydw, Channel channel, @Nullable Guild guild, String code) {
        super(ydw);
        this.channel = channel;
        this.guild = guild;
        this.code = code;
    }

    public Channel getChannel() {
        return channel;
    }

    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    public String getCode() {
        return code;
    }
}
