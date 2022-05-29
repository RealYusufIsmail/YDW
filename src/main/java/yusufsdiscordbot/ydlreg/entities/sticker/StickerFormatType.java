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

package yusufsdiscordbot.ydlreg.entities.sticker;

import org.jetbrains.annotations.NotNull;

public enum StickerFormatType {
    PNG(1),
    APNG(2),
    LOTTIE(3),
    /**
     * For future use or invalid formats.
     */
    UNKNOWN(-1);

    private final int value;

    StickerFormatType(int value) {
        this.value = value;
    }

    public static @NotNull StickerFormatType valueOf(int value) {
        for (StickerFormatType type : StickerFormatType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }
}
