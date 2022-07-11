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
            
package io.github.realyusufismail.ydw.application.commands.option;

import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydwreg.application.commands.option.CommandOptionMapping;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public interface CommandOption {
    OptionType getType();

    String getName();

    String getDescription();

    Optional<Boolean> isRequired();

    List<CommandOptionChoice> getChoices();

    List<CommandOptionMapping> getOptions();

    EnumSet<ChannelType> getChannelTypes();

    /**
     * If the option is an INTEGER or NUMBER type, the minimum value permitted.
     *
     * @return the minimum value
     */
    Optional<Integer> getMinValue();

    /**
     * If the option is an INTEGER or NUMBER type, the maximum value permitted.
     *
     * @return the maximum value
     */
    Optional<Integer> getMaxValue();

    Boolean isAutoComplete();
}
