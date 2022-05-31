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

package io.github.realyusufismail.ydw.entities.guild.message;

import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

import java.util.Optional;


public interface MessageReference extends SnowFlake {

    Optional<SnowFlake> getMessage();

    Optional<Channel> getChannel();

    Optional<Guild> getGuild();

    /**
     * when sending, whether to error if the referenced message doesn't exist instead of sending as
     * a normal (non-reply) message, default true
     *
     * @return if found false, if not found true
     */
    Optional<Boolean> shouldFailIfDoesNotExist();
}
