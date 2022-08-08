/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.application.commands.option;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.application.commands.option.CommandOption;
import io.github.realyusufismail.ydw.application.commands.option.CommandOptionChoice;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class CommandOptionReg implements CommandOption {

    private final OptionType optionType;
    private final String name;
    private final String description;
    private final Boolean required;
    private final List<CommandOptionChoice> choices = new ArrayList<>();
    private final List<CommandOptionMapping> options = new ArrayList<>();
    private final EnumSet<ChannelType> channelTypes = EnumSet.noneOf(ChannelType.class);
    private final Integer minValue;
    private final Integer maxValue;
    private final Boolean autoComplete;

    public CommandOptionReg(@NotNull JsonNode option) {
        optionType = OptionType.getOptionType(option.get("type").asInt());
        name = option.get("name").asText();
        description = option.get("description").asText();
        required = option.hasNonNull("required") ? option.get("required").asBoolean() : null;
        minValue = option.hasNonNull("min_value") ? option.get("min_value").asInt() : null;
        maxValue = option.hasNonNull("max_value") ? option.get("max_value").asInt() : null;
        autoComplete =
                option.hasNonNull("autocomplete") ? option.get("autocomplete").asBoolean() : null;

        if (option.hasNonNull("choices")) {
            for (JsonNode choice : option.get("choices")) {
                choices.add(new CommandOptionChoiceReg(choice));
            }
        }

        if (option.hasNonNull("options")) {
            for (JsonNode optionNode : option.get("options")) {
                options.add(new CommandOptionMapping(optionNode));
            }
        }

        if (option.hasNonNull("channel_types")) {
            for (JsonNode channelType : option.get("channel_types")) {
                channelTypes.add(ChannelType.getChannelType(channelType.asInt()));
            }
        }
    }

    @Override
    public OptionType getType() {
        return optionType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Optional<Boolean> isRequired() {
        return Optional.ofNullable(required);
    }

    @Override
    public List<CommandOptionChoice> getChoices() {
        return choices;
    }

    @Override
    public List<CommandOptionMapping> getOptions() {
        return options;
    }

    @Override
    public EnumSet<ChannelType> getChannelTypes() {
        return channelTypes;
    }

    @Override
    public Optional<Integer> getMinValue() {
        return Optional.ofNullable(minValue);
    }

    @Override
    public Optional<Integer> getMaxValue() {
        return Optional.ofNullable(maxValue);
    }

    @Override
    public Boolean isAutoComplete() {
        return autoComplete;
    }
}
