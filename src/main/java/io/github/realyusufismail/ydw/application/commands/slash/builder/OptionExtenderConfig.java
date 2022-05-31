package io.github.realyusufismail.ydw.application.commands.slash.builder;

import io.github.realyusufismail.ydwreg.application.commands.slash.builder.OptionExtender;

public interface OptionExtenderConfig {
    OptionExtenderConfig addChoice(String name, Object value);

    default OptionExtenderConfig addChoice(String name, int value) {
        return addChoice(name, (Object) value);
    }

    default OptionExtenderConfig addChoice(String name, double value) {
        return addChoice(name, (Object) value);
    }

    OptionExtenderConfig setRequired(boolean required);

    OptionExtenderConfig setMaxAndMin(Integer max, Integer min);

    OptionExtenderConfig setMaxAndMin(Double max, Double min);

    default OptionExtenderConfig setMaxAndMin(int max) {
        return setMaxAndMin(max, null);
    }

    default OptionExtenderConfig setMaxAndMin(double max) {
        return setMaxAndMin(max, null);
    }

    default OptionExtenderConfig setMin(int min) {
        return setMaxAndMin(null, min);
    }

    default OptionExtenderConfig setMin(double min) {
        return setMaxAndMin(null, min);
    }
}
