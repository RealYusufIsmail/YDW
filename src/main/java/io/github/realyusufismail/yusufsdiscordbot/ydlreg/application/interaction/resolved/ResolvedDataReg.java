/*
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *                 YDL Copyright (C) 2022 - future  YusufsDiscordbot
 * This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it
 * under certain conditions
 */

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.application.interaction.resolved;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.github.realyusufismail.yusufsdiscordbot.ydl.application.interaction.resolved.ResolvedData;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.Attachment;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.User;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Channel;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Member;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Message;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Role;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import java.util.Map;

// TODO: finish this class
public record ResolvedDataReg(JsonNode data) implements ResolvedData {

    @Override
    public @NotNull Map<SnowFlake, User> getUsers() {
        return null;
    }

    @Override
    public @Nullable Map<SnowFlake, Member> getMembers() {
        return null;
    }

    @Override
    public @Nullable Map<SnowFlake, Role> getRoles() {
        return null;
    }

    @Override
    public @Nullable Map<SnowFlake, Channel> getChannels() {
        return null;
    }

    @Override
    public @Nullable Map<SnowFlake, Message> getMessages() {
        return null;
    }

    @Override
    public @Nullable Map<SnowFlake, Attachment> getAttachments() {
        return null;
    }
}
