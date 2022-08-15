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
package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.guild.StageInstance;
import io.github.realyusufismail.ydw.entities.guild.StagePrivacyLevel;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

public class StageInstanceReg implements StageInstance {
    @NotNull
    private final YDW ydw;
    private final long id;

    private final Guild guild;
    private final Channel channel;
    private final String topic;
    @NotNull
    private final StagePrivacyLevel privacyLevel;
    @NotNull
    private final Boolean isDiscoverableDisabled;
    @NotNull
    private final Long scheduledEventId;

    public StageInstanceReg(@NotNull JsonNode stage, long id, @NotNull YDW ydw) {
        this.ydw = ydw;
        this.id = id;

        this.guild = ydw.getGuild(stage.get("guildId").asLong());
        this.channel = ydw.getChannel(Channel.class, stage.get("channelId").asLong());
        this.topic = stage.get("topic").asText();
        this.privacyLevel = StagePrivacyLevel.valueOf(stage.get("privacyLevel").asText());
        this.isDiscoverableDisabled = stage.get("isDiscoverableDisabled").asBoolean();
        this.scheduledEventId = stage.get("ScheduledEvent").asLong();
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public StagePrivacyLevel getPrivacyLevel() {
        return privacyLevel;
    }

    @Override
    public Boolean isDiscoverableDisabled() {
        return isDiscoverableDisabled;
    }

    @NotNull
    @Override
    public SnowFlake getScheduledEventId() {
        return SnowFlake.of(scheduledEventId);
    }
}
