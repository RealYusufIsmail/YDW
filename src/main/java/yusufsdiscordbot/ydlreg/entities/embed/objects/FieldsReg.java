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

package yusufsdiscordbot.ydlreg.entities.embed.objects;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;
import yusufsdiscordbot.ydl.entities.embed.objects.Fields;

public class FieldsReg implements Fields {
    private final @Nullable JsonNode fields;
    private String name;
    private String value;
    private boolean inline;

    public FieldsReg(JsonNode fields) {
        this.fields = fields;
    }

    public FieldsReg(String name, String value, boolean inline) {
        this.name = name;
        this.value = value;
        this.inline = inline;
        this.fields = null;
    }

    @Override
    public String getName() {
        return fields.get("name").asText();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return fields.get("value").asText();
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean isInlined() {
        return fields.get("inline").asBoolean();
    }

    @Override
    public void isInlined(boolean inlined) {
        this.inline = inlined;
    }
}
