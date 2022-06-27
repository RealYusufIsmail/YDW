package io.github.realyusufismail.ydwreg.handle.handles.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class GuildRoleUpdateHandler extends Handle {
    public GuildRoleUpdateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    public void start() {}
}
