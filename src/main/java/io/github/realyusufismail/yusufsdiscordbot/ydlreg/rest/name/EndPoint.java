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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.name;

import io.github.realyusufismail.yusufsdiscordbot.ydl.YDLInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public enum EndPoint {
    GET_GUILD(YDLInfo.DISCORD_REST_LINK + "/guilds/%s"),
    GET_STICKERS_IN_GUILD(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/stickers"),
    GET_GUILD_STICKER(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/stickers/%s"),
    GET_GUILD_PREVIEW(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/preview"),
    BAN_USER(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/bans/%s"),
    GET_BANS(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/bans"),
    GET_BAN(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/bans/%s"),
    UNBAN_USER(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/bans/%s"),
    KICK_MEMBER(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/members/%s"),
    GET_MEMBER(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/members/%s"),

    // sticker
    GET_STICKERS_AVAILABLE_FOR_NITRO(YDLInfo.DISCORD_REST_LINK + "/sticker-packs"),

    // channel
    GET_CHANNEL(YDLInfo.DISCORD_REST_LINK + "/channels/%s"),
    DELETE_CHANNEL(YDLInfo.DISCORD_REST_LINK + "/channels/%s"),
    GET_CHANNEL_MESSAGES(YDLInfo.DISCORD_REST_LINK + "/channels/%s/messages"),
    GET_CHANNEL_MESSAGE(YDLInfo.DISCORD_REST_LINK + "/channels/%s/messages/%s"),
    CREATE_MESSAGE(YDLInfo.DISCORD_REST_LINK + "/channels/%s/messages"),

    // user
    GET_CURRENT_USER(YDLInfo.DISCORD_REST_LINK + "/users/@me"),
    GET_USER(YDLInfo.DISCORD_REST_LINK + "/users/%s"),
    GET_CURRENT_USER_GUILD_MEMBER(YDLInfo.DISCORD_REST_LINK + "/users/@me/guilds/%s/member"),
    GET_CURRENT_USER_GUILDS(YDLInfo.DISCORD_REST_LINK + "/users/@me/guilds/%s"),
    LEAVE_GUILD(YDLInfo.DISCORD_REST_LINK + "/users/@me/guilds/%s"),
    CREATE_DM(YDLInfo.DISCORD_REST_LINK + "/users/@me/channels"),

    // voice
    LIST_VOICE_REGIONS(YDLInfo.DISCORD_REST_LINK + "/voice/regions"),

    // emoji
    GET_LIST_GUILD_EMOJI(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/emojis"),
    GET_GUILD_EMOJI(YDLInfo.DISCORD_REST_LINK + "/guilds/%s/emojis/%s"),;

    private final String endpoint;

    EndPoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public @NotNull String getFullEndpoint(@NotNull String... parms) {
        StringBuilder sb = new StringBuilder(endpoint);
        int parmsAmount = getEndpoint().split("%s").length - (getEndpoint().endsWith("%s") ? 1 : 0);
        if (parms.length > parmsAmount) {
            for (int i = parmsAmount; i < parms.length; i++) {
                sb.append("/").append(parms[i]);
            }
        }
        return sb.toString();
    }

    @NotNull
    public String getFullEndpoint(Long... parms) {
        return getFullEndpoint(Arrays.toString(parms));
    }
}
