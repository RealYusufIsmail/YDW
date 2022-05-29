/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package yusufsdiscordbot.ydlreg.application.commands.data;

import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.application.commands.option.OptionTypeEnum;

import java.util.List;
import java.util.Objects;

public class SlashCommandData {
    private final String commandName;
    private final String commandDescription;
    private final boolean isGuildOnly;
    private List<CommandOptionData> commandOptionData;

    public SlashCommandData(String commandName, String commandDescription, boolean isGuildOnly) {
        this.commandName = commandName;
        this.commandDescription = commandDescription;
        this.isGuildOnly = isGuildOnly;
    }

    public void setCommandOptionData(@NotNull CommandOptionData commandOptionData) {
        this.commandOptionData = List.of(commandOptionData);
    }

    /**
     * Used to create a new Option for the Command
     *
     * @param name The name of the Option
     * @param description The description of the Option
     */
    public @NotNull SlashCommandData newOption(@NotNull OptionTypeEnum optionType,
            @NotNull String name, @NotNull String description) {
        var optionData = new CommandOptionData(optionType, name, description, false);
        setCommandOptionData(optionData);
        return this;
    }

    /**
     * Used to create a new Option for the Command
     *
     * @param name The name of the Option
     * @param description The description of the Option
     * @param isRequired Whether the Option is required
     */
    public @NotNull SlashCommandData newOption(@NotNull OptionTypeEnum optionType,
            @NotNull String name, @NotNull String description, boolean isRequired) {
        var commandOption = new CommandOptionData(optionType, name, description, isRequired);
        setCommandOptionData(commandOption);
        return this;
    }


    public @NotNull SlashCommandData newOptions(@NotNull CommandOptionData commandOptionData) {
        var commandOption = new CommandOptionData(commandOptionData.getOptionType(),
                commandOptionData.getOptionName(), commandOptionData.getOptionDescription(),
                commandOptionData.isRequired());
        setCommandOptionData(commandOption);
        return this;
    }

    public @NotNull SlashCommandData newOptions(
            @NotNull CommandOptionData @NotNull [] commandOptionData) {
        var commandOption = new CommandOptionData(commandOptionData[0].getOptionType(),
                commandOptionData[0].getOptionName(), commandOptionData[0].getOptionDescription(),
                commandOptionData[0].isRequired());
        setCommandOptionData(commandOption);
        for (var i = 1; i < commandOptionData.length; i++) {
            commandOption = new CommandOptionData(commandOptionData[i].getOptionType(),
                    commandOptionData[i].getOptionName(),
                    commandOptionData[i].getOptionDescription(), commandOptionData[i].isRequired());
            setCommandOptionData(commandOption);
        }
        return this;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public boolean isGuildOnly() {
        return isGuildOnly;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SlashCommandData that))
            return false;
        return isGuildOnly() == that.isGuildOnly()
                && Objects.equals(getCommandName(), that.getCommandName())
                && Objects.equals(getCommandDescription(), that.getCommandDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommandName(), getCommandDescription(), isGuildOnly());
    }

    @Override
    public @NotNull String toString() {
        return "SlashCommandData{" + "commandName='" + commandName + '\'' + ", commandDescription='"
                + commandDescription + '\'' + ", isGuildOnly=" + isGuildOnly + '}';
    }

    public List<CommandOptionData> getOptions() {
        return commandOptionData;
    }
}
