package io.github.realyusufismail.ydwreg.application.commands.slash.builder;

import io.github.realyusufismail.ydw.application.commands.option.OptionTypeEnum;
import io.github.realyusufismail.ydw.application.commands.slash.builder.OptionExtenderConfig;

public class OptionExtender extends Option implements OptionExtenderConfig {

    public OptionExtender(OptionTypeEnum type, String name, String description, boolean isRequired) {
        super(type, name, description, isRequired);
    }

    @Override
    public OptionExtenderConfig addChoice(String name, Object value) {
        return null;
    }

    @Override
    public OptionExtenderConfig setRequired(boolean required) {
        return null;
    }

    @Override
    public OptionExtenderConfig setMaxAndMin(Integer max, Integer min) {
        return null;
    }

    @Override
    public OptionExtenderConfig setMaxAndMin(Double max, Double min) {
        return null;
    }
}
