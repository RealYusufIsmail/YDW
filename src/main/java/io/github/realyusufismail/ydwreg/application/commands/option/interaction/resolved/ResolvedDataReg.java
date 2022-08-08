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
package io.github.realyusufismail.ydwreg.application.commands.option.interaction.resolved;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.interaction.resolved.ResolvedData;
import io.github.realyusufismail.ydw.entities.Attachment;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.entities.guild.AttachmentReg;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydwreg.entities.guild.MessageReg;
import io.github.realyusufismail.ydwreg.entities.guild.RoleReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

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
