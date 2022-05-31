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

package io.github.realyusufismail.ydw.entities.guild;

import org.jetbrains.annotations.NotNull;

public enum SystemChannelFlags {
    /**
     * Suppress member join notifications.
     */
    SUPPRESS_JOIN_NOTIFICATIONS(1 << 0),
    /**
     * Suppress server boost notifications.
     */
    SUPPRESS_PREMIUM_SUBSCRIPTIONS(1 << 1),
    /**
     * Suppress server setup tips.
     */
    SUPPRESS_GUILD_REMINDER_NOTIFICATIONS(1 << 2),
    /**
     * Hide member join sticker reply buttons.
     */
    SUPPRESS_JOIN_NOTIFICATION_REPLIES(1 << 3),
    UNKNOWN(-1);

    private final int value;

    SystemChannelFlags(int value) {
        this.value = value;
    }

    @NotNull
    public static SystemChannelFlags fromValue(int value) {
        for (SystemChannelFlags flag : values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }
}
