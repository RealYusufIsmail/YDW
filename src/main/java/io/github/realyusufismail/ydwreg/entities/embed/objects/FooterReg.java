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
import io.github.realyusufismail.ydw.entities.embed.objects.Footer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class FooterReg implements Footer {

    private final String text;
    private final String iconUrl;
    private final String proxyIconUrl;

    public FooterReg(@NotNull JsonNode footer) {

        this.text = footer.hasNonNull("text") ? footer.get("text").asText() : null;
        this.iconUrl = footer.hasNonNull("icon_url") ? footer.get("icon_url").asText() : null;
        this.proxyIconUrl =
                footer.hasNonNull("proxy_icon_url") ? footer.get("proxy_icon_url").asText() : null;
    }

    public FooterReg(String text, String iconUrl, String proxyIconUrl) {
        this.text = text;
        this.iconUrl = iconUrl;
        this.proxyIconUrl = proxyIconUrl;
    }


    @NotNull
    @Override
    public Optional<String> getText() {
        return Optional.ofNullable(text);
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
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("text", text);
        objectNode.put("icon_url", iconUrl);
        objectNode.put("proxy_icon_url", proxyIconUrl);
        return objectNode;
    }
}
