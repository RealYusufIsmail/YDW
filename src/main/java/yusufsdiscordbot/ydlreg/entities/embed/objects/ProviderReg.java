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
import yusufsdiscordbot.ydl.entities.embed.objects.Provider;

public class ProviderReg implements Provider {
    private final JsonNode provider;
    private String name;
    private String url;

    public ProviderReg(JsonNode provider) {
        this.provider = provider;
    }

    @Override
    public String name() {
        return provider.get("name").asText();
    }

    @Override
    public String url() {
        return provider.get("url").asText();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }
}
