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

package io.github.realyusufismail.ydwreg.application.commands.slash;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.action.ReplyAction;
import io.github.realyusufismail.ydw.application.Interaction;
import io.github.realyusufismail.ydw.application.commands.reply.IReply;
import io.github.realyusufismail.ydw.application.interaction.InteractionData;
import io.github.realyusufismail.ydw.application.interaction.resolved.ResolvedData;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydwreg.action.ReplyActionReg;
import io.github.realyusufismail.ydwreg.application.InteractionReg;
import io.github.realyusufismail.ydwreg.message_components.ComponentType;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SlashCommandReg extends InteractionReg implements Interaction, IReply {

    public SlashCommandReg(@NotNull JsonNode application, long id, YDW ydw) {
        super(application, id, ydw);
    }

    @Override
    public ReplyAction reply(String message) {
        Request request = ydw.getRest().getSlashCommandCaller().reply(message);
        return new ReplyActionReg(request, ydw);
    }

    @Override
    public ReplyAction replyEmbed(Embed embed) {
        return null;
    }

    public String getName() {
        return super.getData().get().getName();
    }
}

