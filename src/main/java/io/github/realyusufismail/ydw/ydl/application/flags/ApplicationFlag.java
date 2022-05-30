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

package io.github.realyusufismail.ydw.ydl.application.flags;

import org.jetbrains.annotations.NotNull;

public enum ApplicationFlag {
    /**
     * Intent required for bots in 100 or more servers to receive presence_update events.
     */
    GATEWAY_PRESENCE(1 << 12),
    /**
     * Intent required for bots in under 100 servers to receive presence_update events, found in Bot
     * Settings
     */
    GATEWAY_PRESENCE_LIMITED(1 << 13),
    /**
     * Intent required for bots in 100 or more servers to receive member-related events like
     * guild_member_add. See list of member-related events under GUILD_MEMBERS.
     */
    GATEWAY_GUILD_MEMBERS(1 << 14),
    /**
     * Intent required for bots in under 100 servers to receive member-related events like
     * guild_member_add, found in Bot Settings. See list of member-related events under
     * GUILD_MEMBERS.
     */
    GATEWAY_GUILD_MEMBERS_LIMITED(1 << 15),
    /**
     * Indicates unusual growth of an app that prevents verification.
     */
    VERIFICATION_PENDING_GUILD_LIMIT(1 << 16),
    /**
     * Indicates if an app is embedded within the Discord client (currently unavailable publicly).
     */
    EMBEDDED(1 << 17),
    /**
     * Intent required for bots in 100 or more servers to receive message content.
     */
    GATEWAY_MESSAGE_CONTENT(1 << 18),
    /**
     * Intent required for bots in under 100 servers to receive message content, found in Bot
     * Settings.
     */
    GATEWAY_MESSAGE_CONTENT_LIMITED(1 << 19),
    /**
     * For future use.
     */
    UNKNOWN(-1);

    private final int value;

    ApplicationFlag(int value) {
        this.value = value;
    }

    @NotNull
    public static ApplicationFlag fromValue(int value) {
        for (ApplicationFlag flag : values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        return UNKNOWN;
    }

    @NotNull
    public static ApplicationFlag[] fromValues(int value) {
        ApplicationFlag[] flags = new ApplicationFlag[0];
        for (ApplicationFlag flag : values()) {
            if ((value & flag.getValue()) == flag.getValue()) {
                flags = append(flags, flag);
            }
        }
        return flags;
    }

    @NotNull
    private static ApplicationFlag[] append(@NotNull ApplicationFlag[] flags,
            ApplicationFlag flag) {
        ApplicationFlag[] newFlags = new ApplicationFlag[flags.length + 1];
        System.arraycopy(flags, 0, newFlags, 0, flags.length);
        newFlags[flags.length] = flag;
        return newFlags;
    }

    public int getValue() {
        return value;
    }
}
