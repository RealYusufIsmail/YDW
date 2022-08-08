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
package io.github.realyusufismail.ydwreg.handle.handles.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.event.events.channel.ChannelPinsUpdateEvent;
import io.github.realyusufismail.ydwreg.handle.Handle;

import java.time.ZonedDateTime;

public class ChannelPinsUpdateHandler extends Handle {
    public ChannelPinsUpdateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {

        Guild guild =
                json.hasNonNull("guild_id") ? ydw.getGuild(json.get("guild_id").asLong()) : null;

        Channel channel =
                json.hasNonNull("channel_id") ? ydw.getChannel(json.get("channel_id").asLong())
                        : null;

        ZonedDateTime lastPinTime = json.hasNonNull("last_pin_timestamp")
                ? ZonedDateTime.parse(json.get("last_pin_timestamp").asText())
                : null;

        ydw.handelEvent(new ChannelPinsUpdateEvent(ydw, guild, channel, lastPinTime));
    }
}
