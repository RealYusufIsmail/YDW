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

import api.ydl.snowflake.SnowFlake;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonUtils {
    private JsonUtils() {}

    public static <T> @NotNull Stream<T> stream(@NotNull Spliterator<T> spliterator) {
        return StreamSupport.stream(spliterator, false);
    }

    public static <T> @NotNull Stream<T> stream(@NotNull Iterable<T> iterable) {
        return stream(iterable.spliterator());
    }

    public static <T> @NotNull ArrayList<T> list(@NotNull Spliterator<T> spliterator) {
        return StreamSupport.stream(spliterator, false)
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Contract(pure = true)
    public static boolean isNull(@NotNull Object object) {
        return object == null;
    }

    @Contract(pure = true)
    public static boolean isNotNull(@NotNull Object object) {
        return object != null;
    }

    @Contract(pure = true)
    public static boolean isEmpty(@NotNull String string) {
        return string.isEmpty();
    }

    @Contract(pure = true)
    public static boolean isNotEmpty(@NotNull String string) {
        return !string.isEmpty();
    }

    @Contract(pure = true)
    public static boolean isEmpty(@NotNull Iterable<?> iterable) {
        return iterable.spliterator().getExactSizeIfKnown() == 0;
    }

    public static @NotNull List<SnowFlake> getSnowFlakeList(@NotNull JsonElement ids) {
        return stream(ids.getAsArrayNode().spliterator()).map(JsonElement:.asText())
            .flatMap(SnowFlake::parse)
                .toList();
    }

    public static JsonElement getJsonElement(@NotNull JsonNode JsonNode, String key) {
        return JsonNode.get(key);
    }

    public static Optional<JsonElement> resolve(JsonElement key) {
        return Optional.ofNullable(key);
    }
}
