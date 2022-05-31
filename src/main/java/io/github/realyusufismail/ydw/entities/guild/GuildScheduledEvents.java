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

package io.github.realyusufismail.ydw.entities.guild;

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
