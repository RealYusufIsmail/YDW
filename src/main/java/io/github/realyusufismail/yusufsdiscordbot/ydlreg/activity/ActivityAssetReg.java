package io.github.realyusufismail.yusufsdiscordbot.ydlreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.activity.ActivityAsset;
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
