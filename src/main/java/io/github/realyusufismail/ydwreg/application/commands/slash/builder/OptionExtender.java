package io.github.realyusufismail.ydwreg.application.commands.slash.builder;

import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydw.application.commands.slash.builder.OptionExtenderConfig;

import java.util.Collection;

public class OptionExtender extends Option implements OptionExtenderConfig {

    private final OptionType type;
    private final String name;
    private final String description;
    private final Boolean required;

    private Collection<Choice> choices;

    public OptionExtender(OptionType type, String name, String description, boolean isRequired) {
        super(type, name, description, isRequired);
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = isRequired;
    }

    public OptionExtender(OptionType type, String name, String description) {
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

    public Collection<Choice> getChoices() {
        return choices;
    }
}
