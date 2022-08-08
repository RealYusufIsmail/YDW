/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.rest.callers;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.emoji.Emoji;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.emoji.EmojiReg;
import io.github.realyusufismail.ydwreg.rest.error.RestApiError;
import io.github.realyusufismail.ydwreg.rest.exception.RestApiException;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import io.github.realyusufismail.ydwreg.rest.request.YDWRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmojiCaller {

    private final YDWReg ydw;
    private final OkHttpClient client;
    private final String token;
    private ResponseBody body = null;

    public EmojiCaller(String token, @Nullable YDW ydw, OkHttpClient client) {
        this.ydw = (YDWReg) ydw;
        this.client = client;
        this.token = token;
    }

    @NotNull
    public List<Emoji> getGuildEmojis(long guildId) {
        Request request = new YDWRequest()
            .request(token, EndPoint.GET_LIST_GUILD_EMOJI.getFullEndpoint(guildId))
            .get()
            .build();
        try (var response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());

            body = response.body();

            JsonNode json = ydw.getMapper().readTree(body.string());
            List<Emoji> emojis = new ArrayList<>();
            for (JsonNode node : json) {
                emojis.add(new EmojiReg(node, node.get("id").asLong(), ydw));
            }
            return emojis;
        } catch (IOException e) {
            throw new RestApiException(e);
        } finally {
            if (body != null)
                body.close();
        }
    }

    @Nullable
    public Emoji getGuildEmoji(long guildId, long emojiId) {
        Request request = new Request.Builder()
            .url(EndPoint.GET_GUILD_EMOJI.getFullEndpoint(guildId, emojiId))
            .get()
            .build();
        try (var response = client.newCall(request).execute()) {

            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());

            var body = response.body();
            JsonNode json = ydw.getMapper().readTree(body.string());
            return new EmojiReg(json, json.get("id").asLong(), ydw);
        } catch (IOException e) {
            throw new RestApiException(e);
        } finally {
            if (body != null)
                body.close();
        }
    }
}
