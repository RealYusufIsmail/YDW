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
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.CategoryReg;
import io.github.realyusufismail.ydwreg.handle.Handle;

import java.util.Objects;
import java.util.Optional;

public class ChannelUpdateHandler extends Handle {
    public ChannelUpdateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        Channel channel = new ChannelReg(json, json.get("id").asLong(), ydw);
        ChannelType channelType = channel.getType();

        if (channelType.isGuild()) {
            Optional<GuildChannel> guildChannel = channel.asGuildChannel();

            if (guildChannel.isEmpty()) {
                return;
            }

            switch (channelType) {
                case TEXT -> updateText(guildChannel.get());
                case NEWS -> updateNews(guildChannel.get());
                case VOICE -> updateVoice(guildChannel.get());
                case CATEGORY -> updateCategory(guildChannel.get());
                case STAGE ->  updateStage(guildChannel.get());
                case GUILD_DIRECTORY -> updateGuildDirectory(guildChannel.get());
                case NEWS_THREAD -> updateNewsThread(guildChannel.get());
                case PUBLIC_THREAD -> updatePublicThread(guildChannel.get());
                case PRIVATE_THREAD -> updatePrivateThread(guildChannel.get());
                case GUILD_FORUM -> updateGuildForum(guildChannel.get());
                default -> {
                    // Do nothing
                }
            }
        } else {
            switch (channelType) {
                case DM -> updateDM(channel);
                case GROUP_DM -> updateGroupDM(channel);
                default -> {
                    // Do nothing
                }
            }
        }
    }

    // Guild channels
    private void updateCategory(GuildChannel channel) {
        Optional<Category> opCategory = channel.asCategory();

        if (opCategory.isEmpty()) {
            return;
        }

        CategoryReg category = (CategoryReg) opCategory.get();

        String oldName = category.getName();
        int oldPosition = category.getPosition();
        boolean oldNSFW = category.isNSFW();

        String newName = json.get("name").asText();
        int newPosition = json.get("position").asInt();
        boolean newNSFW = json.get("nsfw").asBoolean();

        if (!Objects.deepEquals(oldName, newName)) {
            category.setName(newName);
        }

        if (oldPosition != newPosition) {
            category.setPosition(newPosition);
        }

        if (oldNSFW != newNSFW) {
            category.setNsfw(newNSFW);
        }
    }

    private void updateText(GuildChannel channel) {
        Optional<TextChannel> opTextChannel = channel.asTextChannel();

        if (opTextChannel.isEmpty()) {
            return;
        }

        TextChannel textChannel = opTextChannel.get();

        String oldName = textChannel.getName();
        int oldPosition = textChannel.getPosition();
        boolean oldNSFW = textChannel.isNSFW();
        int oldRateLimit = textChannel.getRateLimitPerUser();
        String oldTopic = textChannel.getTopic();
    }

    private void updateNews(GuildChannel channel) {
    }

    private void updateVoice(GuildChannel channel) {
    }

    private void updateNewsThread(GuildChannel channel) {
    }

    private void updatePublicThread(GuildChannel channel) {
    }

    private void updatePrivateThread(GuildChannel channel) {
    }

    private void updateGuildForum(GuildChannel channel) {
    }

    private void updateStage(GuildChannel channel) {
    }

    private void updateGuildDirectory(GuildChannel channel) {
    }

    //non-guild channels
    private void updateDM(Channel channel) {
    }

    private void updateGroupDM(Channel channel) {
    }
}
