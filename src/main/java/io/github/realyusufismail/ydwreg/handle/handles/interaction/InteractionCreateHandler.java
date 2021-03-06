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
package io.github.realyusufismail.ydwreg.handle.handles.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.Interaction;
import io.github.realyusufismail.ydw.application.commands.ApplicationCommand;
import io.github.realyusufismail.ydw.application.interaction.InteractionType;
import io.github.realyusufismail.ydw.event.events.interaction.SlashCommandInteractionEvent;
import io.github.realyusufismail.ydwreg.application.InteractionReg;
import io.github.realyusufismail.ydwreg.handle.Handle;

public class InteractionCreateHandler extends Handle {
    public InteractionCreateHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        Interaction interaction = new InteractionReg(json, json.get("id").asLong(), ydw);
        if (interaction.getType() == InteractionType.APPLICATION_COMMAND) {
            if (interaction.getData().isEmpty())
                throw new IllegalArgumentException(
                        "Application command interaction data is empty, internal error");

            if (interaction.getApplicationId() == null)
                throw new IllegalArgumentException("Application id is null, internal error");

            ApplicationCommand ap = ydw.getRest()
                .getInteractionCaller()
                .getApplication(interaction.getApplicationId().getIdLong(),
                        interaction.getData().get().getIdLong(), interaction);

            ydw.handelEvent(new SlashCommandInteractionEvent(ap, ydw));
        }
    }
}
