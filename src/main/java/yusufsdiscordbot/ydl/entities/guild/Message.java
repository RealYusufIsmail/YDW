/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package yusufsdiscordbot.ydl.entities.guild;

import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import yusufsdiscordbot.ydl.application.Application;
import yusufsdiscordbot.ydl.entities.Attachment;
import yusufsdiscordbot.ydl.entities.GenericEntity;
import yusufsdiscordbot.ydl.entities.Guild;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydl.entities.embed.Embed;
import yusufsdiscordbot.ydl.entities.guild.channel.Reaction;
import yusufsdiscordbot.ydl.entities.guild.message.MessageReference;
import yusufsdiscordbot.ydl.entities.sticker.Sticker;
import yusufsdiscordbot.ydl.entities.sticker.StickerItem;
import yusufsdiscordbot.ydl.interaction.MessageInteraction;
import yusufsdiscordbot.ydlreg.entities.message.MessageActivityType;
import yusufsdiscordbot.ydlreg.entities.message.MessageFlags;
import yusufsdiscordbot.ydlreg.entities.message.MessageType;
import yusufsdiscordbot.ydlreg.message_components.ComponentType;

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
