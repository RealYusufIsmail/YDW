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

package io.github.realyusufismail.ydwreg;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Flags {
    /**
     * Intent required for bots in 100 or more servers to receive presence_update events
     */
    GATEWAY_PRESENCE(1 << 12),
    /**
     * Intent required for bots in under 100 servers to receive presence_update events, found in Bot
     * Settings
     */
    GATEWAY_PRESENCE_LIMITED(1 << 13),
    /**
     * Intent required for bots in 100 or more servers to receive member-related events like
     * guild_member_add. See list of member-related events under GUILD_MEMBERS
     */
    GATEWAY_GUILD_MEMBERS(1 << 14),
    /**
     * Intent required for bots in under 100 servers to receive member-related events like
     * guild_member_add, found in Bot Settings. See list of member-related events under
     * GUILD_MEMBERS
     */
    GATEWAY_GUILD_MEMBERS_LIMITED(1 << 15),
    /**
     * Indicates unusual growth of an app that prevents verification
     */
    VERIFICATION_PENDING_GUILD_LIMIT(1 << 16),
    /**
     * Indicates if an app is embedded within the Discord client (currently unavailable publicly)
     */
    EMBEDDED(1 << 17),
    /**
     * Intent required for bots in 100 or more servers to receive message content
     */
    GATEWAY_MESSAGE_CONTENT(1 << 18),
    /**
     * Intent required for bots in under 100 servers to receive message content, found in Bot
     * Settings
     */
    GATEWAY_MESSAGE_CONTENT_LIMITED(1 << 19);

    private final int value;

    Flags(int value) {
        this.value = value;
    }

    @NotNull
    public static Flags[] getFlags(int value) {
        Flags[] flags = new Flags[0];
        for (Flags flag : values()) {
            if ((value & flag.getValue()) == flag.getValue()) {
                flags = add(flags, flag);
            }
        }
        return flags;
    }

    public static @Nullable Flags getFlag(int value) {
        for (Flags flag : values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        return null;
    }

    @NotNull
    private static Flags[] add(@NotNull Flags[] flags, Flags flag) {
        Flags[] newFlags = new Flags[flags.length + 1];
        System.arraycopy(flags, 0, newFlags, 0, flags.length);
        newFlags[flags.length] = flag;
        return newFlags;
    }

    public static int getValue(@NotNull Flags[] flags) {
        int value = 0;
        for (Flags flag : flags) {
            value |= flag.getValue();
        }
        return value;
    }

    @Contract(pure = true)
    public static boolean contains(@NotNull Flags[] flags, Flags flag) {
        for (Flags f : flags) {
            if (f == flag) {
                return true;
            }
        }
        return false;
    }

    public int getValue() {
        return value;
    }
}
