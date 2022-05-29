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

package yusufsdiscordbot.ydlreg.entities.sticker;

import api.ydl.snowflake.SnowFlake;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.sticker.Sticker;
import yusufsdiscordbot.ydl.entities.sticker.StickerPack;

import java.util.ArrayList;
import java.util.List;

public class StickerPackReg implements StickerPack {
    private final long id;

    private final List<Sticker> stickers = new ArrayList<>();
    private final String name;
    private final String description;
    private final Long skuId;
    private final Long coverStickerId;
    private final Long bannerAssetId;

    public StickerPackReg(@NotNull JsonNode sticker, YDL ydl, long id) {
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

    @Override
    public Long getIdLong() {
        return id;
    }

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

    @Override
    public SnowFlake getSkuId() {
        return SnowFlake.of(skuId);
    }

    @Override
    public SnowFlake getCoverStickerId() {
        return SnowFlake.of(coverStickerId);
    }

    @Override
    public SnowFlake getBannerAssetId() {
        return SnowFlake.of(bannerAssetId);
    }
}
