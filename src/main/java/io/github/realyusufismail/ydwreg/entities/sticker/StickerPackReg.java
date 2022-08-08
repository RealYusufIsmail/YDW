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

        if (sticker.hasNonNull("stickers")) {
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
