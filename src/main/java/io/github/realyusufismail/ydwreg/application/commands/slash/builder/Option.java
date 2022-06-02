package io.github.realyusufismail.ydwreg.application.commands.slash.builder;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class Option {

    public Option(OptionType type, String name, String description, boolean isRequired) {
        SlashCommandBuilderReg.setOptionType(type);
        SlashCommandBuilderReg.setOptionName(name);
        SlashCommandBuilderReg.setOptionDescription(description);
        SlashCommandBuilderReg.setOptionRequired(isRequired);
    }

    public Option(OptionType type, String name, String description) {
        this(type, name, description, false);
    }

    public static ArrayNode toJsonArray(Collection<Option> options,
            Collection<OptionExtender> optionExtenders) {
        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
        for (Option option : options) {
            arrayNode.add(option.optionToJson());
        }
        for (OptionExtender optionExtender : optionExtenders) {
            arrayNode.add(optionExtender.optionExtenderToJson(optionExtender));
        }
        return arrayNode;
    }

    String optionToJson() {
        return JsonNodeFactory.instance.arrayNode()
                .add(JsonNodeFactory.instance.objectNode()
                        .put("name", SlashCommandBuilderReg.getOptionName())
                        .put("description", SlashCommandBuilderReg.getOptionDescription())
                        .put("type", SlashCommandBuilderReg.getOptionType().name())
                        .put("required", SlashCommandBuilderReg.isOptionRequired()))
                .toString();
    }

    String optionExtenderToJson(OptionExtender optionExtender) {
        return JsonNodeFactory.instance.arrayNode()
                .add(JsonNodeFactory.instance.objectNode()
                        .put("name", SlashCommandBuilderReg.getOptionName())
                        .put("description", SlashCommandBuilderReg.getOptionDescription())
                        .put("type", SlashCommandBuilderReg.getOptionType().name())
                        .put("required", SlashCommandBuilderReg.isOptionRequired())
                        .put("choices", optionExtender.getChoices()))
                .toString();
    }

    String choicesToJson(Choice choices) {
        return JsonNodeFactory.instance.arrayNode()
                .add(JsonNodeFactory.instance.objectNode()
                        .put("name", choices.getName())
                        .put("value", choices.getValue()))
                .toString();
    }
}
