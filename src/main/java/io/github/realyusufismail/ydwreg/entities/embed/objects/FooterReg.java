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
}
