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
package io.github.realyusufismail.ydwreg.handle.handles.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.channel.*;
import io.github.realyusufismail.ydw.event.events.channel.ChannelDeleteEvent;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.StageChannelReg;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class ChannelDeleteHandler extends Handle {
    public ChannelDeleteHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        ChannelType channelType = ChannelType.getChannelType(json.get("type").asInt());
        long guildId = 0;

        if (channelType.isGuild()) {
            guildId = json.get("guild_id").asLong();
        }

        long channelId = json.get("id").asLong();

        GuildReg guildReg = (GuildReg) ydw.getGuild(guildId);

        switch (channelType) {
            case GUILD_TEXT -> {
                TextChannel textChannel = ydw.getTextChannelCache().get(channelId);
                if (textChannel == null || guildReg == null) {
                    ydw.getLogger()
                        .debug("Received channel delete event but channel is null, here is the json: "
                                + json.toPrettyString());
                }
                ydw.getTextChannelCache().remove(channelId);
                ydw.handelEvent(new ChannelDeleteEvent(ydw, textChannel));
            }
            case GUILD_VOICE -> {
                VoiceChannel voiceChannel = ydw.getVoiceChannelCache().get(channelId);
                if (voiceChannel == null || guildReg == null) {
                    ydw.getLogger()
                        .debug("Received channel delete event but channel is null, here is the json: "
                                + json.toPrettyString());
                }
                ydw.getVoiceChannelCache().remove(channelId);
                ydw.handelEvent(new ChannelDeleteEvent(ydw, voiceChannel));
            }
            case GUILD_CATEGORY -> {
                Category category = ydw.getCategoryCache().get(channelId);
                if (category == null || guildReg == null) {
                    ydw.getLogger()
                        .debug("Received channel delete event but channel is null, here is the json: "
                                + json.toPrettyString());
                }
                ydw.getCategoryCache().remove(channelId);
                ydw.handelEvent(new ChannelDeleteEvent(ydw, category));
            }
            case GUILD_NEWS -> {
                NewsChannel newsChannel = ydw.getNewsChannelCache().get(channelId);
                if (newsChannel == null || guildReg == null) {
                    ydw.getLogger()
                        .debug("Received channel delete event but channel is null, here is the json: "
                                + json.toPrettyString());
                }
                ydw.getNewsChannelCache().remove(channelId);
                ydw.handelEvent(new ChannelDeleteEvent(ydw, newsChannel));
            }
            case GUILD_STORE -> {
                StageChannel stageChannel = ydw.getStageChannelCache().get(channelId);
                if (stageChannel == null || guildReg == null) {
                    ydw.getLogger()
                        .debug("Received channel delete event but channel is null, here is the json: "
                                + json.toPrettyString());
                }
                ydw.getStageChannelCache().remove(channelId);
                ydw.handelEvent(new ChannelDeleteEvent(ydw, stageChannel));
            }
            default -> {
                ydw.getLogger()
                    .debug("Received channel delete event but channel type is unknown or unsupported, here is the json: "
                            + json.toPrettyString());
            }
        }
    }
}
