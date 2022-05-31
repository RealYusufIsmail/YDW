/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
 */

package io.github.realyusufismail.ydwreg.rest.callers;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.entities.sticker.StickerPack;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerPackReg;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerReg;
import io.github.realyusufismail.ydwreg.rest.exception.RestApiException;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StickerCaller {
    @NotNull
    private final YDWReg ydw;

    private final OkHttpClient client;

    public StickerCaller(@NotNull YDWReg ydw) {
        this.ydw = ydw;
        this.client = ydw.getHttpClient();
    }

    @Nullable
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
                JsonNode json = ydw.getMapper().readTree(body.string());
                List<StickerPack> packs = new ArrayList<>();
                for (var pack : json) {
                    packs.add(new StickerPackReg(pack, ydw, pack.get("id").asLong()));
                }
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
        return null;
    }

    @NotNull
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
                JsonNode json = ydw.getMapper().readTree(body.string());
                List<Sticker> stickers = new ArrayList<>();
                for (var sticker : json) {
                    stickers.add(new StickerReg(sticker, sticker.get("id").asLong(), ydw));
                }
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
        return new ArrayList<>();
    }

    @Nullable
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
                JsonNode json = ydw.getMapper().readTree(body.string());
                return new StickerReg(json, stickerId, ydw);
            }
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }
}
