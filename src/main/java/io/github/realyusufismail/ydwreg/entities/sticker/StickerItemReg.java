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
