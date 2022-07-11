/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import io.github.realyusufismail.ydw.entities.embed.objects.SameEmbedStructure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SameEmbedStructureReg implements SameEmbedStructure {

    @Nullable
    private final String url;
    @Nullable
    private final String proxyUrl;
    @Nullable
    private final Integer height;
    @Nullable
    private final Integer width;

    public SameEmbedStructureReg(@NotNull JsonNode embed) {
        this.url = embed.hasNonNull("url") ? embed.get("url").asText() : null;
        this.proxyUrl = embed.hasNonNull("proxy_url") ? embed.get("proxy_url").asText() : null;
        this.height = embed.hasNonNull("height") ? embed.get("height").asInt() : null;
        this.width = embed.hasNonNull("width") ? embed.get("width").asInt() : null;
    }

    public SameEmbedStructureReg(@Nullable String url, @Nullable String proxyUrl,
            @Nullable Integer height, @Nullable Integer width) {
        this.url = url;
        this.proxyUrl = proxyUrl;
        this.height = height;
        this.width = width;
    }

    @NotNull
    @Override
    public Optional<String> getUrl() {
        return Optional.empty();
    }

    @NotNull
    @Override
    public Optional<String> getProxyUrl() {
        return Optional.empty();
    }

    @NotNull
    @Override
    public Optional<Integer> getHeight() {
        return Optional.empty();
    }

    @NotNull
    @Override
    public Optional<Integer> getWidth() {
        return Optional.empty();
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        getUrl().ifPresent(url -> json.put("url", url));
        getProxyUrl().ifPresent(proxyUrl -> json.put("proxy_url", proxyUrl));
        getHeight().ifPresent(height -> json.put("height", height));
        getWidth().ifPresent(width -> json.put("width", width));
        return json;
    }
}
