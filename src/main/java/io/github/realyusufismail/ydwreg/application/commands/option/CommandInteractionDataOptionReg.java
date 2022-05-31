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
import io.github.realyusufismail.ydw.application.commands.option.CommandInteractionDataOption;
import io.github.realyusufismail.ydw.application.commands.option.OptionTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandInteractionDataOptionReg implements CommandInteractionDataOption {

    private final String name;
    private final OptionTypeEnum type;
    private final JsonNode value;
    private final List<CommandInteractionDataOption> options = new ArrayList<>();
    private final Boolean focused;

    public CommandInteractionDataOptionReg(JsonNode option) {
        name = option.get("name").asText();
        type = OptionTypeEnum.getOptionType(option.get("type").asInt());
        focused = option.hasNonNull("focused") ? option.get("focused").asBoolean() : null;
        value = option.hasNonNull("value") ? option.get("value") : null;

        if (option.hasNonNull("options")) {
            option.get("options")
                .forEach(
                        optionNode -> options.add(new CommandInteractionDataOptionReg(optionNode)));
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public OptionTypeEnum getType() {
        return type;
    }

    @Override
    public Optional<Object> getValue() {
        if (value.isTextual()) {
            return Optional.of(value.asText());
        } else if (value.isInt()) {
            return Optional.of(value.asInt());
        } else if (value.isDouble()) {
            return Optional.of(value.asDouble());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<CommandInteractionDataOption> getOptions() {
        return options;
    }

    @Override
    public Optional<Boolean> isFocused() {
        return Optional.ofNullable(focused);
    }
}

