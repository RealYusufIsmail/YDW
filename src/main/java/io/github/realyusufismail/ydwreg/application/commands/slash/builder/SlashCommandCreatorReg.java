/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.application.commands.slash.builder;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydw.application.commands.slash.builder.SlashCommandBuilder;
import io.github.realyusufismail.ydw.application.commands.slash.builder.SlashCommandCreator;
import io.github.realyusufismail.ydwreg.rest.callers.SlashCommandCaller;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class SlashCommandCreatorReg implements SlashCommandCreator {
    private static String optionName;
    private static String optionDescription;
    private static OptionType optionType;
    private static boolean optionRequired;
    private final SlashCommandCaller caller;
    private Boolean guildOnly = false;

    public SlashCommandCreatorReg(@NotNull YDW ydw, String name, String description) {
        this.caller = ydw.getRest().getSlashCommandCaller();
        caller.setName(name);
        caller.setDescription(description);
    }

    public static void setOptionName(String optionName) {
        SlashCommandCreatorReg.optionName = optionName;
    }

    public static void setOptionDescription(String optionDescription) {
        SlashCommandCreatorReg.optionDescription = optionDescription;
    }

    public static void setOptionType(OptionType optionType) {
        SlashCommandCreatorReg.optionType = optionType;
    }

    public static void setOptionRequired(boolean optionRequired) {
        SlashCommandCreatorReg.optionRequired = optionRequired;
    }

    @Override
    public SlashCommandCreator isGuildOnly(boolean toGuildOnly) {
        this.guildOnly = toGuildOnly;
        return this;
    }

    @Override
    public SlashCommandCreator setOption(OptionType optionType, String name, String description,
            boolean required) {
        caller.setOptions(List.of(new Option(optionType, name, description, required)));
        return this;
    }

    @Override
    public SlashCommandCreator setOptions(Collection<Option> options) {
        caller.setOptions(options);
        return this;
    }

    @Override
    public SlashCommandCreator setOptions(Option... options) {
        caller.setOptions(List.of(options));
        return this;
    }

    @Override
    public SlashCommandCreator setExtendedOptions(OptionExtender... optionExtenders) {
        caller.setOptionExtenders(List.of(optionExtenders));
        return this;
    }

    @Override
    public SlashCommandCreator setExtendedOptions(Collection<OptionExtender> optionExtenders) {
        caller.setOptionExtenders(optionExtenders);
        return this;
    }

    @Override
    public SlashCommandBuilder build() {
        return new SlashCommandBuilderReg(caller, guildOnly);
    }

    public static String getOptionName() {
        return optionName;
    }

    public static String getOptionDescription() {
        return optionDescription;
    }

    public static boolean isOptionRequired() {
        return optionRequired;
    }

    public static OptionType getOptionType() {
        return optionType;
    }
}
