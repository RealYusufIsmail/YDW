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

package io.github.realyusufismail.ydwreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.activity.ActivityAsset;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ActivityAssetReg implements ActivityAsset {

    private final String largeImage;
    private final String largeText;
    private final String smallImage;
    private final String smallText;

    public ActivityAssetReg(@NotNull JsonNode asset) {
        this.largeImage =
                asset.hasNonNull("large_image") ? asset.get("large_image").asText() : null;
        this.largeText = asset.hasNonNull("large_text") ? asset.get("large_text").asText() : null;
        this.smallImage =
                asset.hasNonNull("small_image") ? asset.get("small_image").asText() : null;
        this.smallText = asset.hasNonNull("small_text") ? asset.get("small_text").asText() : null;
    }

    @NotNull
    @Override
    public Optional<String> getLargeImage() {
        return Optional.ofNullable(largeImage);
    }

    @NotNull
    @Override
    public Optional<String> getLargeText() {
        return Optional.ofNullable(largeText);
    }

    @NotNull
    @Override
    public Optional<String> getSmallImage() {
        return Optional.ofNullable(smallImage);
    }

    @NotNull
    @Override
    public Optional<String> getSmallText() {
        return Optional.ofNullable(smallText);
    }
}
