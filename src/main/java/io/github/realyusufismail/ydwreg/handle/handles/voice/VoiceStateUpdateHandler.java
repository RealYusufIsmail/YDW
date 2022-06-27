package io.github.realyusufismail.ydwreg.handle.handles.voice;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class VoiceStateUpdateHandler extends Handle {
    public VoiceStateUpdateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {

    }
}
