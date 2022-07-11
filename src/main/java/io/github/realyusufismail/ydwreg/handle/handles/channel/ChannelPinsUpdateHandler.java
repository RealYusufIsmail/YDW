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

        Guild guild = json.hasNonNull("guild_id") ? ydw.getGuild(json.get("guild_id").asLong()) : null;

        Channel channel = json.hasNonNull("channel_id") ? ydw.getChannel(json.get("channel_id").asLong()) : null;

        ZonedDateTime lastPinTime = json.hasNonNull("last_pin_timestamp") ? ZonedDateTime.parse(json.get("last_pin_timestamp").asText()) : null;

        ydw.handelEvent(new ChannelPinsUpdateEvent(ydw, guild, channel, lastPinTime));
    }
}
