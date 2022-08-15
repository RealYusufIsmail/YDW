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
package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.Application;
import io.github.realyusufismail.ydw.entities.Attachment;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Reaction;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydw.entities.guild.message.MessageActivity;
import io.github.realyusufismail.ydw.entities.guild.message.MessageReference;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.entities.sticker.StickerItem;
import io.github.realyusufismail.ydw.interaction.MessageInteraction;
import io.github.realyusufismail.ydwreg.application.ApplicationReg;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.ReactionReg;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.entities.embed.EmbedReg;
import io.github.realyusufismail.ydwreg.entities.message.MessageActivityReg;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydwreg.entities.message.MessageReferenceReg;
import io.github.realyusufismail.ydwreg.entities.message.MessageType;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerItemReg;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerReg;
import io.github.realyusufismail.ydwreg.message_components.ComponentType;
import io.github.realyusufismail.ydwreg.message_components.interaction.MessageInteractionReg;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class MessageReg implements Message {
    private final YDW ydw;
    private final long id;

    private final Channel channel;
    @NotNull
    private final User author;
    private final String content;
    private final ZonedDateTime timestamp;
    private final ZonedDateTime editedTimestamp;
    private final boolean tts;

    private final List<User> mentions = new ArrayList<>();
    private final List<Role> mentionedRoles = new ArrayList<>();
    private final List<Channel> mentionedChannels = new ArrayList<>();
    private final List<Attachment> attachments = new ArrayList<>();
    private final List<Embed> embeds = new ArrayList<>();
    private final List<Reaction> reactions = new ArrayList<>();
    private final List<Sticker> stickers = new ArrayList<>();
    private final EnumSet<MessageFlags> flags = EnumSet.noneOf(MessageFlags.class);
    private final List<StickerItem> stickerItems = new ArrayList<>();
    private final EnumSet<ComponentType> componentTypes = EnumSet.noneOf(ComponentType.class);
    private final MessageType type;
    private final Boolean isPinned;
    private final Integer nonce;
    private final MessageReference reference;
    private final boolean mentionsEveryone;
    private final Application application;
    private final Message referencedMessage;
    private final MessageInteraction interaction;
    private final Channel thread;
    private final MessageActivity messageActivity;


    public MessageReg(@NotNull JsonNode message, long id, @NotNull YDW ydw) {
        this.ydw = ydw;
        this.id = id;

        this.channel = ydw.getChannel(Channel.class, message.get("channel_id").asLong());

        this.author =
                new UserReg(message.get("author"), message.get("author").get("id").asLong(), ydw);

        this.content = message.get("content").asText();

        this.timestamp = ZonedDateTime.parse(message.get("timestamp").asText());

        this.editedTimestamp = message.hasNonNull("edited_timestamp")
                ? ZonedDateTime.parse(message.get("edited_timestamp").asText())
                : null;

        this.tts = message.get("tts").asBoolean();

        this.type = MessageType.fromInt(message.get("type").asInt());

        this.isPinned = message.get("pinned").asBoolean();

        this.nonce = message.get("nonce") != null ? message.get("nonce").asInt() : null;

        this.reference = message.get("message_reference") != null
                ? new MessageReferenceReg(message.get("message_reference"),
                        message.get("message_reference").get("id").asLong(), ydw)
                : null;

        this.mentionsEveryone = message.get("mention_everyone").asBoolean();

        this.application =
                message.hasNonNull("application")
                        ? new ApplicationReg(message.get("application"),
                                message.get("application").get("id").asLong(), ydw)
                        : null;

        this.referencedMessage =
                message.hasNonNull("referenced_message")
                        ? new MessageReg(message.get("referenced_message"),
                                message.get("referenced_message").get("id").asLong(), ydw)
                        : null;

        this.interaction = message.hasNonNull("interaction")
                ? new MessageInteractionReg(message.get("interaction"),
                        message.get("interaction").get("id").asLong(), ydw)
                : null;

        this.thread = message.hasNonNull("thread_channel_id")
                ? ydw.getChannel(Channel.class, message.get("thread_channel_id").asLong())
                : null;

        this.messageActivity =
                message.hasNonNull("activity") ? new MessageActivityReg(message.get("activity"))
                        : null;

        if (message.hasNonNull("mentions")) {
            for (JsonNode mention : message.get("mentions")) {
                mentions.add(new UserReg(mention, mention.get("id").asLong(), ydw));
            }
        }

        if (message.hasNonNull("mention_roles")) {
            for (JsonNode role : message.get("mention_roles")) {
                mentionedRoles.add(new RoleReg(role, ydw, role.get("id").asLong()));
            }
        }

        if (message.hasNonNull("mention_channels")) {
            for (JsonNode channel : message.get("mention_channels")) {
                mentionedChannels.add(new ChannelReg(channel, channel.get("id").asLong(), ydw));
            }
        }

        if (message.hasNonNull("attachments")) {
            for (JsonNode attachment : message.get("attachments")) {
                attachments.add(new AttachmentReg(attachment, attachment.get("id").asLong(), ydw));
            }
        }

        if (message.hasNonNull("embeds")) {
            for (JsonNode embed : message.get("embeds")) {
                embeds.add(new EmbedReg(embed));
            }
        }

        if (message.hasNonNull("reactions")) {
            for (JsonNode reaction : message.get("reactions")) {
                reactions.add(new ReactionReg(reaction, ydw));
            }
        }

        if (message.hasNonNull("stickers")) {
            for (JsonNode sticker : message.get("stickers")) {
                stickers.add(new StickerReg(sticker, sticker.get("id").asLong(), ydw));
            }
        }

        if (message.hasNonNull("sticker_items")) {
            for (JsonNode sticker : message.get("sticker_items")) {
                stickerItems.add(new StickerItemReg(sticker, ydw));
            }
        }

        if (message.hasNonNull("components")) {
            for (JsonNode component : message.get("components")) {
                ComponentType type = ComponentType.valueOf(component.get("type").asText());
                componentTypes.add(type);
            }
        }
    }

    @Override
    public YDW getYDW() {
        return ydw;
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

    @NotNull
    @Override
    public User getAuthor() {
        return author;
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
    public Optional<ZonedDateTime> getEditedTimestamp() {
        return Optional.ofNullable(editedTimestamp);
    }

    @Override
    public Boolean isTTS() {
        return tts;
    }

    @Override
    public Boolean doesMentionEveryone() {
        return mentionsEveryone;
    }

    @Override
    public List<User> getMentions() {
        return mentions;
    }

    @Override
    public List<Role> getMentionedRoles() {
        return mentionedRoles;
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

    @NotNull
    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public Optional<MessageActivity> getActivity() {
        return Optional.ofNullable(messageActivity);
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

    @Override
    public Optional<Message> getReferencedMessage() {
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
