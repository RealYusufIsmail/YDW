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
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.GuildScheduledEvents;
import io.github.realyusufismail.ydwreg.entities.event.EventPrivacyLevel;
import io.github.realyusufismail.ydwreg.entities.event.EventStatus;
import io.github.realyusufismail.ydwreg.entities.event.EventType;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.Optional;

public class GuildScheduledEventsReg implements GuildScheduledEvents {
    private final YDW ydw;
    private final long id;

    private final Guild guild;
    private final Channel channel;
    @Nullable
    private final User creator;
    private final String name;
    private final String description;
    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime;
    @Nullable
    private final EventPrivacyLevel privacyLevel;
    @Nullable
    private final EventStatus status;
    @Nullable
    private final EventType type;
    private final Long entityId;
    private final Integer userCount;
    private final String image;

    public GuildScheduledEventsReg(@NotNull JsonNode event, long id, @NotNull YDW ydw) {
        this.ydw = ydw;
        this.id = id;

        this.guild = event.hasNonNull("guild") ? ydw.getGuild(event.get("guild").asLong()) : null;
        this.channel = event.hasNonNull("channel")
                ? ydw.getChannel(Channel.class, event.get("channel").asLong())
                : null;
        this.creator =
                event.hasNonNull("creator") ? ydw.getUser(event.get("creator").asLong()) : null;
        this.name = event.hasNonNull("name") ? event.get("name").asText() : null;
        this.description =
                event.hasNonNull("description") ? event.get("description").asText() : null;
        this.startTime =
                event.hasNonNull("startTime") ? ZonedDateTime.parse(event.get("startTime").asText())
                        : null;
        this.endTime =
                event.hasNonNull("endTime") ? ZonedDateTime.parse(event.get("endTime").asText())
                        : null;
        this.privacyLevel = event.hasNonNull("privacyLevel")
                ? EventPrivacyLevel.valueOf(event.get("privacyLevel").asText())
                : null;
        this.status = event.hasNonNull("status") ? EventStatus.valueOf(event.get("status").asText())
                : null;
        this.type = event.hasNonNull("type") ? EventType.valueOf(event.get("type").asText()) : null;
        this.entityId = event.hasNonNull("entityId") ? event.get("entityId").asLong() : null;
        this.userCount = event.hasNonNull("userCount") ? event.get("userCount").asInt() : null;
        this.image = event.hasNonNull("image") ? event.get("image").asText() : null;
    }

    /**
     * @return The core long of this api.
     */
    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @NotNull
    @Override
    public Optional<User> getEventCreator() {
        return Optional.ofNullable(creator);
    }

    @Override
    public String getEventName() {
        return name;
    }

    @NotNull
    @Override
    public Optional<String> getEventDescription() {
        return Optional.ofNullable(description);
    }

    @Override
    public ZonedDateTime getEventStartTime() {
        return startTime;
    }

    @Override
    public ZonedDateTime getEventEndTime() {
        return endTime;
    }

    @Override
    public @NotNull EventPrivacyLevel getEventPrivacyLevel() {
        return privacyLevel;
    }

    @Override
    public @NotNull EventStatus getEventStatus() {
        return status;
    }

    @Override
    public @NotNull EventType getEventType() {
        return type;
    }

    @Override
    public @NotNull SnowFlake getEntityIdLong() {
        return SnowFlake.of(entityId);
    }

    @NotNull
    @Override
    public Optional<Integer> getUserCount() {
        return Optional.ofNullable(userCount);
    }

    @NotNull
    @Override
    public Optional<String> getImage() {
        return Optional.ofNullable(image);
    }
}
