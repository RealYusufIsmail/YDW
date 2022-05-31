/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
 */

package io.github.realyusufismail.ydwreg.application.commands.data;

import io.github.realyusufismail.ydw.application.commands.option.OptionTypeEnum;
import org.jetbrains.annotations.NotNull;

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

    public @NotNull SlashCommandData newOptions(@NotNull CommandOptionData[] commandOptionData) {
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
