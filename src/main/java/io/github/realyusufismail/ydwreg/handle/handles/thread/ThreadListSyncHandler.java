package io.github.realyusufismail.ydwreg.handle.handles.thread;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class ThreadListSyncHandler extends Handle {
    public ThreadListSyncHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {

    }
}
