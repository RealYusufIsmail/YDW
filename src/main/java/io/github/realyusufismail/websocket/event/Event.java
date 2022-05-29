package io.github.realyusufismail.websocket.event;

import com.fasterxml.jackson.databind.JsonNode;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydlreg.YDLReg;

public abstract class Event {
    protected final JsonNode json;
    protected final YDLReg ydl;

    protected Event(JsonNode json, YDL ydl) {
        this.json = json;
        this.ydl = (YDLReg) ydl;
    }

    public abstract void start();
}
