package io.github.realyusufismail.websocket.handle;

import com.fasterxml.jackson.databind.JsonNode;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydlreg.YDLReg;

public abstract class Handle {
    protected final JsonNode json;
    protected final YDLReg ydl;

    protected Handle(JsonNode json, YDL ydl) {
        this.json = json;
        this.ydl = (YDLReg) ydl;
    }

    public abstract void start();
}
