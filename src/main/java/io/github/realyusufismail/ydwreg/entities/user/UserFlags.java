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

package io.github.realyusufismail.ydwreg.entities.user;

import org.jetbrains.annotations.NotNull;

public enum UserFlags {
    NONE(0),
    STAFF(1 << 0),
    PARTNER(1 << 1),
    HYPESQUAD(1 << 2),
    BUG_HUNTER_LEVEL_1(1 << 3),
    HYPESQUAD_ONLINE_HOUSE_1(1 << 6),
    HYPESQUAD_ONLINE_HOUSE_2(1 << 7),
    HYPESQUAD_ONLINE_HOUSE_3(1 << 8),
    PREMIUM_EARLY_SUPPORTER(1 << 9),
    TEAM_PSEUDO_USER(1 << 10),
    BUG_HUNTER_LEVEL_2(1 << 14),
    VERIFIED_BOT(1 << 16),
    VERIFIED_DEVELOPER(1 << 17),
    CERTIFIED_MODERATOR(1 << 18),
    BOT_HTTP_INTERACTIONS(1 << 19),
    UNKNOWN(-1);

    private final int flags;

    UserFlags(final int flags) {
        this.flags = flags;
    }

    public static @NotNull UserFlags getFlag(int flags) {
        for (UserFlags flag : values()) {
            if (flag.getFlag() == flags) {
                return flag;
            }
        }
        return UNKNOWN;
    }

    public int getFlag() {
        return this.flags;
    }
}
