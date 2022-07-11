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

public enum StickerFormatType {
    PNG(1),
    APNG(2),
    LOTTIE(3),
    /**
     * For future use or invalid formats.
     */
    UNKNOWN(-1);

    private final int value;

    StickerFormatType(int value) {
        this.value = value;
    }

    public static @NotNull StickerFormatType valueOf(int value) {
        for (StickerFormatType type : StickerFormatType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }
}
