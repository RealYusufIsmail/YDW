package io.github.realyusufismail.websocket.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import yusufsdiscordbot.ydl.YDL;

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
