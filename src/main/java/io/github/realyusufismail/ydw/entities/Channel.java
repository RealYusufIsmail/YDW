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
package io.github.realyusufismail.ydw.entities;

import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.threads.ThreadMetadata;
import io.github.realyusufismail.ydw.perm.Permission;
import io.github.realyusufismail.ydwreg.action.MessageActionReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface Channel extends SnowFlake, GenericEntity {
    @NotNull
    ChannelType getType();

    Optional<Guild> getGuild();

    Optional<Integer> getPosition();

    List<Overwrite> getPermissionOverwrites();

    Optional<String> getName();

    Optional<String> getTopic();

    Optional<Boolean> isNSFW();

    Optional<SnowFlake> getLastMessageId();

    Optional<Integer> getBitrate();

    Optional<Integer> getUserLimit();

    Optional<Integer> getRateLimitPerUser();

    List<User> getRecipients();

    Optional<String> getIcon();

    Optional<User> getOwner();

    Optional<SnowFlake> getApplicationId();

    Optional<SnowFlake> getParentId();

    Optional<ZonedDateTime> getLastPinTimestamp();

    Optional<String> getRTCRegion();

    Optional<Integer> getVideoQualityMode();

    Optional<Integer> getMessageCount();

    Optional<Integer> getMemberCount();

    Optional<ThreadMetadata> getThreadMetadata();

    Optional<Member> getMember();

    Optional<Integer> getDefaultAutoArchiveDuration();

    Optional<Permission[]> getPermissions();

    Optional<MessageFlags[]> getFlags();

    Optional<Category> getCategory();


    // Rest Actions
    MessageActionReg sendMessage(String message);

    MessageActionReg sendEmbedMessage(EmbedBuilder embedBuilder);

    @NotNull
    Message getMessage(long messageId);

    @NotNull
    default Message getMessage(@NotNull String messageId) {
        return getMessage(Long.parseUnsignedLong(messageId));
    }

    List<Message> getMessages(int limit);

    @NotNull
    default MessageActionReg deleteMessage(@NotNull String messageId) {
        return deleteMessage(Long.parseUnsignedLong(messageId));
    }

    @NotNull
    MessageActionReg deleteMessage(long messageId);

    @NotNull
    MessageActionReg deleteMessages(int amount);
}
