package io.github.realyusufismail.ydwreg.application.commands.slash.builder;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.application.commands.option.OptionTypeEnum;

import java.util.Collection;

public class Option {

    public Option(OptionTypeEnum type, String name, String description, boolean isRequired) {
        SlashCommandBuilderReg.setOptionType(type);
        SlashCommandBuilderReg.setOptionName(name);
        SlashCommandBuilderReg.setOptionDescription(description);
        SlashCommandBuilderReg.setOptionRequired(isRequired);
    }

    public static ArrayNode toJsonArray(Collection<Option> options) {
        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
        for (Option option : options) {
            arrayNode.add(option.toJson());
        }
        return arrayNode;
    }

    private String toJson() {
        ObjectNode factory = JsonNodeFactory.instance.objectNode();
        return factory.objectNode()
            .put("name", SlashCommandBuilderReg.getOptionName())
            .put("description", SlashCommandBuilderReg.getOptionDescription())
            .put("type", SlashCommandBuilderReg.getOptionType().getValue())
            .put("required", SlashCommandBuilderReg.isOptionRequired())
            .toString();
    }
}
