package io.github.realyusufismail.yusufsdiscordbot.ydlreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.activity.ActivityButton;
import org.jetbrains.annotations.NotNull;

public class ActivityButtonReg implements ActivityButton {

    private final String label;
    private final String url;

    public ActivityButtonReg(@NotNull JsonNode button) {
        this.label = button.get("label").asText();
        this.url = button.get("url").asText();
    }


    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
