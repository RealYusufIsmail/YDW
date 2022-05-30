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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.callers;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.emoji.Emoji;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.YDLReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.emoji.EmojiReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.exception.RestApiException;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.name.EndPoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmojiCaller {
    private final YDLReg ydl;

    private final OkHttpClient client;

    public EmojiCaller(@NotNull YDL ydl) {
        this.ydl = (YDLReg) ydl;
        this.client = ((YDLReg) ydl).getHttpClient();
    }

    @NotNull
    public List<Emoji> getGuildEmojis(long guildId) {
        Request request =
                new Request.Builder().url(EndPoint.GET_LIST_GUILD_EMOJI.getFullEndpoint(guildId))
                    .get()
                    .build();
        try (var response = client.newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return new ArrayList<>();
            } else {
                JsonNode json = ydl.getMapper().readTree(body.string());
                List<Emoji> emojis = new ArrayList<>();
                for (JsonNode node : json) {
                    emojis.add(new EmojiReg(node, node.get("id").asLong(), ydl));
                }
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
        return new ArrayList<>();
    }

    @Nullable
    public Emoji getGuildEmoji(long guildId, long emojiId) {
        Request request = new Request.Builder()
            .url(EndPoint.GET_GUILD_EMOJI.getFullEndpoint(guildId, emojiId))
            .get()
            .build();
        try (var response = client.newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return null;
            } else {
                JsonNode json = ydl.getMapper().readTree(body.string());
                return new EmojiReg(json, json.get("id").asLong(), ydl);
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }
}
