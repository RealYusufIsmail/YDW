/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.rest.name;

import io.github.realyusufismail.ydw.YDWInfo;
import org.jetbrains.annotations.NotNull;

public enum EndPoint {
    GET_GUILD("/guilds/%s"),
    GET_STICKERS_IN_GUILD("/guilds/%s/stickers"),
    GET_GUILD_STICKER("/guilds/%s/stickers/%s"),
    GET_GUILD_PREVIEW("/guilds/%s/preview"),
    BAN_USER("/guilds/%s/bans/%s"),
    GET_BANS("/guilds/%s/bans"),
    GET_BAN("/guilds/%s/bans/%s"),
    UNBAN_USER("/guilds/%s/bans/%s"),
    KICK_MEMBER("/guilds/%s/members/%s"),
    GET_MEMBER("/guilds/%s/members/%s"),

    // sticker
    GET_STICKERS_AVAILABLE_FOR_NITRO("/sticker-packs"),

    // channel
    GET_CHANNEL("/channels/%s"),
    DELETE_CHANNEL("/channels/%s"),
    GET_CHANNEL_MESSAGES("/channels/%s/messages"),
    GET_CHANNEL_MESSAGE("/channels/%s/messages/%s"),
    CREATE_MESSAGE("/channels/%s/messages"),
    DELETE_MESSAGE("/channels/%s/messages/%s"),
    BULK_DELETE_MESSAGES("/channels/%s/messages/bulk-delete"),
    // does not include threads
    GET_GUILD_CHANNELS("/guilds/%s/channels"),

    GET_CATEGORY("/channels/%s"),

    // user
    GET_CURRENT_USER("/users/@me"),
    GET_USER("/users/%s"),
    GET_CURRENT_USER_GUILD_MEMBER("/users/@me/guilds/%s/member"),
    GET_CURRENT_USER_GUILDS("/users/@me/guilds"),
    LEAVE_GUILD("/users/@me/guilds/%s"),
    CREATE_DM("/users/@me/channels"),

    // voice
    LIST_VOICE_REGIONS("/voice/regions"),

    // emoji
    GET_LIST_GUILD_EMOJI("/guilds/%s/emojis"),
    GET_GUILD_EMOJI("/guilds/%s/emojis/%s"),

    // Slash command.
    GLOBAL_SLASH_COMMAND("/applications/%s/commands"),
    GUILD_SLASH_COMMAND("/applications/%s/guilds/%s/commands"),
    UPDATE_GUILD_SLASH_COMMAND("/applications/%s/guilds/%s/commands/%s"),
    UPDATE_GLOBAL_SLASH_COMMAND("/applications/%s/commands/%s"),
    DELETE_GLOBAL_SLASH_COMMAND("/applications/%s/commands/%s"),
    DELETE_GUILD_SLASH_COMMAND("/applications/%s/guilds/%s/commands/%s"),
    BULK_OVERWRITE_GLOBAL_SLASH_COMMAND("/applications/%s/commands"),
    BULK_OVERWRITE_GUILD_SLASH_COMMAND("/applications/%s/guilds/%s/commands"),
    GET_GLOBAL_SLASH_COMMAND("/applications/%s/commands/%s"),
    GET_GLOBAL_SLASH_COMMANDS("/applications/%s/commands"),
    GET_GUILD_SLASH_COMMAND("/applications/%s/guilds/%s/commands/%s"),
    GET_GUILD_SLASH_COMMANDS("/applications/%s/guilds/%s/commands"),
    REPLY_TO_SLASH_COMMAND("/interactions/%s/%s/callback"),
    GET_APPLICATION_COMMAND("/applications/%s/commands/%s");

    private final String endpoint;

    EndPoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    @NotNull
    public String getFullEndpoint(@NotNull Object... parameters) {
        StringBuilder sb = new StringBuilder(endpoint);
        // add YDWInfo.DISCORD_REST_LINK at the beginning of the string
        sb.insert(0, YDWInfo.DISCORD_REST_LINK);
        // replaces %s with parameters
        for (Object parm : parameters) {
            sb.replace(sb.indexOf("%s"), sb.indexOf("%s") + 2, parm.toString());
        }
        return sb.toString();
    }
}
