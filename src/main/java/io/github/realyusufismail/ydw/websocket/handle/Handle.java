package io.github.realyusufismail.ydw.websocket.handle;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydlreg.YDLReg;

public abstract class Handle {
    protected final JsonNode json;
    protected final YDLReg ydl;

    protected Handle(JsonNode json, YDL ydl) {
        this.json = json;
        this.ydl = (YDLReg) ydl;
    }

    public abstract void start();
}
