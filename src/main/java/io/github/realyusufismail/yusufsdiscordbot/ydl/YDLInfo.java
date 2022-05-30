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

package io.github.realyusufismail.yusufsdiscordbot.ydl;

public class YDLInfo {
    public static final int DISCORD_REST_VERSION = 10;
    public static final int DISCORD_GATEWAY_VERSION = 9;
    public static final int YDL_VERSION = 1;
    public static final String DISCORD_GATEWAY_LINK =
            "wss://gateway.discord.gg/?v=" + DISCORD_GATEWAY_VERSION + "&encoding=json";
    public static final String DISCORD_REST_LINK =
            "https://discord.com/api/v" + DISCORD_REST_VERSION;
    public static final String YDL_GITHUB = "https://github.com/YDL";

    private YDLInfo() {}
}
