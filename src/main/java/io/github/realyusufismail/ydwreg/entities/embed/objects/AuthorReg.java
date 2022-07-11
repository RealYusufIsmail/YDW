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
import io.github.realyusufismail.ydw.entities.embed.objects.Author;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AuthorReg implements Author {

    @Nullable
    private final String name;
    @Nullable
    private final String url;
    private final String iconUrl;
    private final String proxyIconUrl;

    public AuthorReg(@NotNull JsonNode author) {

        this.name = author.hasNonNull("name") ? author.get("name").asText() : null;
        this.url = author.hasNonNull("url") ? author.get("url").asText() : null;
        this.iconUrl = author.hasNonNull("icon_url") ? author.get("icon_url").asText() : null;
        this.proxyIconUrl =
                author.hasNonNull("proxy_icon_url") ? author.get("proxy_icon_url").asText() : null;
    }

    public AuthorReg(@Nullable String name, @Nullable String url, String iconUrl,
            String proxyIconUrl) {
        this.name = name;
        this.url = url;
        this.iconUrl = iconUrl;
        this.proxyIconUrl = proxyIconUrl;
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

    @NotNull
    @Override
    public Optional<String> getIconUrl() {
        return Optional.ofNullable(iconUrl);
    }

    @NotNull
    @Override
    public Optional<String> getProxyIconUrl() {
        return Optional.ofNullable(proxyIconUrl);
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode author = JsonNodeFactory.instance.objectNode();
        getName().ifPresent(name -> author.put("name", name));
        getUrl().ifPresent(url -> author.put("url", url));
        getIconUrl().ifPresent(iconUrl -> author.put("icon_url", iconUrl));
        getProxyIconUrl().ifPresent(proxyIconUrl -> author.put("proxy_icon_url", proxyIconUrl));
        return author;
    }
}
