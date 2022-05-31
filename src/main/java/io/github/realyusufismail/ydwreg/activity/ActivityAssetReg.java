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
