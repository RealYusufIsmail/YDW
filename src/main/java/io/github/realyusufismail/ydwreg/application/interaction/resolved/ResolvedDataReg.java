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

package io.github.realyusufismail.ydwreg.application.interaction.resolved;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.interaction.resolved.ResolvedData;
import io.github.realyusufismail.ydw.entities.Attachment;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.entities.guild.*;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

// TODO: finish this class
public class ResolvedDataReg implements ResolvedData {

    private final Map<SnowFlake, User> users = new HashMap<>();
    private final Map<SnowFlake, Member> members = new HashMap<>();
    private final Map<SnowFlake, Role> roles = new HashMap<>();
    private final Map<SnowFlake, Channel> channels = new HashMap<>();
    private final Map<SnowFlake, Message> messages = new HashMap<>();
    private final Map<SnowFlake, Attachment> attachments = new HashMap<>();

    public ResolvedDataReg(JsonNode data, YDW ydw) {

        if (data.hasNonNull("users")) {
            JsonNode users = data.get("users");
            for (JsonNode user : users) {
                this.users.put(SnowFlake.of(user.get("id").asLong()),
                        new UserReg(user, user.get("id").asLong(), ydw));
            }
        }

        if (data.hasNonNull("members")) {
            JsonNode members = data.get("members");
            for (JsonNode member : members) {
                this.members.put(SnowFlake.of(member.get("id").asLong()),
                        new MemberReg(member, ydw));
            }
        }

        if (data.hasNonNull("roles")) {
            JsonNode roles = data.get("roles");
            for (JsonNode role : roles) {
                this.roles.put(SnowFlake.of(role.get("id").asLong()),
                        new RoleReg(role, ydw, role.get("id").asLong()));
            }
        }

        if (data.hasNonNull("channels")) {
            JsonNode channels = data.get("channels");
            for (JsonNode channel : channels) {
                this.channels.put(SnowFlake.of(channel.get("id").asLong()),
                        new ChannelReg(channel, channel.get("id").asLong(), ydw));
            }
        }

        if (data.hasNonNull("messages")) {
            JsonNode messages = data.get("messages");
            for (JsonNode message : messages) {
                this.messages.put(SnowFlake.of(message.get("id").asLong()),
                        new MessageReg(message, message.get("id").asLong(), ydw));
            }
        }

        if (data.hasNonNull("attachments")) {
            JsonNode attachments = data.get("attachments");
            for (JsonNode attachment : attachments) {
                this.attachments.put(SnowFlake.of(attachment.get("id").asLong()),
                        new AttachmentReg(attachment, attachment.get("id").asLong(), ydw));
            }
        }
    }

    @Override
    public @NotNull Map<SnowFlake, User> getUsers() {
        return users;
    }

    @Override
    public @Nullable Map<SnowFlake, Member> getMembers() {
        return members;
    }

    @Override
    public @Nullable Map<SnowFlake, Role> getRoles() {
        return roles;
    }

    @Override
    public @Nullable Map<SnowFlake, Channel> getChannels() {
        return channels;
    }

    @Override
    public @Nullable Map<SnowFlake, Message> getMessages() {
        return messages;
    }

    @Override
    public @Nullable Map<SnowFlake, Attachment> getAttachments() {
        return attachments;
    }
}
