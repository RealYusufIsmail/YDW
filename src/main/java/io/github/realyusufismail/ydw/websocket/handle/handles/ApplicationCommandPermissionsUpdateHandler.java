package io.github.realyusufismail.ydw.websocket.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.websocket.handle.Handle;
import io.github.realyusufismail.ydw.ydl.YDL;

public class ApplicationCommandPermissionsUpdateHandler extends Handle {
    public ApplicationCommandPermissionsUpdateHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {

    }
}
