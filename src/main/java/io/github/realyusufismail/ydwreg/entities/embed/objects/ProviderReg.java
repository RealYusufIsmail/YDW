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
import io.github.realyusufismail.ydw.entities.embed.objects.Provider;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ProviderReg implements Provider {

    private final String name;
    private final String url;

    public ProviderReg(@NotNull JsonNode provider) {

        this.name = provider.hasNonNull("name") ? provider.get("name").asText() : null;
        this.url = provider.hasNonNull("url") ? provider.get("url").asText() : null;
    }

    public ProviderReg(@NotNull String name, @NotNull String url) {
        this.name = name;
        this.url = url;
    }

    @NotNull
    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @NotNull
    @Override
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        getName().ifPresent(name -> json.put("name", name));
        getUrl().ifPresent(url -> json.put("url", url));
        return json;
    }
}
