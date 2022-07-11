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
package io.github.realyusufismail.ydwreg.application.commands.slash.option;


import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydw.application.commands.slash.option.SlashOptionGetter;
import io.github.realyusufismail.ydwreg.application.commands.option.CommandOptionMapping;
import io.github.realyusufismail.ydwreg.application.commands.slash.SlashCommandReg;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlashOptionGetterReg extends SlashCommandReg implements SlashOptionGetter {

    private final List<CommandOptionMapping> optionMappings = new ArrayList<>();

    public SlashOptionGetterReg(@NotNull JsonNode application, long id, YDW ydw) {
        super(application, id, ydw);

        if (application.hasNonNull("options")) {
            application.get("options").forEach(option -> {
                optionMappings.add(new CommandOptionMapping(application.get("options"),
                        OptionType.getOptionType(option.get("type").asInt())));
            });
        }
    }

    public List<CommandOptionMapping> getCommandOptions() {
        return optionMappings;
    }
}

