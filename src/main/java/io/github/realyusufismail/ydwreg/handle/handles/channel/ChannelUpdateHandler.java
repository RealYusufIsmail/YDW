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
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.NewsChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.VoiceChannel;
import io.github.realyusufismail.ydw.event.events.channel.update.*;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.guild.GuildChannelReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.CategoryReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.NewsChannelReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.TextChannelReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.VoiceChannelReg;
import io.github.realyusufismail.ydwreg.handle.Handle;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

import java.util.List;
import java.util.Objects;

public class ChannelUpdateHandler extends Handle {
    public ChannelUpdateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        Channel channel = new ChannelReg(json, json.get("id").asLong(), ydw);

        ChannelType type = channel.getType();

        if (type.isGuild()) {
            GuildChannel guildChannel = new GuildChannelReg(json, json.get("id").asLong(), ydw);
            updateGuildChannel(guildChannel, type);
        } else {
            updateChannel(channel);
        }
    }

    private void updateGuildChannel(GuildChannel channel, ChannelType type) {
        Guild guild = channel.getGuild();

        switch (type) {
            case GUILD_TEXT -> updateTextChannel(
                    new TextChannelReg(json, json.get("id").asLong(), ydw), guild);
            case GUILD_VOICE -> updateVoiceChannel(
                    new VoiceChannelReg(json, json.get("id").asLong(), ydw));
            case GUILD_CATEGORY -> updateCategoryChannel(
                    new CategoryReg(json, json.get("id").asLong(), ydw));
            case GUILD_NEWS -> updateNewsChannel(
                    new NewsChannelReg(json, json.get("id").asLong(), ydw));
            default -> ydw.getLogger().warn("Unknown guild channel type: " + type);
        }
    }

    private void updateChannel(Channel channel) {

    }

    private void updateTextChannel(TextChannel channel, Guild guild) {
        TextChannel textChannel = ydw.getTextChannelCache().get(channel.getIdLong());

        if (textChannel != null) {
            String name = channel.getName();
            boolean nsfw = channel.isNSFW();
            Integer position = channel.getPosition();
            Integer rateLimit = channel.getRateLimitPerUser();
            String topic = channel.getTopic();
            SnowFlake lastMessageId = channel.getLastMessageId();
            SnowFlake parentId = channel.getParentId();
            Category parent = channel.getCategory();
            List<Overwrite> overwrites = channel.getPermissionOverwrites();

            new TextChannelReg(json, json.get("id").asLong(), ydw);

            if (!Objects.equals(name, textChannel.getName())) {
                ydw.handelEvent(
                        new ChannelNameUpdateEvent(ydw, textChannel, textChannel.getName(), name));
            } else if (!nsfw == textChannel.isNSFW()) {
                ydw.handelEvent(
                        new ChannelNSFWUpdateEvent(ydw, textChannel, textChannel.isNSFW(), nsfw));
            } else if (!Objects.equals(position, textChannel.getPosition())) {
                ydw.handelEvent(new ChannelPositionUpdateEvent(ydw, textChannel,
                        textChannel.getPosition(), position));
            } else if (!Objects.equals(rateLimit, textChannel.getRateLimitPerUser())) {
                ydw.handelEvent(new ChannelRateLimitUpdateEvent(ydw, textChannel,
                        textChannel.getRateLimitPerUser(), rateLimit));
            } else if (!Objects.equals(topic, textChannel.getTopic())) {
                ydw.handelEvent(
                        new ChannelTopicUpdate(ydw, textChannel, textChannel.getTopic(), topic));
            } else if (!Objects.equals(lastMessageId, textChannel.getLastMessageId())) {
                ydw.handelEvent(new ChannelLastMessageUpdateEvent(ydw, textChannel,
                        textChannel.getLastMessageId(), lastMessageId));
            } else if (!Objects.equals(parentId, textChannel.getParentId())) {
                ydw.handelEvent(new ChannelParentUpdateEvent(ydw, textChannel,
                        textChannel.getParentId(), parentId));
            } else if (!Objects.equals(parent, textChannel.getCategory())) {
                ydw.handelEvent(new ChannelCategoryUpdateEvent(ydw, textChannel,
                        textChannel.getCategory(), parent));
            } else if (!overwrites.equals(textChannel.getPermissionOverwrites())) {
                ydw.handelEvent(new ChannelPermissionUpdateEvent(ydw, textChannel,
                        textChannel.getPermissionOverwrites(), overwrites));
            }
        } else {
            new TextChannelReg(json, json.get("id").asLong(), ydw);
        }
    }

    private void updateVoiceChannel(VoiceChannel channel) {}

    private void updateCategoryChannel(Category channel) {}

    private void updateNewsChannel(NewsChannel channel) {}
}
