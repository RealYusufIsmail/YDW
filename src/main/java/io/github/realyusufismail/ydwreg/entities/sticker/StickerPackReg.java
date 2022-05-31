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

package io.github.realyusufismail.ydwreg.entities.sticker;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.entities.sticker.StickerPack;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
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

    public StickerPackReg(@NotNull JsonNode sticker, @NotNull YDW ydw, long id) {
        this.id = id;

        this.name = sticker.get("name").asText();
        this.description = sticker.get("description").asText();
        this.skuId = sticker.get("skuId").asLong();
        this.coverStickerId = sticker.get("coverStickerId").asLong();
        this.bannerAssetId = sticker.get("bannerAssetId").asLong();

        if (sticker.has("stickers")) {
            for (JsonNode s : sticker.get("stickers")) {
                stickers.add(new StickerReg(s, s.get("id").asLong(), ydw));
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
