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

package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Channel;
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
        this.channel =
                event.hasNonNull("channel") ? ydw.getChannel(event.get("channel").asLong()) : null;
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
