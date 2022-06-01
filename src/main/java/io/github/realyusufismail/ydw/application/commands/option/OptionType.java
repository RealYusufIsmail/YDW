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

package io.github.realyusufismail.ydw.application.commands.option;

import org.jetbrains.annotations.NotNull;

public enum OptionType {
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

    OptionType(int value) {
        this.value = value;
    }

    public static @NotNull OptionType getOptionType(int options) {
        for (OptionType type : values()) {
            if (type.getValue() == options) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }

    public @NotNull OptionType getType(int value) {
        for (OptionType type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
