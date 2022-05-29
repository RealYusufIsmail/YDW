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

package yusufsdiscordbot.ydlreg.entities.message;

public enum MessageActivityType {
    JOIN(1),
    SPECTATE(2),
    LISTEN(3),
    JOIN_REQUEST(4),
    UNKNOWN(-1);

    private final int value;

    MessageActivityType(int value) {
        this.value = value;
    }

    public static MessageActivityType fromInt(int i) {
        for (MessageActivityType type : values()) {
            if (type.getValue() == i) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }
}
