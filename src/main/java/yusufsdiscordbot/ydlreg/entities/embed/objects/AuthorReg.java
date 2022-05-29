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
import yusufsdiscordbot.ydl.entities.embed.objects.Author;

public class AuthorReg implements Author {
    private final JsonNode author;
    private String name;
    private String url;
    private String iconUrl;
    private String proxyIconUrl;

    public AuthorReg(JsonNode author) {
        this.author = author;
    }

    @Override
    public String getName() {
        return author.get("name").asText();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUrl() {
        return author.get("url").asText();
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getIconUrl() {
        return author.get("icon_url").asText();
    }

    @Override
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String getProxyIconUrl() {
        return author.get("proxy_icon_url").asText();
    }

    @Override
    public void setProxyIconUrl(String proxyIconUrl) {
        this.proxyIconUrl = proxyIconUrl;
    }

}
