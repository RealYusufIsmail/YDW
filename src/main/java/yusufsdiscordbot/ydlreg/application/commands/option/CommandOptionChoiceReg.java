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

package yusufsdiscordbot.ydlreg.application.commands.option;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.application.commands.option.CommandOptionChoice;

public class CommandOptionChoiceReg implements CommandOptionChoice {
    private final String name;
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
