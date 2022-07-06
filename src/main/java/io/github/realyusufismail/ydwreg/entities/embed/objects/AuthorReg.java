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
}
