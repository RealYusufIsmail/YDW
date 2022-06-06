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

package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.application.Application;
import io.github.realyusufismail.ydw.entities.Attachment;
import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.channel.Reaction;
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
