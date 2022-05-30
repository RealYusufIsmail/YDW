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

package io.github.realyusufismail.ydw.ydlreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.Attachment;
import io.github.realyusufismail.ydw.ydl.entities.Guild;
import io.github.realyusufismail.ydw.ydl.entities.User;
import io.github.realyusufismail.ydw.ydl.entities.embed.Embed;
import io.github.realyusufismail.ydw.ydl.entities.guild.Channel;
import io.github.realyusufismail.ydw.ydl.entities.guild.Member;
import io.github.realyusufismail.ydw.ydl.entities.guild.Message;
import io.github.realyusufismail.ydw.ydl.entities.guild.Role;
import io.github.realyusufismail.ydw.ydl.entities.guild.channel.Reaction;
import io.github.realyusufismail.ydw.ydlreg.application.ApplicationReg;
import io.github.realyusufismail.ydw.ydlreg.entities.channel.ReactionReg;
import io.github.realyusufismail.ydw.ydlreg.entities.message.MessageActivityType;
import io.github.realyusufismail.ydw.ydlreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydw.ydlreg.entities.message.MessageReferenceReg;
import io.github.realyusufismail.ydw.ydlreg.entities.message.MessageType;
import io.github.realyusufismail.ydw.ydlreg.entities.sticker.StickerItemReg;
import io.github.realyusufismail.ydw.ydlreg.entities.sticker.StickerReg;
import io.github.realyusufismail.ydw.ydlreg.message_components.ComponentType;
import io.github.realyusufismail.ydw.ydlreg.message_components.interaction.MessageInteractionReg;
import io.github.realyusufismail.ydw.ydl.application.Application;
import io.github.realyusufismail.ydw.ydl.entities.guild.message.MessageReference;
import io.github.realyusufismail.ydw.ydl.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.ydl.entities.sticker.StickerItem;
import io.github.realyusufismail.ydw.ydl.interaction.MessageInteraction;
import io.github.realyusufismail.ydw.ydlreg.entities.UserReg;
import io.github.realyusufismail.ydw.ydlreg.entities.embed.EmbedReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.*;

public class MessageReg implements Message {
    private final YDL ydl;
    private final long id;

    private final Channel channel;
    private final Guild guild;
    @NotNull
    private final User author;
    @Nullable
    private final Member member;
    private final String content;
    private final ZonedDateTime timestamp;
    private final ZonedDateTime editedTimestamp;
    @NotNull
    private final Boolean tts;
    @NotNull
    private final Boolean mentionEveryone;
    private final Map<User, Member> mentions = new HashMap<>();
    private final List<Role> roles = new ArrayList<>();
    private final List<Channel> mentionedChannels = new ArrayList<>();
    private final List<Attachment> attachments = new ArrayList<>();
    private final List<Embed> embeds = new ArrayList<>();
    private final List<Reaction> reactions = new ArrayList<>();
    private final List<Sticker> stickers = new ArrayList<>();
    private final EnumSet<MessageFlags> flags = EnumSet.noneOf(MessageFlags.class);
    private final List<StickerItem> stickerItems = new ArrayList<>();
    private final EnumSet<ComponentType> componentTypes = EnumSet.noneOf(ComponentType.class);
    @NotNull
    private final MessageType type;
    @NotNull
    private final Boolean isPinned;
    private final Integer nonce;
    @Nullable
    private final MessageReference reference;
    @Nullable
    private final MessageActivityType activityType;
    @NotNull
    private final Boolean mentionsEveryone;
    @Nullable
    private final Application application;
    @Nullable
    private final Message referencedMessage;
    @Nullable
    private final MessageInteraction interaction;
    private final Channel thread;


    public MessageReg(@NotNull JsonNode message, long id, @NotNull YDL ydl) {
        this.ydl = ydl;
        this.id = id;

        this.channel = ydl.getChannel(message.get("channel_id").asLong());
        this.guild =
                message.get("guild_id") != null ? ydl.getGuild(message.get("guild_id").asLong())
                        : null;
        this.author =
                new UserReg(message.get("author"), message.get("author").get("id").asLong(), ydl);
        this.member =
                message.get("member") != null ? new MemberReg(message.get("member"), ydl) : null;
        this.content = message.get("content").asText();
        this.timestamp = ZonedDateTime.parse(message.get("timestamp").asText());
        this.editedTimestamp = ZonedDateTime.parse(message.get("edited_timestamp").asText());
        this.tts = message.get("tts").asBoolean();
        this.mentionEveryone = message.get("mention_everyone").asBoolean();
        this.type = MessageType.valueOf(message.get("type").asText());
        this.isPinned = message.get("pinned").asBoolean();
        this.nonce = message.get("nonce") != null ? message.get("nonce").asInt() : null;
        this.reference = message.get("message_reference") != null
                ? new MessageReferenceReg(message.get("message_reference"), ydl)
                : null;
        this.activityType = message.get("activity") != null
                ? MessageActivityType.valueOf(message.get("activity").get("type").asText())
                : null;
        this.mentionsEveryone = message.get("mentions_everyone").asBoolean();
        this.application =
                message.get("application") != null ? new ApplicationReg(message.get("application"),
                        message.get("application").get("id").asLong(), ydl) : null;
        this.referencedMessage = message.get("referenced_message") != null
                ? new MessageReg(message.get("referenced_message"),
                        message.get("referenced_message").get("id").asLong(), ydl)
                : null;
        this.interaction = message.get("interaction") != null
                ? new MessageInteractionReg(message.get("interaction"), ydl)
                : null;
        this.thread = message.get("thread_channel_id") != null
                ? ydl.getChannel(message.get("thread_channel_id").asLong())
                : null;

        if (message.has("mentions")) {
            for (JsonNode mention : message.get("mentions")) {
                mentions.put(new UserReg(mention, mention.get("id").asLong(), ydl),
                        new MemberReg(mention, ydl));
            }
        }

        if (message.has("mention_roles")) {
            for (JsonNode role : message.get("mention_roles")) {
                roles.add(new RoleReg(role, ydl, role.get("id").asLong()));
            }
        }

        if (message.has("mention_channels")) {
            for (JsonNode channel : message.get("mention_channels")) {
                mentionedChannels.add(new ChannelReg(channel, channel.get("id").asLong(), ydl));
            }
        }

        if (message.has("attachments")) {
            for (JsonNode attachment : message.get("attachments")) {
                attachments.add(new AttachmentReg(attachment, attachment.get("id").asLong(), ydl));
            }
        }

        if (message.has("embeds")) {
            for (JsonNode embed : message.get("embeds")) {
                embeds.add(new EmbedReg(embed));
            }
        }

        if (message.has("reactions")) {
            for (JsonNode reaction : message.get("reactions")) {
                reactions.add(new ReactionReg(reaction, ydl));
            }
        }

        if (message.has("stickers")) {
            for (JsonNode sticker : message.get("stickers")) {
                stickers.add(new StickerReg(sticker, sticker.get("id").asLong(), ydl));
            }
        }

        if (message.has("sticker_items")) {
            for (JsonNode sticker : message.get("sticker_items")) {
                stickerItems.add(new StickerItemReg(sticker, ydl));
            }
        }

        if (message.has("components")) {
            for (JsonNode component : message.get("components")) {
                ComponentType type = ComponentType.valueOf(component.get("type").asText());
                componentTypes.add(type);
            }
        }
    }

    @Override
    public YDL getYDL() {
        return ydl;
    }

    /**
     * @return The core long of this api.
     */
    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @NotNull
    @Override
    public Optional<Member> getMember() {
        return Optional.ofNullable(member);
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public ZonedDateTime getEditedTimestamp() {
        return editedTimestamp;
    }

    @Override
    public Boolean isTTS() {
        return tts;
    }

    @Override
    public Boolean doesMentionEveryone() {
        return mentionsEveryone;
    }

    @NotNull
    @Override
    public Map<User, Member> getMentions() {
        return mentions;
    }

    @NotNull
    @Override
    public List<Role> getRoles() {
        return roles;
    }

    @NotNull
    @Override
    public List<Channel> getMentionChannels() {
        return mentionedChannels;
    }

    @NotNull
    @Override
    public List<Attachment> getAttachments() {
        return attachments;
    }

    @NotNull
    @Override
    public List<Embed> getEmbeds() {
        return embeds;
    }

    @NotNull
    @Override
    public List<Reaction> getReactions() {
        return reactions;
    }

    @NotNull
    @Override
    public Optional<Integer> getNonceAsInt() {
        return Optional.ofNullable(nonce);
    }

    @Override
    public Boolean isPinned() {
        return isPinned;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public MessageActivityType getActivityType() {
        return activityType;
    }

    @NotNull
    @Override
    public Optional<Application> getApplication() {
        return Optional.ofNullable(application);
    }

    @NotNull
    @Override
    public Optional<MessageReference> getMessageReference() {
        return Optional.ofNullable(reference);
    }

    @NotNull
    @Override
    public EnumSet<MessageFlags> getFlags() {
        return flags;
    }

    @NotNull
    @Override
    public Optional<Message> getReferenceMessage() {
        return Optional.ofNullable(referencedMessage);
    }

    @NotNull
    @Override
    public Optional<MessageInteraction> getMessageInteraction() {
        return Optional.ofNullable(interaction);
    }

    @NotNull
    @Override
    public Optional<Channel> getThread() {
        return Optional.ofNullable(thread);
    }

    @NotNull
    @Override
    public EnumSet<ComponentType> getComponentTypes() {
        return componentTypes;
    }

    @NotNull
    @Override
    public List<StickerItem> getStickerItems() {
        return stickerItems;
    }

    @NotNull
    @Override
    public List<Sticker> getStickers() {
        return stickers;
    }
}
