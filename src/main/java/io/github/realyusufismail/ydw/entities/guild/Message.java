/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.application.Application;
import io.github.realyusufismail.ydw.entities.*;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydw.entities.guild.message.MessageReference;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.entities.sticker.StickerItem;
import io.github.realyusufismail.ydw.interaction.MessageInteraction;
import io.github.realyusufismail.ydwreg.entities.message.MessageActivityType;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydwreg.entities.message.MessageType;
import io.github.realyusufismail.ydwreg.message_components.ComponentType;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Message extends SnowFlake, GenericEntity {
    Channel getChannel();

    Guild getGuild();

    User getAuthor();

    Optional<Member> getMember();

    String getContent();

    ZonedDateTime getTimestamp();

    ZonedDateTime getEditedTimestamp();

    /**
     * @return true if the message is text to speech(TTS), false otherwise
     */
    Boolean isTTS();

    Boolean doesMentionEveryone();

    Map<User, Member> getMentions();

    List<Role> getRoles();

    List<Channel> getMentionChannels();

    List<Attachment> getAttachments();

    List<Embed> getEmbeds();

    List<Reaction> getReactions();

    Optional<Integer> getNonceAsInt();

    @NotNull
    default Optional<String> getNonce() {
        return getNonceAsInt().map(Object::toString);
    }

    Boolean isPinned();

    MessageType getType();

    MessageActivityType getActivityType();

    Optional<Application> getApplication();

    Optional<MessageReference> getMessageReference();

    EnumSet<MessageFlags> getFlags();

    Optional<Message> getReferenceMessage();

    Optional<MessageInteraction> getMessageInteraction();

    Optional<Channel> getThread();

    EnumSet<ComponentType> getComponentTypes();

    List<StickerItem> getStickerItems();

    List<Sticker> getStickers();
}
