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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.sticker;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.sticker.Sticker;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.sticker.StickerPack;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StickerPackReg implements StickerPack {
    private final long id;

    private final List<Sticker> stickers = new ArrayList<>();
    private final String name;
    private final String description;
    @NotNull
    private final Long skuId;
    @NotNull
    private final Long coverStickerId;
    @NotNull
    private final Long bannerAssetId;

    public StickerPackReg(@NotNull JsonNode sticker, @NotNull YDL ydl, long id) {
        this.id = id;

        this.name = sticker.get("name").asText();
        this.description = sticker.get("description").asText();
        this.skuId = sticker.get("skuId").asLong();
        this.coverStickerId = sticker.get("coverStickerId").asLong();
        this.bannerAssetId = sticker.get("bannerAssetId").asLong();

        if (sticker.has("stickers")) {
            for (JsonNode s : sticker.get("stickers")) {
                stickers.add(new StickerReg(s, s.get("id").asLong(), ydl));
            }
        }
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @NotNull
    @Override
    public List<Sticker> getStickers() {
        return stickers;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @NotNull
    @Override
    public SnowFlake getSkuId() {
        return SnowFlake.of(skuId);
    }

    @NotNull
    @Override
    public SnowFlake getCoverStickerId() {
        return SnowFlake.of(coverStickerId);
    }

    @NotNull
    @Override
    public SnowFlake getBannerAssetId() {
        return SnowFlake.of(bannerAssetId);
    }
}
