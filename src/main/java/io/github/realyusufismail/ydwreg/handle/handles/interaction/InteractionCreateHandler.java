package io.github.realyusufismail.ydwreg.handle.handles.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.Interaction;
import io.github.realyusufismail.ydw.application.interaction.InteractionType;
import io.github.realyusufismail.ydw.event.events.interaction.SlashCommandInteractionEvent;
import io.github.realyusufismail.ydwreg.application.InteractionReg;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class InteractionCreateHandler extends Handle {
    public InteractionCreateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        Interaction interaction = new InteractionReg(json, json.get("id").asLong(), ydw);
        if (interaction.getType() == InteractionType.APPLICATION_COMMAND) {
            ydw.getRest().getSlashCommandCaller().setInteractionToken(interaction.getToken());
            ydw.handelEvent(new SlashCommandInteractionEvent(json, ydw));
        }
    }
}
