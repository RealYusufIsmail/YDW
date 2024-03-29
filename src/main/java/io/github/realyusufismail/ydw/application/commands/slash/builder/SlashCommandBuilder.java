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

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.SlashCommandCreatorReg;

// TODO: problem were it registers only one command.
public interface SlashCommandBuilder {

    static SlashCommandCreator create(YDW ydw, String name, String description) {
        return new SlashCommandCreatorReg(ydw, name, description);
    }

}
