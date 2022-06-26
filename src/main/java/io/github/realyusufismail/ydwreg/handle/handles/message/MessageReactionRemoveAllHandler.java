package io.github.realyusufismail.ydwreg.handle.handles.message;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class MessageReactionRemoveAllHandler extends Handle {

    public MessageReactionRemoveAllHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {

    }
}
