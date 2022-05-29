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

import java.util.Optional;

public class ProviderReg implements Provider {

    private final String name;
    private final String url;

    public ProviderReg(JsonNode provider) {

        this.name = provider.hasNonNull("name") ? provider.get("name").asText() : null;
        this.url = provider.hasNonNull("url") ? provider.get("url").asText() : null;
    }


    @Override
    public Optional<String> name() {
        return Optional.ofNullable(name);
    }

    @Override
    public Optional<String> url() {
        return Optional.ofNullable(url);
    }
}
