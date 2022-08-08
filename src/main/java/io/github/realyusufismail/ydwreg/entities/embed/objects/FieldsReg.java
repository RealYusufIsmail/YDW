/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.entities.embed.objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    @Override
    public ObjectNode toJson() {
        ObjectNode fields = JsonNodeFactory.instance.objectNode();
        getName().ifPresent(n -> fields.put("name", n));
        getValue().ifPresent(v -> fields.put("value", v));
        isInline().ifPresent(i -> fields.put("inline", i));
        return fields;


    }
}
