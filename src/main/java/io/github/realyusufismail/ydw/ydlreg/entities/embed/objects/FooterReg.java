/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package io.github.realyusufismail.ydw.ydlreg.entities.embed.objects;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.entities.embed.objects.Footer;
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
