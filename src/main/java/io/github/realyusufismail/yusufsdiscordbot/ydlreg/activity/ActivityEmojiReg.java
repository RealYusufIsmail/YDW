package io.github.realyusufismail.yusufsdiscordbot.ydlreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.activity.ActivityEmoji;

import java.util.Optional;

public class ActivityEmojiReg implements ActivityEmoji {

    private final long id;
    private final String name;
    private final Boolean animated;

    public ActivityEmojiReg(JsonNode emoji, long id) {
        this.id = id;
        this.name = emoji.get("name").asText();
        this.animated = emoji.hasNonNull("animated") && emoji.get("animated").asBoolean();
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<Boolean> isAnimated() {
        return Optional.ofNullable(animated);
    }

    @Override
    public Long getIdLong() {
        return id;
    }
}
