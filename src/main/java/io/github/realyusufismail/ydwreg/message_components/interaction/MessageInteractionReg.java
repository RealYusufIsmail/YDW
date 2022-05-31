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

package io.github.realyusufismail.ydwreg.message_components.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.interaction.MessageInteraction;
import io.github.realyusufismail.ydw.application.interaction.InteractionType;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.entities.guild.MemberReg;
import io.github.realyusufismail.ydwreg.util.Verify;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MessageInteractionReg implements MessageInteraction {
    private final JsonNode message;
    private final YDW ydw;

    public MessageInteractionReg(JsonNode message, YDW ydw) {
        this.message = message;
        this.ydw = ydw;
    }


    /**
     * Called when the bot is ready.
     *
     * @return The ydw instance.
     */
    @Override
    public @Nullable YDW getYDW() {
        return ydw;
    }

    /**
     * @return The core long of this api.
     */
    @NotNull
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

    @NotNull
    @Override
    public User getUser() {
        return new UserReg(message.get("user").getAsJsonNode(), ydw);
    }

    @NotNull
    @Override
    public Member getMember() {
        var member = message.get("member");
        Verify.checkIfNull(member, "Member is null");
        return new MemberReg(member.getAsJsonNode(), ydw);
    }
}
