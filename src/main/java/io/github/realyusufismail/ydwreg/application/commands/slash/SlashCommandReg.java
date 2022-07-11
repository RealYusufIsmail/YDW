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
import io.github.realyusufismail.ydw.application.commands.reply.IReply;
import io.github.realyusufismail.ydw.application.commands.reply.ReplyConfig;
import io.github.realyusufismail.ydwreg.action.ActionReg;
import io.github.realyusufismail.ydwreg.application.InteractionReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlashCommandReg extends InteractionReg implements Interaction, IReply {

    public SlashCommandReg(@NotNull JsonNode application, long id, YDW ydw) {
        super(application, id, ydw);
    }

    @Override
    public Action reply(String message, @Nullable ReplyConfig config) {
        var req = ydw.getRest()
            .getSlashCommandCaller()
            .reply(message, config, super.getId(), super.getToken());

        return new ActionReg(req, ydw);
    }

    @Override
    public Action replyEmbed(EmbedBuilder embed, @Nullable ReplyConfig config) {
        var req = ydw.getRest()
            .getSlashCommandCaller()
            .replyEmbed(embed.build(), config, super.getId(), super.getToken());

        return new ActionReg(req, ydw);
    }
}

