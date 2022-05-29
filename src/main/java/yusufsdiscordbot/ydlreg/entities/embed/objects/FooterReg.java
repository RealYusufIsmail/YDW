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

package yusufsdiscordbot.ydlreg.entities.embed.objects;

import com.fasterxml.jackson.databind.JsonNode;
import yusufsdiscordbot.ydl.entities.embed.objects.Footer;

public class FooterReg implements Footer {
    private final JsonNode footer;
    private String text;
    private String iconUrl;
    private String proxyIconUrl;

    public FooterReg(JsonNode footer) {
        this.footer = footer;
    }

    @Override
    public String getText() {
        return footer.get("text").asText();
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getIconUrl() {
        return footer.get("icon_url").asText();
    }

    @Override
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String getProxyIconUrl() {
        return footer.get("proxy_icon_url").asText();
    }

    @Override
    public void setProxyIconUrl(String proxyIconUrl) {
        this.proxyIconUrl = proxyIconUrl;
    }
}
