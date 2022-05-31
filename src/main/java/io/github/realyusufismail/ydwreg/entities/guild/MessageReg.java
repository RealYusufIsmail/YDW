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

package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.Application;
import io.github.realyusufismail.ydw.entities.Attachment;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydw.entities.guild.channel.Reaction;
import io.github.realyusufismail.ydw.entities.guild.message.MessageReference;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.entities.sticker.StickerItem;
import io.github.realyusufismail.ydw.interaction.MessageInteraction;
import io.github.realyusufismail.ydwreg.application.ApplicationReg;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.entities.channel.ReactionReg;
import io.github.realyusufismail.ydwreg.entities.embed.EmbedReg;
import io.github.realyusufismail.ydwreg.entities.message.MessageActivityType;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydwreg.entities.message.MessageReferenceReg;
import io.github.realyusufismail.ydwreg.entities.message.MessageType;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerItemReg;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerReg;
import io.github.realyusufismail.ydwreg.message_components.ComponentType;
import io.github.realyusufismail.ydwreg.message_components.interaction.MessageInteractionReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.*;

public class MessageReg implements Message {
    private final YDW ydw;
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


    public MessageReg(@NotNull JsonNode message, long id, @NotNull YDW ydw) {
        this.ydw = ydw;
        this.id = id;

        this.channel = ydw.getChannel(message.get("channel_id").asLong());
        this.guild =
                message.get("guild_id") != null ? ydw.getGuild(message.get("guild_id").asLong())
                        : null;
        this.author =
                new UserReg(message.get("author"), message.get("author").get("id").asLong(), ydw);
        this.member =
                message.get("member") != null ? new MemberReg(message.get("member"), ydw) : null;
        this.content = message.get("content").asText();
        this.timestamp = ZonedDateTime.parse(message.get("timestamp").asText());
        this.editedTimestamp = ZonedDateTime.parse(message.get("edited_timestamp").asText());
        this.tts = message.get("tts").asBoolean();
        this.mentionEveryone = message.get("mention_everyone").asBoolean();
        this.type = MessageType.valueOf(message.get("type").asText());
        this.isPinned = message.get("pinned").asBoolean();
        this.nonce = message.get("nonce") != null ? message.get("nonce").asInt() : null;
        this.reference = message.get("message_reference") != null
                ? new MessageReferenceReg(message.get("message_reference"),
                        message.get("message_reference").get("id").asLong(), ydw)
                : null;
        this.activityType = message.get("activity") != null
                ? MessageActivityType.valueOf(message.get("activity").get("type").asText())
                : null;
        this.mentionsEveryone = message.get("mentions_everyone").asBoolean();
        this.application =
                message.get("application") != null ? new ApplicationReg(message.get("application"),
                        message.get("application").get("id").asLong(), ydw) : null;
        this.referencedMessage = message.get("referenced_message") != null
                ? new MessageReg(message.get("referenced_message"),
                        message.get("referenced_message").get("id").asLong(), ydw)
                : null;
        this.interaction = message.get("interaction") != null ? new MessageInteractionReg(
                message.get("interaction"), message.get("interaction").get("id").asLong(), ydw)
                : null;
        this.thread = message.get("thread_channel_id") != null
                ? ydw.getChannel(message.get("thread_channel_id").asLong())
                : null;

        if (message.has("mentions")) {
            for (JsonNode mention : message.get("mentions")) {
                mentions.put(new UserReg(mention, mention.get("id").asLong(), ydw),
                        new MemberReg(mention, ydw));
            }
        }

        if (message.has("mention_roles")) {
            for (JsonNode role : message.get("mention_roles")) {
                roles.add(new RoleReg(role, ydw, role.get("id").asLong()));
            }
        }

        if (message.has("mention_channels")) {
            for (JsonNode channel : message.get("mention_channels")) {
                mentionedChannels.add(new ChannelReg(channel, channel.get("id").asLong(), ydw));
            }
        }

        if (message.has("attachments")) {
            for (JsonNode attachment : message.get("attachments")) {
                attachments.add(new AttachmentReg(attachment, attachment.get("id").asLong(), ydw));
            }
        }

        if (message.has("embeds")) {
            for (JsonNode embed : message.get("embeds")) {
                embeds.add(new EmbedReg(embed));
            }
        }

        if (message.has("reactions")) {
            for (JsonNode reaction : message.get("reactions")) {
                reactions.add(new ReactionReg(reaction, ydw));
            }
        }

        if (message.has("stickers")) {
            for (JsonNode sticker : message.get("stickers")) {
                stickers.add(new StickerReg(sticker, sticker.get("id").asLong(), ydw));
            }
        }

        if (message.has("sticker_items")) {
            for (JsonNode sticker : message.get("sticker_items")) {
                stickerItems.add(new StickerItemReg(sticker, ydw));
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
