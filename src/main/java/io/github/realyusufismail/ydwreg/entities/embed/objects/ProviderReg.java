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
