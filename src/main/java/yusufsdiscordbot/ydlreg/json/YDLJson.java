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

package yusufsdiscordbot.ydlreg.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class YDLJson {
    private YDLJson() {}

    /**
     * Parses a String into a {@link JsonNode}.
     *
     * @param input string to parse
     * @return JsonNode
     */
    public static @Nullable JsonNode parseObject(String input) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode payload = mapper.readTree(input);
        return payload;
    }

    // Used to parse a json array
    public static @Nullable JsonNode parseArray(String input) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode payload = mapper.readTree(input);
        return payload;
    }

    public static @NotNull Reader convertInputStreamToReader(@NotNull InputStream inputStream) {
        return new InputStreamReader(inputStream);
    }
}
