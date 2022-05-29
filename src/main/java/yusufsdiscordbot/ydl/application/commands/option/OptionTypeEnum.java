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

package yusufsdiscordbot.ydl.application.commands.option;

import org.jetbrains.annotations.NotNull;

public enum OptionTypeEnum {
    SUB_COMMAND(1),
    SUB_COMMAND_GROUP(2),
    STRING(3),
    INTEGER(4),
    BOOLEAN(5),
    USER(6),
    CHANNEL(7),
    ROLE(8),
    MENTIONABLE(9),
    NUMBER(10),
    ATTACHMENT(11),
    UNKNOWN(-1);

    private final int value;

    OptionTypeEnum(int value) {
        this.value = value;
    }

    public static @NotNull OptionTypeEnum getType(String value) {
        for (OptionTypeEnum type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static @NotNull OptionTypeEnum getOptionType(int options) {
        for (OptionTypeEnum type : values()) {
            if (type.getValue() == options) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }

    public @NotNull OptionTypeEnum getType(int value) {
        for (OptionTypeEnum type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
