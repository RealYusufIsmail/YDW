package io.github.realyusufismail.ydw.application.commands.slash.option;

import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydwreg.application.commands.option.CommandOptionMapping;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface SlashOptionGetter {

    /**
     * Used to get the slash command options
     * 
     * @return the list of options
     */
    // TODO: revert to List<CommandOption> getOptions(); when the issue is fixed.
    List<CommandOptionMapping> getCommandOptions();

    /**
     * Used to get the slash command options with that specific name.
     * 
     * @param name the name of the option
     * @return the option
     */
    default List<CommandOptionMapping> getOptionsByName(@NotNull String name) {
        return getCommandOptions().stream()
            .filter(option -> option.getName().equals(name))
            .toList();
    }

    default List<CommandOptionMapping> getOptionsByType(OptionType type) {
        return getCommandOptions().stream()
            .filter(option -> option.getType().equals(type))
            .toList();
    }

    default CommandOptionMapping getOption(String name) {
        List<CommandOptionMapping> options = getOptionsByName(name);
        return options.isEmpty() ? null : options.get(0);
    }

    default <T> T getOption(String name,
            @NotNull Function<? super CommandOptionMapping, ? extends T> mapping,
            @Nullable Supplier<? extends T> fallback) {
        CommandOptionMapping option = getOption(name);
        return option == null ? fallback == null ? null : fallback.get() : mapping.apply(option);
    }

    default <T> T getOption(String name,
            @NotNull Function<? super CommandOptionMapping, ? extends T> mapping) {
        return getOption(name, mapping, null);
    }

    default <T> T getOption(String name,
            @NotNull Function<? super CommandOptionMapping, ? extends T> mapping,
            @NotNull T fallback) {
        return getOption(name, mapping, () -> fallback);
    }
}
