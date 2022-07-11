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
            
package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydwreg.entities.event.EventPrivacyLevel;
import io.github.realyusufismail.ydwreg.entities.event.EventStatus;
import io.github.realyusufismail.ydwreg.entities.event.EventType;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.Optional;

public interface GuildScheduledEvents extends SnowFlake, GenericEntity {

    Guild getGuild();

    Channel getChannel();

    Optional<User> getEventCreator();

    String getEventName();

    Optional<String> getEventDescription();

    ZonedDateTime getEventStartTime();

    ZonedDateTime getEventEndTime();

    @NotNull
    EventPrivacyLevel getEventPrivacyLevel();

    @NotNull
    EventStatus getEventStatus();

    @NotNull
    EventType getEventType();

    @NotNull
    SnowFlake getEntityIdLong();

    Optional<Integer> getUserCount();

    Optional<String> getImage();
}
