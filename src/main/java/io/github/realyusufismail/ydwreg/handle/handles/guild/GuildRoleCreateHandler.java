package io.github.realyusufismail.ydwreg.handle.handles.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class GuildRoleCreateHandler extends Handle {
    public GuildRoleCreateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    public void start() {}
}
