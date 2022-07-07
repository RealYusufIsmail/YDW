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
