package io.github.realyusufismail.ydw.application.commands.slash.builder;

import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.Option;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.OptionExtender;

import javax.annotation.CheckReturnValue;
import java.util.Collection;

public interface SlashCommandBuilder {

    /**
     * Weather the command should be available in specified guild or available for all guilds.
     *
     * @return true if the command should be available in specified guild or false were it is
     *         available for all guilds.
     */
    @CheckReturnValue
    SlashCommandBuilder setToGuildOnly(boolean toGuildOnly);

    @CheckReturnValue

    SlashCommandBuilder setOption(OptionType optionType, String name, String description,
            boolean required);

    @CheckReturnValue

    default SlashCommandBuilder setOption(OptionType optionType, String name, String description) {
        return setOption(optionType, name, description, false);
    }

    @CheckReturnValue

    SlashCommandBuilder setOptions(Collection<Option> options);

    @CheckReturnValue

    SlashCommandBuilder setOptions(Option... options);

    @CheckReturnValue

    SlashCommandBuilder setExtendedOptions(OptionExtender... optionExtenders);

    @CheckReturnValue

    SlashCommandBuilder setExtendedOptions(Collection<OptionExtender> optionExtenders);
}
