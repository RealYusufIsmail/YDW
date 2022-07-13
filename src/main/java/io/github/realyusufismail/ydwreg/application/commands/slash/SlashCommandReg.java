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
package io.github.realyusufismail.ydwreg.application.commands.slash;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.action.Action;
import io.github.realyusufismail.ydw.application.Interaction;
import io.github.realyusufismail.ydw.application.commands.ApplicationCommand;
import io.github.realyusufismail.ydw.application.commands.reply.IReply;
import io.github.realyusufismail.ydw.application.commands.reply.ReplyConfig;
import io.github.realyusufismail.ydwreg.action.ActionReg;
import io.github.realyusufismail.ydwreg.application.commands.ApplicationCommandReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: need to use Application command instead of Interaction
public class SlashCommandReg extends ApplicationCommandReg implements ApplicationCommand, IReply {

    public SlashCommandReg(@NotNull JsonNode application, long id, Interaction interaction,
            YDW ydw) {
        super(application, id, interaction, ydw);
    }

    public SlashCommandReg(@NotNull ApplicationCommand applicationCommand, YDW ydw) {
        super(applicationCommand, ydw);
    }

    //TODO: Reply system is broken.
    @Override
    public Action reply(String message, @Nullable ReplyConfig config) {
        String token =
                super.getInteraction().isPresent() ? super.getInteraction().get().getToken() : null;

        if (token == null) {
            throw new IllegalStateException("Interaction token is null");
        }

        var req =
                ydw.getRest().getSlashCommandCaller().reply(message, config, super.getId(), token);

        return new ActionReg(req, ydw);
    }

    @Override
    public Action replyEmbed(EmbedBuilder embed, @Nullable ReplyConfig config) {
        String token =
                super.getInteraction().isPresent() ? super.getInteraction().get().getToken() : null;
        if (token == null) {
            throw new IllegalStateException("Interaction token is null");
        }
        var req = ydw.getRest()
            .getSlashCommandCaller()
            .replyEmbed(embed.build(), config, super.getId(), token);

        return new ActionReg(req, ydw);
    }
}

