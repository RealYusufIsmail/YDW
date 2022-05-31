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

package io.github.realyusufismail.ydwreg.entities.embed.objects;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.entities.embed.objects.Fields;
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
