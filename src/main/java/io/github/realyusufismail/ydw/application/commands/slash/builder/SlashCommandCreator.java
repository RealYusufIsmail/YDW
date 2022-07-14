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
package io.github.realyusufismail.ydw.application.commands.slash.builder;

import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.Option;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.OptionExtender;

import java.util.Collection;

public interface SlashCommandCreator {

    /**
     * Weather the command should be available in specified guild or available for all guilds.
     *
     * @return true if the command should be available in specified guild or false were it is
     *         available for all guilds.
     */
    @CheckReturnValue
    SlashCommandCreator isGuildOnly(boolean toGuildOnly);

    @CheckReturnValue
    SlashCommandCreator setOption(OptionType optionType, String name, String description,
            boolean required);

    @CheckReturnValue
    default SlashCommandCreator setOption(OptionType optionType, String name, String description) {
        return setOption(optionType, name, description, false);
    }

    @CheckReturnValue
    SlashCommandCreator setOptions(Collection<Option> options);

    @CheckReturnValue
    SlashCommandCreator setOptions(Option... options);

    @CheckReturnValue
    SlashCommandCreator setExtendedOptions(OptionExtender... optionExtenders);

    @CheckReturnValue
    SlashCommandCreator setExtendedOptions(Collection<OptionExtender> optionExtenders);

    SlashCommandBuilder build();
}
