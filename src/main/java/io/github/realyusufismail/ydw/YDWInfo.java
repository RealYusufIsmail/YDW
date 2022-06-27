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

package io.github.realyusufismail.ydw;

public class YDWInfo {
    public static final int DISCORD_REST_VERSION = 10;
    public static final int DISCORD_GATEWAY_VERSION = 10;
    public static final String ydw_VERSION = "0.0.1";

    public static final String DISCORD_GATEWAY_LINK =
            "wss://gateway.discord.gg/?v=" + DISCORD_GATEWAY_VERSION + "&encoding=json";
    public static final String DISCORD_REST_LINK =
            "https://discord.com/api/v" + DISCORD_REST_VERSION;
    public static final String ydw_GITHUB = "https://github.com/ydw";

    private YDWInfo() {}
}
