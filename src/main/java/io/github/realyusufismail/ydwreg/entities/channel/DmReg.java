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
            
package io.github.realyusufismail.ydwreg.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.channel.Dm;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DmReg extends ChannelReg implements Dm {

    private final Long lassMessageId;
    private final User user;

    public DmReg(@NotNull JsonNode channelJ, long id, @NotNull YDW ydw) {
        super(channelJ, id, ydw);

        this.lassMessageId =
                channelJ.hasNonNull("last_message_id") ? channelJ.get("last_message_id").asLong()
                        : null;
        this.user = channelJ.hasNonNull("recipients")
                ? ydw.getUser(channelJ.get("recipients").get(0).get("id").asLong())
                : null;
    }

    @Override
    public Optional<SnowFlake> getLastMessageId() {
        return Optional.ofNullable(lassMessageId).map(SnowFlake::of);
    }

    @Override
    public User getUser() {
        return user;
    }
}
