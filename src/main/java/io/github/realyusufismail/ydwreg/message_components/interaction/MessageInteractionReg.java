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

import java.util.Optional;

public class MessageInteractionReg implements MessageInteraction {
    private final YDW ydw;
    private final long id;

    private final InteractionType type;
    private final String name;
    private final User user;
    private final Member member;

    public MessageInteractionReg(JsonNode message, long id, YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.type = InteractionType.getValue(message.get("type").asInt());
        this.name = message.get("name").asText();
        this.user = new UserReg(message.get("user"), message.get("user").get("id").asLong(), ydw);
        this.member = message.hasNonNull("member") ? new MemberReg(message.get("member")
                , ydw) : null;
    }

    @Nullable
    @Override
    public YDW getYDW() {
        return ydw;
    }

    @Override
    public InteractionType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Optional<Member> getMember() {
        return Optional.ofNullable(member);
    }

    @Nullable
    @Override
    public Long getIdLong() {
        return id;
    }
}
