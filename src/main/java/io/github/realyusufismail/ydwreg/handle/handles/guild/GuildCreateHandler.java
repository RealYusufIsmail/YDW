package io.github.realyusufismail.ydwreg.handle.handles.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.handle.Handle;
import org.jetbrains.annotations.NotNull;

public class GuildCreateHandler extends Handle {
    public GuildCreateHandler(@NotNull JsonNode json, @NotNull YDW ydwReg) {
        super(json, ydwReg);
    }


    @Override
    public void start() {

    }
}
