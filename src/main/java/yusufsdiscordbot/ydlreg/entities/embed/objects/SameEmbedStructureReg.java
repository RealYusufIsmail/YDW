package yusufsdiscordbot.ydlreg.entities.embed.objects;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yusufsdiscordbot.ydl.entities.embed.objects.SameEmbedStructure;

import java.util.Optional;

public class SameEmbedStructureReg implements SameEmbedStructure {

    private String url;
    private String proxyUrl;
    private Integer height;
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

    @Override
    public Optional<String> getUrl() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getProxyUrl() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getHeight() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getWidth() {
        return Optional.empty();
    }
}
