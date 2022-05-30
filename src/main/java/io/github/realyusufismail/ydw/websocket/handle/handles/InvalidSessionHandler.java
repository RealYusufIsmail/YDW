package io.github.realyusufismail.ydw.websocket.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.websocket.handle.Handle;
import io.github.realyusufismail.ydw.ydl.YDL;

public class InvalidSessionHandler extends Handle {
    public InvalidSessionHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {
        Boolean resumable = json.get("d").asBoolean();
        ydl.setResumable(resumable);
    }
}
