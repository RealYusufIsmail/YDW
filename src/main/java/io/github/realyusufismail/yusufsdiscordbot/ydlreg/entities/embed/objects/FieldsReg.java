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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.embed.objects;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.embed.objects.Fields;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class FieldsReg implements Fields {

    private final String name;
    private final String value;
    private final Boolean inline;

    public FieldsReg(@NotNull JsonNode fields) {
        this.name = fields.hasNonNull("name") ? fields.get("name").asText() : null;
        this.value = fields.hasNonNull("value") ? fields.get("value").asText() : null;
        this.inline = fields.hasNonNull("inline") ? fields.get("inline").asBoolean() : null;
    }

    public FieldsReg(@NotNull String name, @NotNull String value, Boolean inline) {
        this.name = name;
        this.value = value;
        this.inline = inline;
    }

    @NotNull
    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @NotNull
    @Override
    public Optional<String> getValue() {
        return Optional.ofNullable(value);
    }

    @NotNull
    @Override
    public Optional<Boolean> isInline() {
        return Optional.ofNullable(inline);
    }
}
