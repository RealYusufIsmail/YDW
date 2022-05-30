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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.message_components;

public enum ComponentType {
    /**
     * A container for other components.
     */
    ACTION_ROW(1),
    /**
     * A button object.
     */
    BUTTON(2),
    /**
     * A select menu for picking from choices.
     */
    SELECT_MENU(3),
    /**
     * A text input object.
     */
    TEXT_INPUT(4),
    /**
     * For any future additions.
     */
    UNKNOWN(-1);

    private final int value;

    ComponentType(int value) {
        this.value = value;
    }

    public static ComponentType getComponentType(int type) {
        for (ComponentType c : ComponentType.values()) {
            if (c.getValue() == type) {
                return c;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }
}
