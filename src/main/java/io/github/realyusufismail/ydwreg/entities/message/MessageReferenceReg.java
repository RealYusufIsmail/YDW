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

package io.github.realyusufismail.ydwreg.entities.message;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.guild.message.MessageReference;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MessageReferenceReg implements MessageReference {
    private final long id;
    private final YDW ydw;

    private final Long message;
    private final Channel channel;
    private final Guild guild;
    private final Boolean failIfDoesNotExist;

    public MessageReferenceReg(JsonNode message, long id, YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.message = message.hasNonNull("message_id") ? message.get("message_id").asLong() : null;
        this.channel = message.hasNonNull("channel_id")
                ? ydw.getChannel(message.get("channel_id").asLong())
                : null;
        this.guild = message.hasNonNull("guild_id") ? ydw.getGuild(message.get("guild_id").asLong())
                : null;
        this.failIfDoesNotExist = message.hasNonNull("fail_if_not_exists")
                ? message.get("fail_if_not_exists").asBoolean()
                : null;
    }

    @Override
    public Optional<SnowFlake> getMessage() {
        return Optional.ofNullable(message).map(SnowFlake::of);
    }

    @Override
    public Optional<Channel> getChannel() {
        return Optional.ofNullable(channel);
    }

    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @Override
    public Optional<Boolean> shouldFailIfDoesNotExist() {
        return Optional.ofNullable(failIfDoesNotExist);
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
