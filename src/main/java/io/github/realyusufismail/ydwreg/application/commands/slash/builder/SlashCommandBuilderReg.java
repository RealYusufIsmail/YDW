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

import io.github.realyusufismail.ydw.application.commands.slash.builder.SlashCommandBuilder;
import io.github.realyusufismail.ydwreg.rest.callers.SlashCommandCaller;

public class SlashCommandBuilderReg implements SlashCommandBuilder {
    private final SlashCommandCaller caller;
    private final boolean guildOnly;

    public SlashCommandBuilderReg(SlashCommandCaller caller, boolean guildOnly) {
        this.caller = caller;
        this.guildOnly = guildOnly;
    }


    @Override
    public void upsert() {
        if (guildOnly) {
            caller.upsertGuildCommand();
        } else {
            caller.upsertGlobalCommand();
        }
    }

    @Override
    public void create() {
        if (guildOnly) {
            caller.createGuildOnlyCommand();
        } else {
            caller.createGlobalCommand();
        }
    }
}
