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
import io.github.realyusufismail.ydw.entities.guild.channel.*;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.channel.OverwriteReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.*;
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
            Optional<GuildChannel> opGuildChannel = channel.asGuildChannel();

            if (opGuildChannel.isEmpty()) {
                return;
            }

            GuildChannel guildChannel = opGuildChannel.get();

            switch (channelType) {
                case TEXT -> updateText(guildChannel);
                case NEWS -> updateNews(guildChannel);
                case VOICE -> updateVoice(guildChannel);
                case CATEGORY -> updateCategory(guildChannel);
                case STAGE -> updateStage(guildChannel);
                case GUILD_DIRECTORY -> updateGuildDirectory(guildChannel);
                case NEWS_THREAD, PRIVATE_THREAD, PUBLIC_THREAD -> updateThread(guildChannel);
                case GUILD_FORUM -> updateGuildForum(guildChannel);
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

        TextChannelReg textChannel = (TextChannelReg) opTextChannel.get();

        String oldName = textChannel.getName();
        int oldPosition = textChannel.getPosition();
        boolean oldNSFW = textChannel.isNSFW();
        int oldRateLimit = textChannel.getRateLimitPerUser();
        String oldTopic = textChannel.getTopic();
        int oldPermissionOverwrites = textChannel.getPermissionOverwrites().size();
        int oldDefaultAutoArchiveDuration = textChannel.getDefaultAutoArchiveDuration();
        Optional<Category> oldCategory = textChannel.getCategory();
        long oldLastMessageId = textChannel.getLastMessageId().getIdLong();

        String newName = json.get("name").asText();
        int newPosition = json.get("position").asInt();
        boolean newNSFW = json.get("nsfw").asBoolean();
        int newRateLimit = json.get("rate_limit_per_user").asInt();
        String newTopic = json.get("topic").asText();
        int newPermissionOverwrites = json.get("permission_overwrites").size();
        int newDefaultAutoArchiveDuration = json.get("default_auto_archive_duration").asInt();
        Optional<Category> newCategory = json.hasNonNull("parent_id")
                ? Optional
                    .ofNullable(ydw.getChannel(Category.class, json.get("parent_id").asLong()))
                : Optional.empty();
        long newLastMessageId = json.get("last_message_id").asLong();

        if (!Objects.deepEquals(oldName, newName)) {
            textChannel.setName(newName);
        }

        if (oldPosition != newPosition) {
            textChannel.setPosition(newPosition);
        }

        if (oldNSFW != newNSFW) {
            textChannel.setNsfw(newNSFW);
        }

        if (oldRateLimit != newRateLimit) {
            textChannel.setRateLimitPerUser(newRateLimit);
        }

        if (!Objects.deepEquals(oldTopic, newTopic)) {
            textChannel.setTopic(newTopic);
        }

        if (oldPermissionOverwrites != newPermissionOverwrites) {
            for (JsonNode permissionOverwrite : json.get("permission_overwrites")) {
                textChannel.setPermissionOverwrites(new OverwriteReg(permissionOverwrite,
                        permissionOverwrite.get("id").asLong(), ydw));
            }
        }

        if (oldDefaultAutoArchiveDuration != newDefaultAutoArchiveDuration) {
            textChannel.setDefaultAutoArchiveDuration(newDefaultAutoArchiveDuration);
        }

        if (!oldCategory.equals(newCategory)) {
            oldCategory.ifPresent(textChannel::setCategory);
        }

        if (oldLastMessageId != newLastMessageId) {
            textChannel.setLastMessageId(newLastMessageId);
        }
    }

    private void updateNews(GuildChannel channel) {
        Optional<NewsChannel> opNewsChannel = channel.asNewsChannel();

        if (opNewsChannel.isEmpty()) {
            return;
        }

        NewsChannelReg newsChannel = (NewsChannelReg) opNewsChannel.get();

        String oldName = newsChannel.getName();
        int oldPosition = newsChannel.getPosition();
        boolean oldNSFW = newsChannel.isNSFW();
        String oldTopic = newsChannel.getTopic();
        int oldPermissionOverwrites = newsChannel.getPermissionOverwrites().size();
        int oldDefaultAutoArchiveDuration = newsChannel.getDefaultAutoArchiveDuration();
        long oldLastMessageId = newsChannel.lastMessageId().getIdLong();
        Optional<Category> oldCategory = newsChannel.getCategory();

        String newName = json.get("name").asText();
        int newPosition = json.get("position").asInt();
        boolean newNSFW = json.get("nsfw").asBoolean();
        String newTopic = json.get("topic").asText();
        int newPermissionOverwrites = json.get("permission_overwrites").size();
        int newDefaultAutoArchiveDuration = json.get("default_auto_archive_duration").asInt();
        long newLastMessageId = json.get("last_message_id").asLong();
        Optional<Category> newCategory = json.hasNonNull("parent_id")
                ? Optional
                    .ofNullable(ydw.getChannel(Category.class, json.get("parent_id").asLong()))
                : Optional.empty();

        if (!Objects.deepEquals(oldName, newName)) {
            newsChannel.setName(newName);
        }

        if (oldPosition != newPosition) {
            newsChannel.setPosition(newPosition);
        }

        if (oldNSFW != newNSFW) {
            newsChannel.setNsfw(newNSFW);
        }

        if (!Objects.deepEquals(oldTopic, newTopic)) {
            newsChannel.setTopic(newTopic);
        }

        if (oldPermissionOverwrites != newPermissionOverwrites) {
            for (JsonNode permissionOverwrite : json.get("permission_overwrites")) {
                newsChannel.setPermissionOverwrites(new OverwriteReg(permissionOverwrite,
                        permissionOverwrite.get("id").asLong(), ydw));
            }
        }

        if (oldDefaultAutoArchiveDuration != newDefaultAutoArchiveDuration) {
            newsChannel.setDefaultAutoArchiveDuration(newDefaultAutoArchiveDuration);
        }

        if (oldLastMessageId != newLastMessageId) {
            newsChannel.setLastMessageId(newLastMessageId);
        }

        if (!oldCategory.equals(newCategory)) {
            oldCategory.ifPresent(newsChannel::setCategory);
        }
    }

    private void updateVoice(GuildChannel channel) {
        Optional<VoiceChannel> opVoiceChannel = channel.asVoiceChannel();

        if (opVoiceChannel.isEmpty()) {
            return;
        }

        VoiceChannelReg voiceChannel = (VoiceChannelReg) opVoiceChannel.get();

        String oldName = voiceChannel.getName();
        int oldPermissionOverwrites = voiceChannel.getPermissionOverwrites().size();
        int oldBitrate = voiceChannel.getBitrate();
        int oldUserLimit = voiceChannel.getUserLimit();
        String oldRTCRegion = voiceChannel.getRTCRegion();
        int oldPosition = voiceChannel.getPosition();
        boolean oldNSFW = voiceChannel.isNSFW();
        Optional<Category> oldCategory = voiceChannel.getCategory();


        String newName = json.get("name").asText();
        int newPermissionOverwrites = json.get("permission_overwrites").size();
        int newBitrate = json.get("bitrate").asInt();
        int newUserLimit = json.get("user_limit").asInt();
        String newRTCRegion = json.get("rtc_region").asText();
        int newPosition = json.get("position").asInt();
        boolean newNSFW = json.get("nsfw").asBoolean();
        Optional<Category> newCategory = json.hasNonNull("parent_id")
                ? Optional
                    .ofNullable(ydw.getChannel(Category.class, json.get("parent_id").asLong()))
                : Optional.empty();

        if (!Objects.deepEquals(oldName, newName)) {
            voiceChannel.setName(newName);
        }

        if (oldPermissionOverwrites != newPermissionOverwrites) {
            for (JsonNode permissionOverwrite : json.get("permission_overwrites")) {
                voiceChannel.setPermissionOverwrites(new OverwriteReg(permissionOverwrite,
                        permissionOverwrite.get("id").asLong(), ydw));
            }
        }

        if (oldBitrate != newBitrate) {
            voiceChannel.setBitrate(newBitrate);
        }

        if (oldUserLimit != newUserLimit) {
            voiceChannel.setUserLimit(newUserLimit);
        }

        if (!Objects.deepEquals(oldRTCRegion, newRTCRegion)) {
            voiceChannel.setRtcRegion(newRTCRegion);
        }

        if (oldPosition != newPosition) {
            voiceChannel.setPosition(newPosition);
        }

        if (oldNSFW != newNSFW) {
            voiceChannel.setNSFW(newNSFW);
        }

        if (!oldCategory.equals(newCategory)) {
            oldCategory.ifPresent(voiceChannel::setCategory);
        }
    }

    private void updateThread(GuildChannel channel) {
        Optional<ThreadChannel> opThreadChannel = channel.asThread();

        if (opThreadChannel.isEmpty()) {
            return;
        }


        ThreadChannelReg threadChannel = (ThreadChannelReg) opThreadChannel.get();

        String oldName = threadChannel.getName();
        long oldLastMessageId = threadChannel.getLastMessageId().getIdLong();
        int oldMessageCount = threadChannel.getMessageCount();
        int oldMemberCount = threadChannel.getMemberCount();
        int oldRateLimitPerUser = threadChannel.getRateLimitPerUser();
    }

    private void updateGuildForum(GuildChannel channel) {}

    private void updateStage(GuildChannel channel) {}

    private void updateGuildDirectory(GuildChannel channel) {}

    // non-guild channels
    private void updateDM(Channel channel) {}

    private void updateGroupDM(Channel channel) {}
}
