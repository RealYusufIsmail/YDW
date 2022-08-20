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
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.channel.*;
import io.github.realyusufismail.ydw.event.events.channel.ChannelDeleteEvent;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.handle.Handle;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

public class ChannelDeleteHandler extends Handle {
    public ChannelDeleteHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        ChannelType channelType = ChannelType.getChannelType(json.get("type").asInt());

        GuildReg guildReg = (GuildReg) ydw.getGuild(json.get("guild_id").asLong());
        long channelId = json.get("id").asLong();

        switch (channelType) {
            case TEXT -> {
                TextChannel textChannel = ydw.getTextChannelCache().remove(SnowFlake.of(channelId));

                if (textChannel == null || guildReg == null) {
                    WebSocketManager.logger.warn("ChannelDeleteHandler: TextChannel is null");
                    return;
                }

                guildReg.getTextChannelCache().remove(SnowFlake.of(channelId));
                ydw.handelEvent(new ChannelDeleteEvent(ydw, textChannel));
            }
            case NEWS -> {
                NewsChannel newsChannel = ydw.getNewsChannelCache().remove(SnowFlake.of(channelId));

                if (newsChannel == null || guildReg == null) {
                    WebSocketManager.logger.warn("ChannelDeleteHandler: NewsChannel is null");
                    return;
                }

                guildReg.getNewsChannelCache().remove(SnowFlake.of(channelId));
                ydw.handelEvent(new ChannelDeleteEvent(ydw, newsChannel));
            }
            case STAGE -> {
                StageChannel stageChannel =
                        ydw.getStageChannelCache().remove(SnowFlake.of(channelId));

                if (stageChannel == null || guildReg == null) {
                    WebSocketManager.logger.warn("ChannelDeleteHandler: StageChannel is null");
                    return;
                }

                guildReg.getStageChannelCache().remove(SnowFlake.of(channelId));
                ydw.handelEvent(new ChannelDeleteEvent(ydw, stageChannel));
            }
            case DM, GROUP_DM -> {
                WebSocketManager.logger.debug("Not supported");
            }
            case VOICE -> {
                VoiceChannel voiceChannel =
                        ydw.getVoiceChannelCache().remove(SnowFlake.of(channelId));

                if (voiceChannel == null || guildReg == null) {
                    WebSocketManager.logger.warn("ChannelDeleteHandler: VoiceChannel is null");
                    return;
                }

                guildReg.getVoiceChannelCache().remove(SnowFlake.of(channelId));
            }
            case CATEGORY -> {
                Category category = ydw.getCategoryCache().remove(SnowFlake.of(channelId));

                if (category == null || guildReg == null) {
                    WebSocketManager.logger.warn("ChannelDeleteHandler: Category is null");
                    return;
                }

                guildReg.getCategoryCache().remove(SnowFlake.of(channelId));

                ydw.handelEvent(new ChannelDeleteEvent(ydw, category));
            }
            case NEWS_THREAD, PUBLIC_THREAD, PRIVATE_THREAD -> {
                ThreadChannel threadChannel =
                        ydw.getThreadChannelCache().remove(SnowFlake.of(channelId));

                if (threadChannel == null || guildReg == null) {
                    WebSocketManager.logger.warn("ChannelDeleteHandler: ThreadChannel is null");
                    return;
                }

                guildReg.getThreadChannelCache().remove(SnowFlake.of(channelId));

                ydw.handelEvent(new ChannelDeleteEvent(ydw, threadChannel));
            }
            case GUILD_DIRECTORY -> {
                WebSocketManager.logger
                    .warn("ChannelDeleteHandler: GuildDirectory is not implemented");
            }
            case GUILD_FORUM -> {
                WebSocketManager.logger
                    .debug("ChannelDeleteHandler: GuildForum is not implemented");
            }
            case UNKNOWN -> {
                WebSocketManager.logger.warn("ChannelDeleteHandler: Unknown channel type");
            }
        }
    }
}
