/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.github.realyusufismail.ydwreg.entities.sticker;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.sticker.StickerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StickerItemReg implements StickerItem {
    private final JsonNode item;
    private final YDW ydw;

    public StickerItemReg(JsonNode item, YDW ydw) {
        this.item = item;
        this.ydw = ydw;
    }

    /**
     * Called when the bot is ready.
     *
     * @return The ydw instance.
     */
    @Override
    public @Nullable YDW getYDW() {
        return ydw;
    }

    /**
     * @return The core long of this api.
     */
    @NotNull
    @Override
    public Long getIdLong() {
        return item.get("id").asLong();
    }

    @Override
    public String getName() {
        return item.get("name").asText();
    }

    @NotNull
    @Override
    public StickerFormatType getFormatType() {
        return StickerFormatType.valueOf(item.get("format").asInt());
    }
}
