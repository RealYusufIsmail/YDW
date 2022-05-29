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

package yusufsdiscordbot.ydlreg.entities.user;

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
