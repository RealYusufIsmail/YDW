package io.github.realyusufismail.websocket.handle;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.YDLReg;

public abstract class Handle {
    protected final JsonNode json;
    protected final YDLReg ydl;

    protected Handle(JsonNode json, YDL ydl) {
        this.json = json;
        this.ydl = (YDLReg) ydl;
    }

    public abstract void start();
}
