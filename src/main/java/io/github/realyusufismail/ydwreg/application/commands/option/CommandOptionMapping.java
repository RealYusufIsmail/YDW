package io.github.realyusufismail.ydwreg.application.commands.option;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.application.commands.option.CommandInteractionDataOption;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;

public class CommandOptionMapping implements CommandInteractionDataOption {

    private final JsonNode config;
    private final OptionType type;

    private final String name;

    public CommandOptionMapping(JsonNode config, OptionType type) {
        this.config = config;
        this.type = type;
        name = config.get("name").asText();
    }

    @Override
    public String getName() {
        return name;
    }
}
