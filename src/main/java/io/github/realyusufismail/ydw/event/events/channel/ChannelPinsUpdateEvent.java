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
package io.github.realyusufismail.ydw.event.events.channel;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.event.Event;

import java.time.ZonedDateTime;

public class ChannelPinsUpdateEvent extends Event {
    private final Guild guild;
    private final Channel channel;
    private final ZonedDateTime lastPinTime;

    public ChannelPinsUpdateEvent(YDW ydw, Guild guild, Channel channel,
            ZonedDateTime lastPinTime) {
        super(ydw);
        this.guild = guild;
        this.channel = channel;
        this.lastPinTime = lastPinTime;
    }

    public Guild getGuild() {
        return guild;
    }

    public Channel getChannel() {
        return channel;
    }

    public ZonedDateTime getLastPinTime() {
        return lastPinTime;
    }
}
