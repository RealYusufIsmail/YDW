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

package io.github.realyusufismail.ydw.activity;

import org.jetbrains.annotations.NotNull;

public enum ActivityFlag {
    INSTANCE(1 << 0),
    JOIN(1 << 1),
    SPECTATE(1 << 2),
    JOIN_REQUEST(1 << 3),
    SYNC(1 << 4),
    PLAY(1 << 5),
    PARTY_PRIVACY_FRIENDS(1 << 6),
    PARTY_PRIVACY_VOICE_CHANNEL(1 << 7),
    EMBEDDED(1 << 8),
    UNKNOWN(-1);

    private final int value;

    private ActivityFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @NotNull
    public static ActivityFlag getFlag(int value) {
        for (ActivityFlag flag : values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        return UNKNOWN;
    }
}
