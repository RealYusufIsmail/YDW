package io.github.realyusufismail.ydwreg.handle.handles.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.Interaction;
import io.github.realyusufismail.ydwreg.application.InteractionReg;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class InteractionCreateHandler extends Handle {
    public InteractionCreateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        Interaction interaction = new InteractionReg(json, json.get("id").asLong(), ydw);
        ydw.getInteractionManager().addInteraction(interaction);
    }
}
