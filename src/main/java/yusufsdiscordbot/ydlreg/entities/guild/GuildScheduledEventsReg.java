/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package yusufsdiscordbot.ydlreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.Guild;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydl.entities.guild.Channel;
import yusufsdiscordbot.ydl.entities.guild.GuildScheduledEvents;
import yusufsdiscordbot.ydlreg.entities.event.EventPrivacyLevel;
import yusufsdiscordbot.ydlreg.entities.event.EventStatus;
import yusufsdiscordbot.ydlreg.entities.event.EventType;
import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import java.time.ZonedDateTime;
import java.util.Optional;

public class GuildScheduledEventsReg implements GuildScheduledEvents {
    private final YDL ydl;
    private final long id;

    private final Guild guild;
    private final Channel channel;
    private final User creator;
    private final String name;
    private final String description;
    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime;
    private final EventPrivacyLevel privacyLevel;
    private final EventStatus status;
    private final EventType type;
    private final Long entityId;
    private final Integer userCount;
    private final String image;

    public GuildScheduledEventsReg(JsonNode event, long id, YDL ydl) {
        this.ydl = ydl;
        this.id = id;

        this.guild = event.hasNonNull("guild") ? ydl.getGuild(event.get("guild").asLong()) : null;
        this.channel =
                event.hasNonNull("channel") ? ydl.getChannel(event.get("channel").asLong()) : null;
        this.creator =
                event.hasNonNull("creator") ? ydl.getUser(event.get("creator").asLong()) : null;
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
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public YDL getYDL() {
        return ydl;
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
    public Optional<User> getEventCreator() {
        return Optional.ofNullable(creator);
    }

    @Override
    public String getEventName() {
        return name;
    }

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

    @Override
    public Optional<Integer> getUserCount() {
        return Optional.ofNullable(userCount);
    }

    @Override
    public Optional<String> getImage() {
        return Optional.ofNullable(image);
    }
}
