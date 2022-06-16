package io.github.realyusufismail.ydw.application.commands.slash.builder;

import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.Option;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.OptionExtender;

import java.util.Collection;

public interface SlashCommandBuilder {

    /**
     * Weather the command should be available in specified guild or available for all guilds.
     * 
     * @return true if the command should be available in specified guild or false were it is
     *         available for all guilds.
     */
    boolean setToGuildOnly(boolean toGuildOnly);

    SlashCommandBuilder setOption(OptionType optionType, String name, String description,
            boolean required);

    default SlashCommandBuilder setOption(OptionType optionType, String name, String description) {
        return setOption(optionType, name, description, false);
    }

    SlashCommandBuilder setOptions(Collection<Option> options);

    SlashCommandBuilder setOptions(Option... options);

    SlashCommandBuilder setExtendedOptions(OptionExtender... optionExtenders);

    SlashCommandBuilder setExtendedOptions(Collection<OptionExtender> optionExtenders);
}
