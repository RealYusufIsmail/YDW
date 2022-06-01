package io.github.realyusufismail.ydw.application.commands.slash.option;

import io.github.realyusufismail.ydw.application.commands.ApplicationCommand;
import io.github.realyusufismail.ydw.application.commands.option.CommandOption;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydwreg.application.commands.ApplicationCommandReg;
import io.github.realyusufismail.ydwreg.application.commands.option.CommandOptionMapping;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface SlashOptionGetter {

    /**
     * Used to get the slash command options
     * @return the list of options
     */
    List<CommandOptionMapping> getOptions();

    /**
     * Used to get the slash command options with that specific name.
     * @param name the name of the option
     * @return the option
     */
    default List<CommandOptionMapping> getOptionsByName(@NotNull String name) {
        return getOptions()
                .stream()
                .filter(option -> option.getName().equals(name))
                .toList();
    }

    default List<CommandOptionMapping> getOptionsByType(OptionType type) {
        return getOptions()
                .stream()
                .filter(option -> option.getType().equals(type))
                .toList();
    }

    default CommandOptionMapping getOption(String name) {
        List<CommandOptionMapping> options = getOptionsByName(name);
        return options.isEmpty() ? null : options.get(0);
    }
}
