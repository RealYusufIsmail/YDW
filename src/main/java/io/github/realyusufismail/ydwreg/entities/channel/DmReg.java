/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
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
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DmReg extends ChannelReg implements Dm {

    private final String name;
    private final Long lassMessageId;
    private final List<User> users = new ArrayList<>();

    public DmReg(@NotNull JsonNode channelJ, long id, @NotNull YDW ydw) {
        super(channelJ, id, ydw);

        this.name = channelJ.get("name").asText();

        this.lassMessageId =
                channelJ.hasNonNull("last_message_id") ? channelJ.get("last_message_id").asLong()
                        : null;

        if (channelJ.hasNonNull("recipients")) {
            channelJ.get("recipients").forEach(recipient -> {
                users.add(new UserReg(channelJ.get("recipients"),
                        channelJ.get("recipients").get("id").asLong(), ydw));
            });
        }
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public SnowFlake lastMessageId() {
        return SnowFlake.of(lassMessageId);
    }

    @Override
    public List<User> getRecipients() {
        return users;
    }
}
