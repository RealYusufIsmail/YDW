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

package yusufsdiscordbot.ydl.application.interaction.resolved;

import org.jetbrains.annotations.Nullable;
import yusufsdiscordbot.ydl.entities.Attachment;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydl.entities.guild.Channel;
import yusufsdiscordbot.ydl.entities.guild.Member;
import yusufsdiscordbot.ydl.entities.guild.Message;
import yusufsdiscordbot.ydl.entities.guild.Role;
import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import java.util.Map;

public interface ResolvedData {
    /**
     * @return the ids and User objects
     */
    Map<SnowFlake, User> getUsers();

    /**
     * @return the ids and partial Member objects
     */
    @Nullable
    Map<SnowFlake, Member> getMembers();

    /**
     * @return the ids and Role objects
     */
    @Nullable
    Map<SnowFlake, Role> getRoles();

    /**
     * @return the ids and partial Channel objects
     */
    @Nullable
    Map<SnowFlake, Channel> getChannels();

    /**
     * @return the ids and partial Message objects
     */
    @Nullable
    Map<SnowFlake, Message> getMessages();

    /**
     * @return the ids and attachment objects
     */
    @Nullable
    Map<SnowFlake, Attachment> getAttachments();
}
