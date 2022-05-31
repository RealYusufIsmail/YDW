package io.github.realyusufismail.ydw.application.commands.slash.builder;

import io.github.realyusufismail.ydw.application.commands.option.OptionTypeEnum;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.Option;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.OptionExtender;

import java.util.Collection;
import java.util.List;

public interface SlashCommandBuilder {

    /**
     * Used to set the command name.
     * @param name The name of the command.
     * @return The builder.
     */
    SlashCommandBuilder setName(String name);

    /**
     * Used to set the command description.
     * @param description What the command does.
     * @return The builder.
     */
    SlashCommandBuilder setDescription(String description);

    SlashCommandBuilder setOption(OptionTypeEnum optionType, String name, String description, boolean required);

    default SlashCommandBuilder setOption(OptionTypeEnum optionType, String name, String description) {
        return setOption(optionType, name, description, false);
    }

    SlashCommandBuilder setOptions(Collection<Option> options);

    SlashCommandBuilder setOptions(Option... options);

    SlashCommandBuilder setExtendedOptions(OptionExtender... optionExtenders);

    SlashCommandBuilder setExtendedOptions(Collection<OptionExtender> optionExtenders);
}
