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
import io.github.realyusufismail.ydw.application.Interaction;
import io.github.realyusufismail.ydw.application.commands.option.CommandInteractionDataOption;
import io.github.realyusufismail.ydw.application.commands.slash.option.SlashOptionGetter;
import io.github.realyusufismail.ydwreg.application.commands.option.CommandOptionMapping;
import io.github.realyusufismail.ydwreg.application.commands.slash.SlashCommandReg;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlashOptionGetterReg extends SlashCommandReg implements SlashOptionGetter {

    public SlashOptionGetterReg(@NotNull JsonNode application, long id, Interaction interaction,
            YDW ydw) {
        super(application, id, interaction, ydw);
    }

    @Override
    public CommandOptionMapping getOption(String name) {
        List<CommandInteractionDataOption> options = getOptions();
        for (CommandInteractionDataOption option : options) {
            if (option.getName().equals(name)) {
                return (CommandOptionMapping) option;
            }
        }
        return null;
    }
}

