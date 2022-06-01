package io.github.realyusufismail.ydwreg.application.commands.slash.builder;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.application.commands.option.OptionTypeEnum;
import io.github.realyusufismail.ydw.application.commands.slash.builder.OptionExtenderConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class OptionExtender extends Option implements OptionExtenderConfig {

    private final OptionTypeEnum type;
    private final String name;
    private final String description;
    private final Boolean required;

    private Collection<Choice> choices;

    public OptionExtender(OptionTypeEnum type, String name, String description,
            boolean isRequired) {
        super(type, name, description, isRequired);
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = isRequired;
    }

    public OptionExtender(OptionTypeEnum type, String name, String description) {
        this(type, name, description, false);
    }

    @Override
    public OptionExtenderConfig addChoice(String name, String value) {
        choices.add(new Choice(name, value));
        return this;
    }

    @Override
    public OptionExtenderConfig addChoice(String name, int value) {
        choices.add(new Choice(name, value));
        return this;
    }

    @Override
    public OptionExtenderConfig addChoice(String name, double value) {
        choices.add(new Choice(name, value));
        return this;
    }

    @Override
    public OptionExtenderConfig addChoices(Collection<Choice> choices) {
        this.choices = choices;
        return this;
    }

    // TODO: add max and min
    @Override
    public OptionExtenderConfig setMaxAndMin(Integer max, Integer min) {
        return null;
    }

    @Override
    public OptionExtenderConfig setMaxAndMin(Double max, Double min) {
        return null;
    }

    public static ArrayNode toJsonArray(@NotNull Collection<OptionExtender> options) {
        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
        for (OptionExtender option : options) {
            arrayNode.add(option.toJson());
        }
        return arrayNode;
    }

    private String toJson() {
        ObjectNode factory = JsonNodeFactory.instance.objectNode();
        return factory.put("name", name)
            .put("description", description)
            .put("type", type.getValue())
            .put("required", required)
            .set("choices", Choice.toJsonArray(choices))
            .toString();
    }
}
