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

package io.github.realyusufismail.yusufsdiscordbot.ydl.entities.voice;

import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.GenericEntity;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.Guild;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.User;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Channel;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Member;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface VoiceState extends GenericEntity {

    Optional<Guild> getGuild();

    Optional<Channel> getChannel();

    Optional<User> getUser();

    Optional<Member> getMember();

    String getSessionId();

    Boolean isDeafened();

    Boolean isMuted();

    Boolean isSelfDeafened();

    Boolean isSelfMuted();

    Optional<Boolean> isSelfStream();

    Boolean isSelfVideo();

    Boolean isSuppressed();

    ZonedDateTime getRequestToSpeakTimeStamp();

    List<VoiceRegion> getVoiceRegions();
}
