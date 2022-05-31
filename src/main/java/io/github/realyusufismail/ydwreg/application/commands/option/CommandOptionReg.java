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

package io.github.realyusufismail.ydwreg.application.commands.option;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.application.commands.option.CommandOption;
import io.github.realyusufismail.ydw.application.commands.option.CommandOptionChoice;
import io.github.realyusufismail.ydw.application.commands.option.CommandType;
import io.github.realyusufismail.ydw.entities.guild.channel.ChannelType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class CommandOptionReg implements CommandOption {

    private final CommandType commandType;
    private final String name;
    private final String description;
    private final Boolean required;
    private final List<CommandOptionChoice> choices = new ArrayList<>();
    private final List<CommandOption> options = new ArrayList<>();
    private final EnumSet<ChannelType> channelTypes = EnumSet.noneOf(ChannelType.class);
    private final Integer minValue;
    private final Integer maxValue;
    private final Boolean autoComplete;

    public CommandOptionReg(@NotNull JsonNode option) {
        this.commandType = CommandType.getCommandType(option.get("type").asInt());
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
                options.add(new CommandOptionReg(optionNode));
            }
        }

        if (option.hasNonNull("channel_types")) {
            for (JsonNode channelType : option.get("channel_types")) {
                channelTypes.add(ChannelType.getChannelType(channelType.asInt()));
            }
        }
    }

    @Override
    public CommandType getType() {
        return commandType;
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
    public List<CommandOption> getOptions() {
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
