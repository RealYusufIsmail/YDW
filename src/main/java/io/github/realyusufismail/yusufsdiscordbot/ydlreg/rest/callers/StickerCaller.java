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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.sticker.Sticker;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.sticker.StickerPack;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.YDLReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.sticker.StickerPackReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.sticker.StickerReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.exception.RestApiException;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.name.EndPoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StickerCaller {
    private final YDLReg ydl;

    private final OkHttpClient client;

    public StickerCaller(@NotNull YDLReg ydl) {
        this.ydl = ydl;
        this.client = ydl.getHttpClient();
    }

    public List<StickerPack> getStickerPacks() {
        Request request =
                new Request.Builder().url(EndPoint.GET_STICKERS_AVAILABLE_FOR_NITRO.getEndpoint())
                    .get()
                    .build();
        try (var response = client.newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return null;
            } else {
                JsonNode json = ydl.getMapper().readTree(body.string());
                List<StickerPack> packs = new ArrayList<>();
                for (var pack : json) {
                    packs.add(new StickerPackReg(pack, ydl, pack.get("id").asLong()));
                }
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
        return null;
    }

    public List<Sticker> getStickers(long guildId) {
        Request request = new Request.Builder()
            .url(EndPoint.GET_STICKERS_IN_GUILD.getFullEndpoint(String.valueOf(guildId)))
            .get()
            .build();
        try (var response = client.newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return new ArrayList<>();
            } else {
                JsonNode json = ydl.getMapper().readTree(body.string());
                List<Sticker> stickers = new ArrayList<>();
                for (var sticker : json) {
                    stickers.add(new StickerReg(sticker, sticker.get("id").asLong(), ydl));
                }
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
        return new ArrayList<>();
    }

    public Sticker getSticker(long guildId, long stickerId) {
        Request request = new Request.Builder()
            .url(EndPoint.GET_GUILD_STICKER.getFullEndpoint(String.valueOf(guildId),
                    String.valueOf(stickerId)))
            .get()
            .build();
        try (var response = client.newCall(request).execute()) {
            var body = response.body();
            if (body == null) {
                return null;
            } else {
                JsonNode json = ydl.getMapper().readTree(body.string());
                return new StickerReg(json, stickerId, ydl);
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }
}
