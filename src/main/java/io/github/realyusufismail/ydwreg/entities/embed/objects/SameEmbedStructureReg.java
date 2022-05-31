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
import io.github.realyusufismail.ydw.entities.embed.objects.SameEmbedStructure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SameEmbedStructureReg implements SameEmbedStructure {

    @Nullable
    private String url;
    @Nullable
    private String proxyUrl;
    @Nullable
    private Integer height;
    @Nullable
    private Integer width;

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
}
