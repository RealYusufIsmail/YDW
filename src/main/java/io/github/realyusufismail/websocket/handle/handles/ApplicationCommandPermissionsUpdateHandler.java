package io.github.realyusufismail.websocket.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;

public class ApplicationCommandPermissionsUpdateHandler extends Handle {
    public ApplicationCommandPermissionsUpdateHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}
