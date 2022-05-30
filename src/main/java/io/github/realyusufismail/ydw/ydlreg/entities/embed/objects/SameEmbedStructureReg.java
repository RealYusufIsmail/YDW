package io.github.realyusufismail.ydw.ydlreg.entities.embed.objects;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.entities.embed.objects.SameEmbedStructure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SameEmbedStructureReg implements SameEmbedStructure {

    @Nullable
    private String url;
    @Nullable
    private String proxyUrl;
    @Nullable
    private Integer height;
    @Nullable
    private Integer width;

    public SameEmbedStructureReg(@NotNull JsonNode embed) {
        this.url = embed.hasNonNull("url") ? embed.get("url").asText() : null;
        this.proxyUrl = embed.hasNonNull("proxy_url") ? embed.get("proxy_url").asText() : null;
        this.height = embed.hasNonNull("height") ? embed.get("height").asInt() : null;
        this.width = embed.hasNonNull("width") ? embed.get("width").asInt() : null;
    }

    public SameEmbedStructureReg(@Nullable String url, @Nullable String proxyUrl,
            @Nullable Integer height, @Nullable Integer width) {
        this.url = url;
        this.proxyUrl = proxyUrl;
        this.height = height;
        this.width = width;
    }

    @NotNull
    @Override
    public Optional<String> getUrl() {
        return Optional.empty();
    }

    @NotNull
    @Override
    public Optional<String> getProxyUrl() {
        return Optional.empty();
    }

    @NotNull
    @Override
    public Optional<Integer> getHeight() {
        return Optional.empty();
    }

    @NotNull
    @Override
    public Optional<Integer> getWidth() {
        return Optional.empty();
    }
}
