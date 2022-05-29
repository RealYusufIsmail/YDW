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

package yusufsdiscordbot.ydl.entities.guild;

import yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.entities.GenericEntity;
import yusufsdiscordbot.ydl.entities.Guild;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydlreg.entities.event.EventPrivacyLevel;
import yusufsdiscordbot.ydlreg.entities.event.EventStatus;
import yusufsdiscordbot.ydlreg.entities.event.EventType;

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
