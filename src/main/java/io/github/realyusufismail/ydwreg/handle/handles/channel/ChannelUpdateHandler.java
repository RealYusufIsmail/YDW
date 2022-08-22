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
import io.github.realyusufismail.cache.Cache;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.*;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydw.event.events.channel.update.ChannelNameUpdateEvent;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.entities.channel.OverwriteReg;
import io.github.realyusufismail.ydwreg.entities.guild.channel.*;
import io.github.realyusufismail.ydwreg.entities.guild.channel.thread.ThreadMetadataReg;
import io.github.realyusufismail.ydwreg.handle.EventCache;
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
            long channelId = json.get("id").asLong();
            String newName = json.get("name").asText();
            int newPosition = json.get("position").asInt();
            boolean newNSFW = json.get("nsfw").asBoolean();
            int newPermissionOverwrites = json.get("permission_overwrites").size();
            int newRateLimitPerUser = json.get("rate_limit_per_user").asInt();
            Optional<Category> newCategory = json.hasNonNull("parent_id")
                    ? Optional
                        .ofNullable(ydw.getChannel(Category.class, json.get("parent_id").asLong()))
                    : Optional.empty();

            if (newCategory.isEmpty()) {
                newCategory = Optional.empty();
            }

            GuildChannel guildChannel = ydw.getGuildChannelById(channelId);

            if (guildChannel == null) {
                // need to get the constructor
                // is there a way to get the constructer as this::new?


                ydw.getEventCache()
                    .cache(EventCache.CacheType.CHANNEL, channelId, json, ChannelUpdateHandler::new,
                            ydw);

                EventCache.logger.debug("Channel {} is not cached yet", channelId);
                return;
            }

            guildChannel = checkForChannelChange(guildChannel, json, channelType);

            switch (channelType) {
                case TEXT -> updateText(guildChannel, newName, newPosition, newNSFW,
                        newPermissionOverwrites, newRateLimitPerUser, newCategory.get());
                case NEWS -> updateNews(guildChannel, newName, newPosition, newNSFW,
                        newPermissionOverwrites, newRateLimitPerUser, newCategory.get());
                case VOICE -> updateVoice(guildChannel, channelId, newName, newPosition, newNSFW,
                        newPermissionOverwrites, newRateLimitPerUser, newCategory.get());
                case CATEGORY -> updateCategory(guildChannel, newName, newPosition, newNSFW);
                case STAGE -> updateStage(guildChannel, channelId, newName, newPosition, newNSFW,
                        newPermissionOverwrites, newRateLimitPerUser, newCategory.get());
                case GUILD_DIRECTORY -> updateGuildDirectory(guildChannel, channelId, newName,
                        newPosition, newNSFW, newPermissionOverwrites, newRateLimitPerUser,
                        newCategory.get());
                case NEWS_THREAD, PRIVATE_THREAD, PUBLIC_THREAD -> updateThread(guildChannel,
                        newName, newRateLimitPerUser);
                case GUILD_FORUM -> updateGuildForum(guildChannel, channelId, newName, newPosition,
                        newNSFW, newPermissionOverwrites, newRateLimitPerUser, newCategory.get());
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

    private GuildChannel checkForChannelChange(GuildChannel guildChannel, JsonNode json,
            ChannelType channelType) {
        if (guildChannel.getType() == channelType) {
            return guildChannel;
        }

        GuildReg guildReg = (GuildReg) guildChannel.getGuild();

        // TODO: Add handle update event
        if (channelType == ChannelType.TEXT) {
            NewsChannel newsChannel = (NewsChannel) guildChannel;
            ydw.getNewsChannelCache().remove(newsChannel);
            guildReg.getNewsChannelCache().remove(newsChannel);

            TextChannelReg textChannelReg =
                    new TextChannelReg(guildReg, json, json.get("id").asLong(), ydw);

            textChannelReg.setLastMessageId(newsChannel.getLastMessageId().getIdLong());

            return textChannelReg;
        }

        if (channelType == ChannelType.NEWS) {
            TextChannel textChannelReg = (TextChannel) guildChannel;
            ydw.getTextChannelCache().remove(textChannelReg);
            guildReg.getTextChannelCache().remove(textChannelReg);

            NewsChannelReg newsChannelReg =
                    new NewsChannelReg(guildReg, json, json.get("id").asLong(), ydw);

            newsChannelReg.setLastMessageId(textChannelReg.getLastMessageId().getIdLong());

            return newsChannelReg;
        }

        return guildChannel;
    }

    // Guild channels
    private void updateCategory(GuildChannel channel, String newName, int newPosition,
            boolean newNSFW) {
        Optional<Category> opCategory = channel.asCategory();

        if (opCategory.isEmpty()) {
            return;
        }

        CategoryReg category = (CategoryReg) opCategory.get();

        String oldName = category.getName();
        int oldPosition = category.getPosition();
        boolean oldNSFW = category.isNSFW();

        if (!Objects.deepEquals(oldName, newName)) {
            category.setName(newName);

            ydw.handelEvent(new ChannelNameUpdateEvent(ydw, category, oldName, newName));
        }

        if (oldPosition != newPosition) {
            category.setPosition(newPosition);
        }

        if (oldNSFW != newNSFW) {
            category.setNsfw(newNSFW);
        }
    }

    private void updateText(GuildChannel channel, String newName, int newPosition, boolean newNSFW,
            int newPermissionOverwrites, int newRateLimitPerUser, Category newCategory) {
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


        int newRateLimit = json.get("rate_limit_per_user").asInt();
        String newTopic = json.get("topic").asText();
        int newDefaultAutoArchiveDuration = json.get("default_auto_archive_duration").asInt();

        long newLastMessageId = json.get("last_message_id").asLong();

        if (!Objects.deepEquals(oldName, newName)) {
            textChannel.setName(newName);

            ydw.handelEvent(new ChannelNameUpdateEvent(ydw, textChannel, oldName, newName));
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

        if (oldCategory.isPresent()) {
            if (!oldCategory.get().equals(newCategory)) {
                oldCategory.ifPresent(textChannel::setCategory);
            }
        }

        if (oldLastMessageId != newLastMessageId) {
            textChannel.setLastMessageId(newLastMessageId);
        }
    }

    private void updateNews(GuildChannel channel, String newName, int newPosition, boolean newNSFW,
            int newPermissionOverwrites, int newRateLimitPerUser, Category newCategory) {
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
        long oldLastMessageId = newsChannel.getLastMessageId().getIdLong();
        Optional<Category> oldCategory = newsChannel.getCategory();

        int newDefaultAutoArchiveDuration = json.get("default_auto_archive_duration").asInt();
        String newTopic = json.get("topic").asText();
        long newLastMessageId = json.get("last_message_id").asLong();


        if (!Objects.deepEquals(oldName, newName)) {
            newsChannel.setName(newName);

            ydw.handelEvent(new ChannelNameUpdateEvent(ydw, newsChannel, oldName, newName));
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

        if (oldCategory.isPresent()) {
            if (!oldCategory.get().equals(newCategory)) {
                oldCategory.ifPresent(newsChannel::setCategory);
            }
        }
    }

    private void updateVoice(GuildChannel channel, long channelId, String newName, int newPosition,
            boolean newNSFW, int newPermissionOverwrites, int newRateLimitPerUser,
            Category newCategory) {
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


        int newBitrate = json.get("bitrate").asInt();
        int newUserLimit = json.get("user_limit").asInt();
        String newRTCRegion = json.get("rtc_region").asText();

        if (!Objects.deepEquals(oldName, newName)) {
            voiceChannel.setName(newName);

            ydw.handelEvent(new ChannelNameUpdateEvent(ydw, voiceChannel, oldName, newName));
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


        if (oldCategory.isPresent()) {
            if (!oldCategory.get().equals(newCategory)) {
                oldCategory.ifPresent(voiceChannel::setCategory);
            }
        }
    }

    private void updateThread(GuildChannel channel, String newName, int newRateLimitPerUser) {
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
        ThreadMetadata oldMetadata = threadChannel.getMetadata();
        int oldTotalMessageSent = threadChannel.getTotalMessagesSent();

        int newMessageCount = json.get("message_count").asInt();
        int newMemberCount = json.get("member_count").asInt();
        ThreadMetadata newMetadata = new ThreadMetadataReg(json.get("metadata"));
        int newTotalMessageSent = json.get("total_messages_sent").asInt();
        long newLastMessageId = json.get("last_message_id").asLong();

        if (!Objects.deepEquals(oldName, newName)) {
            threadChannel.setName(newName);

            ydw.handelEvent(new ChannelNameUpdateEvent(ydw, threadChannel, oldName, newName));
        }

        if (oldLastMessageId != newLastMessageId) {
            threadChannel.setLastMessageId(newLastMessageId);
        }

        if (oldMessageCount != newMessageCount) {
            threadChannel.setMessageCount(newMessageCount);
        }

        if (oldMemberCount != newMemberCount) {
            threadChannel.setMemberCount(newMemberCount);
        }

        if (oldRateLimitPerUser != newRateLimitPerUser) {
            threadChannel.setRateLimitPerUser(newRateLimitPerUser);
        }

        if (!Objects.deepEquals(oldMetadata, newMetadata)) {
            threadChannel.setMetadata(newMetadata);
        }

        if (oldTotalMessageSent != newTotalMessageSent) {
            threadChannel.setTotalMessagesSent(newTotalMessageSent);
        }
    }

    private void updateGuildForum(GuildChannel channel, long channelId, String newName,
            int newPosition, boolean newNSFW, int newPermissionOverwrites, int newRateLimitPerUser,
            Category newCategory) {}

    private void updateStage(GuildChannel channel, long channelId, String newName, int newPosition,
            boolean newNSFW, int newPermissionOverwrites, int newRateLimitPerUser,
            Category newCategory) {
        Optional<StageChannel> opStageChannel = channel.asStageChannel();

        if (opStageChannel.isEmpty()) {
            return;
        }

        StageChannelReg stageChannel = (StageChannelReg) opStageChannel.get();

        String oldName = stageChannel.getName();
        int oldPermissionOverwrites = stageChannel.getPermissionOverwrites().size();
        boolean oldNSFW = stageChannel.isNSFW();
        int oldBitrate = stageChannel.getBitrate();
        int oldUserLimit = stageChannel.getUserLimit();
        String oldRTCRegion = stageChannel.getRTCRegion();
        int oldPosition = stageChannel.getPosition();
        Optional<Category> oldCategory = stageChannel.getCategory();


        int newBitrate = json.get("bitrate").asInt();
        int newUserLimit = json.get("user_limit").asInt();
        String newRTCRegion = json.get("rtc_region").asText();

        if (!Objects.deepEquals(oldName, newName)) {
            stageChannel.setName(newName);

            ydw.handelEvent(new ChannelNameUpdateEvent(ydw, stageChannel, oldName, newName));
        }

        if (oldPermissionOverwrites != newPermissionOverwrites) {
            for (JsonNode permissionOverwrite : json.get("permission_overwrites")) {
                stageChannel.setPermissionOverwrites(new OverwriteReg(permissionOverwrite,
                        permissionOverwrite.get("id").asLong(), ydw));
            }
        }

        if (oldNSFW != newNSFW) {
            stageChannel.setNSFW(newNSFW);
        }

        if (oldBitrate != newBitrate) {
            stageChannel.setBitrate(newBitrate);
        }

        if (oldUserLimit != newUserLimit) {
            stageChannel.setUserLimit(newUserLimit);
        }

        if (!Objects.deepEquals(oldRTCRegion, newRTCRegion)) {
            stageChannel.setRtcRegion(newRTCRegion);
        }

        if (oldPosition != newPosition) {
            stageChannel.setPosition(newPosition);
        }

        if (oldCategory.isPresent()) {
            if (!oldCategory.get().equals(newCategory)) {
                oldCategory.ifPresent(stageChannel::setCategory);
            }
        }
    }

    private void updateGuildDirectory(GuildChannel channel, long channelId, String newName,
            int newPosition, boolean newNSFW, int newPermissionOverwrites, int newRateLimitPerUser,
            Category category) {}

    // non-guild channels
    private void updateDM(Channel channel) {}

    private void updateGroupDM(Channel channel) {}
}
