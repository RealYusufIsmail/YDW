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
    GET_GUILD(YDWInfo.DISCORD_REST_LINK + "/guilds/%s"),
    GET_STICKERS_IN_GUILD(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/stickers"),
    GET_GUILD_STICKER(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/stickers/%s"),
    GET_GUILD_PREVIEW(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/preview"),
    BAN_USER(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/bans/%s"),
    GET_BANS(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/bans"),
    GET_BAN(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/bans/%s"),
    UNBAN_USER(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/bans/%s"),
    KICK_MEMBER(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/members/%s"),
    GET_MEMBER(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/members/%s"),

    // sticker
    GET_STICKERS_AVAILABLE_FOR_NITRO(YDWInfo.DISCORD_REST_LINK + "/sticker-packs"),

    // channel
    GET_CHANNEL(YDWInfo.DISCORD_REST_LINK + "/channels/%s"),
    DELETE_CHANNEL(YDWInfo.DISCORD_REST_LINK + "/channels/%s"),
    GET_CHANNEL_MESSAGES(YDWInfo.DISCORD_REST_LINK + "/channels/%s/messages"),
    GET_CHANNEL_MESSAGE(YDWInfo.DISCORD_REST_LINK + "/channels/%s/messages/%s"),
    CREATE_MESSAGE(YDWInfo.DISCORD_REST_LINK + "/channels/%s/messages"),
    // does not include threads
    GET_GUILD_CHANNELS(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/channels"),

    GET_CATEGORY(YDWInfo.DISCORD_REST_LINK + "/channels/%s"),

    // user
    GET_CURRENT_USER(YDWInfo.DISCORD_REST_LINK + "/users/@me"),
    GET_USER(YDWInfo.DISCORD_REST_LINK + "/users/%s"),
    GET_CURRENT_USER_GUILD_MEMBER(YDWInfo.DISCORD_REST_LINK + "/users/@me/guilds/%s/member"),
    GET_CURRENT_USER_GUILDS(YDWInfo.DISCORD_REST_LINK + "/users/@me/guilds"),
    LEAVE_GUILD(YDWInfo.DISCORD_REST_LINK + "/users/@me/guilds/%s"),
    CREATE_DM(YDWInfo.DISCORD_REST_LINK + "/users/@me/channels"),

    // voice
    LIST_VOICE_REGIONS(YDWInfo.DISCORD_REST_LINK + "/voice/regions"),

    // emoji
    GET_LIST_GUILD_EMOJI(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/emojis"),
    GET_GUILD_EMOJI(YDWInfo.DISCORD_REST_LINK + "/guilds/%s/emojis/%s"),

    // Slash command.
    GLOBAL_SLASH_COMMAND(YDWInfo.DISCORD_REST_LINK + "/applications/%s/commands"),
    GUILD_SLASH_COMMAND(YDWInfo.DISCORD_REST_LINK + "/applications/%s/guilds/%s/commands"),
    UPDATE_GUILD_SLASH_COMMAND(
            YDWInfo.DISCORD_REST_LINK + "/applications/%s/guilds/%s/commands/%s"),
    UPDATE_GLOBAL_SLASH_COMMAND(YDWInfo.DISCORD_REST_LINK + "/applications/%s/commands/%s"),
    DELETE_GLOBAL_SLASH_COMMAND(YDWInfo.DISCORD_REST_LINK + "/applications/%s/commands/%s"),
    DELETE_GUILD_SLASH_COMMAND(
            YDWInfo.DISCORD_REST_LINK + "/applications/%s/guilds/%s/commands/%s"),
    BULK_OVERWRITE_GLOBAL_SLASH_COMMAND(YDWInfo.DISCORD_REST_LINK + "/applications/%s/commands"),
    BULK_OVERWRITE_GUILD_SLASH_COMMAND(
            YDWInfo.DISCORD_REST_LINK + "/applications/%s/guilds/%s/commands"),
    GET_GLOBAL_SLASH_COMMAND(YDWInfo.DISCORD_REST_LINK + "/applications/%s/commands/%s"),
    GET_GLOBAL_SLASH_COMMANDS(YDWInfo.DISCORD_REST_LINK + "/applications/%s/commands"),
    GET_GUILD_SLASH_COMMAND(YDWInfo.DISCORD_REST_LINK + "/applications/%s/guilds/%s/commands/%s"),
    GET_GUILD_SLASH_COMMANDS(YDWInfo.DISCORD_REST_LINK + "/applications/%s/guilds/%s/commands"),
    REPLY_TO_SLASH_COMMAND(YDWInfo.DISCORD_REST_LINK + "/interactions/%s/%s/callback");

    private final String endpoint;

    EndPoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    @NotNull
    public String getFullEndpoint(@NotNull Object... parms) {
        StringBuilder sb = new StringBuilder(endpoint);
        // replaces %s with parms
        for (Object parm : parms) {
            sb.replace(sb.indexOf("%s"), sb.indexOf("%s") + 2, parm.toString());
        }
        return sb.toString();
    }
}
