/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.rest.callers;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.entities.sticker.StickerPack;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerPackReg;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerReg;
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

public class StickerCaller {

    @NotNull
    private final YDWReg ydw;
    private final OkHttpClient client;
    private final String token;
    private ResponseBody body = null;

    public StickerCaller(String token, YDWReg ydw, OkHttpClient client) {
        this.ydw = ydw;
        this.client = client;
        this.token = token;
    }

    @Nullable
    public List<StickerPack> getStickerPacks() {
        Request request = new YDWRequest()
            .request(token, EndPoint.GET_STICKERS_AVAILABLE_FOR_NITRO.getEndpoint())
            .get()
            .build();
        try (var response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());
            var body = response.body();

            JsonNode json = ydw.getMapper().readTree(body.string());
            List<StickerPack> packs = new ArrayList<>();
            for (var pack : json) {
                packs.add(new StickerPackReg(pack, ydw, pack.get("id").asLong()));
            }
            return packs;
        } catch (IOException e) {
            throw new RestApiException(e);
        } finally {
            if (body != null)
                body.close();
        }
    }

    @NotNull
    public List<Sticker> getStickers(long guildId) {
        Request request = new Request.Builder()
            .url(EndPoint.GET_STICKERS_IN_GUILD.getFullEndpoint(String.valueOf(guildId)))
            .get()
            .build();
        try (var response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());
            var body = response.body();

            JsonNode json = ydw.getMapper().readTree(body.string());
            List<Sticker> stickers = new ArrayList<>();
            for (var sticker : json) {
                stickers.add(new StickerReg(sticker, sticker.get("id").asLong(), ydw));
            }
            return stickers;
        } catch (IOException e) {
            throw new RestApiException(e);
        } finally {
            if (body != null)
                body.close();
        }
    }

    @Nullable
    public Sticker getSticker(long guildId, long stickerId) {
        Request request = new Request.Builder()
            .url(EndPoint.GET_GUILD_STICKER.getFullEndpoint(String.valueOf(guildId),
                    String.valueOf(stickerId)))
            .get()
            .build();
        try (var response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + RestApiError.fromCode(response.code())
                        + " " + RestApiError.fromCode(response.code()).getMessage());
            var body = response.body();

            JsonNode json = ydw.getMapper().readTree(body.string());
            return new StickerReg(json, stickerId, ydw);
        } catch (IOException e) {
            throw new RestApiException(e);
        } finally {
            if (body != null)
                body.close();
        }
    }
}
