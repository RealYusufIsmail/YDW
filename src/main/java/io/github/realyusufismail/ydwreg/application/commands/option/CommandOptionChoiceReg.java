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

package io.github.realyusufismail.ydwreg.application.commands.option;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.application.commands.option.CommandOptionChoice;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandOptionChoiceReg implements CommandOptionChoice {
    private final String name;
    @Nullable
    private final Object value;

    public CommandOptionChoiceReg(@NotNull JsonNode node) {
        this.name = node.get("name").asText();
        var valueNode = node.get("value");

        if (valueNode.isTextual()) {
            this.value = valueNode.asText();
        } else if (valueNode.isLong()) {
            this.value = valueNode.asLong();
        } else if (valueNode.isDouble()) {
            this.value = valueNode.asDouble();
        } else {
            this.value = null;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
