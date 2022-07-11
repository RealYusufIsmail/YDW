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
            
package io.github.realyusufismail.ydwreg.entities.sticker;

import org.jetbrains.annotations.NotNull;

public enum StickerType {
    /**
     * an official sticker in a pack, part of Nitro or in a removed purchasable pack.
     */
    STANDARD(1),
    /**
     * a sticker uploaded to a Boosted guild for the guild's members.
     */
    GUILD(2),
    /**
     * For future use or invalid values.
     */
    UNKNOWN(-1);

    private final int id;

    StickerType(final int id) {
        this.id = id;
    }

    public static @NotNull StickerType valueOf(int value) {
        for (StickerType type : StickerType.values()) {
            if (type.getId() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getId() {
        return this.id;
    }
}
