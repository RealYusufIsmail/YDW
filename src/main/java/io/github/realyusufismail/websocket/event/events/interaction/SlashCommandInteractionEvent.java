package io.github.realyusufismail.websocket.event.events.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.application.commands.slash.SlashCommandReg;

public class SlashCommandInteractionEvent extends SlashCommandReg {
    private final YDW ydw;

    public SlashCommandInteractionEvent(JsonNode slashCommand, YDW ydw) {
        super(slashCommand, slashCommand.get("id").asLong(), ydw);
        this.ydw = ydw;
    }
}
