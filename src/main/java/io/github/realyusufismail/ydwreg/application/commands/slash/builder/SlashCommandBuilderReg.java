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

package io.github.realyusufismail.ydwreg.application.commands.slash.builder;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydw.application.commands.slash.builder.SlashCommandBuilder;
import io.github.realyusufismail.ydwreg.rest.callers.SlashCommandCaller;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class SlashCommandBuilderReg implements SlashCommandBuilder {

    private final SlashCommandCaller caller;

    private static String optionName;
    private static String optionDescription;

    private static OptionType optionType;
    private static boolean optionRequired;

    private static boolean guildOnly;

    public SlashCommandBuilderReg(@NotNull YDW ydw, String name, String description) {
        this.caller = ydw.getRest().getSlashCommandCaller();
        caller.setName(name);
        caller.setDescription(description);
    }

    @Override
    public Boolean isGuildOnly() {
        return guildOnly;
    }

    @Override
    public SlashCommandBuilder setOption(OptionType optionType, String name, String description,
            boolean required) {
        caller.setOptions(List.of(new Option(optionType, name, description, required)));
        return this;
    }

    @Override
    public SlashCommandBuilder setOptions(Collection<Option> options) {
        caller.setOptions(options);
        return this;
    }

    @Override
    public SlashCommandBuilder setOptions(Option... options) {
        caller.setOptions(List.of(options));
        return this;
    }

    @Override
    public SlashCommandBuilder setExtendedOptions(OptionExtender... optionExtenders) {
        caller.setOptionExtenders(List.of(optionExtenders));
        return this;
    }

    @Override
    public SlashCommandBuilder setExtendedOptions(Collection<OptionExtender> optionExtenders) {
        caller.setOptionExtenders(optionExtenders);
        return this;
    }

    public static void setOptionName(String optionName) {
        SlashCommandBuilderReg.optionName = optionName;
    }

    public static void setOptionDescription(String optionDescription) {
        SlashCommandBuilderReg.optionDescription = optionDescription;
    }

    public static void setOptionType(OptionType optionType) {
        SlashCommandBuilderReg.optionType = optionType;
    }

    public static void setOptionRequired(boolean optionRequired) {
        SlashCommandBuilderReg.optionRequired = optionRequired;
    }

    public static String getOptionName() {
        return optionName;
    }

    public static String getOptionDescription() {
        return optionDescription;
    }

    public static OptionType getOptionType() {
        return optionType;
    }

    public static boolean isOptionRequired() {
        return optionRequired;
    }

    public SlashCommandBuilder call() {
        if (guildOnly) {
            caller.callGuildOnlyCommand();
        } else {
            caller.callGlobalCommand();
        }
        return this;
    }
}
