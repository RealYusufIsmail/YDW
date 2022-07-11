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
import io.github.realyusufismail.ydwreg.rest.callers.SlashCommandCaller;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class SlashCommandBuilderReg implements SlashCommandBuilder {

    private static String optionName;
    private static String optionDescription;
    private static OptionType optionType;
    private static boolean optionRequired;
    private final SlashCommandCaller caller;
    private Boolean guildOnly = false;

    public SlashCommandBuilderReg(@NotNull YDW ydw, String name, String description) {
        this.caller = ydw.getRest().getSlashCommandCaller();
        caller.setName(name);
        caller.setDescription(description);
    }

    public static String getOptionName() {
        return optionName;
    }

    public static void setOptionName(String optionName) {
        SlashCommandBuilderReg.optionName = optionName;
    }

    public static String getOptionDescription() {
        return optionDescription;
    }

    public static void setOptionDescription(String optionDescription) {
        SlashCommandBuilderReg.optionDescription = optionDescription;
    }

    public static OptionType getOptionType() {
        return optionType;
    }

    public static void setOptionType(OptionType optionType) {
        SlashCommandBuilderReg.optionType = optionType;
    }

    public static boolean isOptionRequired() {
        return optionRequired;
    }

    public static void setOptionRequired(boolean optionRequired) {
        SlashCommandBuilderReg.optionRequired = optionRequired;
    }

    @Override
    public SlashCommandBuilderReg setToGuildOnly(boolean toGuildOnly) {
        this.guildOnly = toGuildOnly;
        return this;
    }

    @Override
    public SlashCommandBuilderReg setOption(OptionType optionType, String name, String description,
            boolean required) {
        caller.setOptions(List.of(new Option(optionType, name, description, required)));
        return this;
    }

    @Override
    public SlashCommandBuilderReg setOptions(Collection<Option> options) {
        caller.setOptions(options);
        return this;
    }

    @Override
    public SlashCommandBuilderReg setOptions(Option... options) {
        caller.setOptions(List.of(options));
        return this;
    }

    @Override
    public SlashCommandBuilderReg setExtendedOptions(OptionExtender... optionExtenders) {
        caller.setOptionExtenders(List.of(optionExtenders));
        return this;
    }

    @Override
    public SlashCommandBuilderReg setExtendedOptions(Collection<OptionExtender> optionExtenders) {
        caller.setOptionExtenders(optionExtenders);
        return this;
    }

    public void call() {
        if (guildOnly) {
            caller.callGuildOnlyCommand();
        } else {
            caller.callGlobalCommand();
        }
    }

    public void update(long commandId) {
        if (guildOnly) {
            caller.updateGuildCommand(commandId);
        } else {
            caller.updateGlobalCommand(commandId);
        }
    }

    public void upsert() {
        if (guildOnly) {
            caller.upsertGuildCommand();
        } else {
            caller.upsertGlobalCommand();
        }
    }
}
