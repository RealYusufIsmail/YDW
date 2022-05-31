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

package io.github.realyusufismail.ydwreg.entities.sticker;

import org.jetbrains.annotations.NotNull;

public enum StickerType {
    /**
     * an official sticker in a pack, part of Nitro or in a removed purchasable pack.
     */
    STANDARD(1),
    /**
     * a sticker uploaded to a Boosted guild for the guild's members.
     */
    GUILD(2),
    /**
     * For future use or invalid values.
     */
    UNKNOWN(-1);

    private final int id;

    StickerType(final int id) {
        this.id = id;
    }

    public static @NotNull StickerType valueOf(int value) {
        for (StickerType type : StickerType.values()) {
            if (type.getId() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getId() {
        return this.id;
    }
}
