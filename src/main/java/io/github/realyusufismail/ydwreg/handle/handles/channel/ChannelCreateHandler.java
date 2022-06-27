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
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.channel.VoiceChannel;
import io.github.realyusufismail.ydw.event.events.channel.ChannelCreateEvent;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.*;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class ChannelCreateHandler extends Handle {
    public ChannelCreateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        ChannelType channelType = ChannelType.getChannelType(json.get("type").asInt());

        Channel channel = createChannel(channelType, json);

        ydw.handelEvent(new ChannelCreateEvent(ydw, channel));
    }

    private Channel createChannel(ChannelType channelType, JsonNode json) {
        switch (channelType) {
            case GUILD_TEXT : new TextChannelReg(json, json.get("id").asLong(), ydw);
            case GUILD_VOICE : new VoiceChannelReg(json, json.get("id").asLong(), ydw);
            case GUILD_CATEGORY : new CategoryReg(json, json.get("id").asLong(), ydw);
            case GUILD_NEWS : new NewsChannelReg(json, json.get("id").asLong(), ydw);
            case GUILD_STAGE_VOICE : new StageChannelReg(json, json.get("id").asLong(), ydw);
            default :
                return null;
        }
    }
}
