package io.github.realyusufismail.ydw.ydlreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.activity.ActivityEmoji;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ActivityEmojiReg implements ActivityEmoji {

    private final long id;
    private final String name;
    @NotNull
    private final Boolean animated;

    public ActivityEmojiReg(@NotNull JsonNode emoji, long id) {
        this.id = id;
        this.name = emoji.get("name").asText();
        this.animated = emoji.hasNonNull("animated") && emoji.get("animated").asBoolean();
    }


    @Override
    public String getName() {
        return name;
    }

    @NotNull
    @Override
    public Optional<Boolean> isAnimated() {
        return Optional.ofNullable(animated);
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
