/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.message_components.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.User;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Member;
import io.github.realyusufismail.yusufsdiscordbot.ydl.interaction.MessageInteraction;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.application.interaction.InteractionType;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.UserReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.guild.MemberReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.util.Verify;

public class MessageInteractionReg implements MessageInteraction {
    private final JsonNode message;
    private final YDL ydl;

    public MessageInteractionReg(JsonNode message, YDL ydl) {
        this.message = message;
        this.ydl = ydl;
    }


    /**
     * Called when the bot is ready.
     *
     * @return The YDL instance.
     */
    @Override
    public @Nullable YDL getYDL() {
        return ydl;
    }

    /**
     * @return The core long of this api.
     */
    @Override
    public Long getIdLong() {
        return message.get("id").asLong();
    }

    @Override
    public InteractionType getType() {
        return InteractionType.fromInt(message.get("type").asInt());
    }

    @Override
    public String getName() {
        return message.get("name").asText();
    }

    @Override
    public User getUser() {
        return new UserReg(message.get("user").getAsJsonNode(), ydl);
    }

    @Override
    public Member getMember() {
        var member = message.get("member");
        Verify.checkIfNull(member, "Member is null");
        return new MemberReg(member.getAsJsonNode(), ydl);
    }
}
