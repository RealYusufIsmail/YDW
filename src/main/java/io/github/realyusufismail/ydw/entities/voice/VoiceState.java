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

package io.github.realyusufismail.ydw.entities.voice;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydw.entities.guild.Member;

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
