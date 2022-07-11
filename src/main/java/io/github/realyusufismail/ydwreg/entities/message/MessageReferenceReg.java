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
