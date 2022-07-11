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

package io.github.realyusufismail.ydw.application.commands.option;

import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.Member;
import io.github.realyusufismail.ydw.entities.guild.Role;
import io.github.realyusufismail.ydw.entities.guild.channel.NewsChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.VoiceChannel;

import java.util.Optional;

public interface CommandInteractionDataOption {

    /**
     * Gets the type of the option.
     *
     * @return the type of the option.
     */
    OptionType getType();


    /**
     * Gets the name of the parameter.
     *
     * @return the name of the parameter.
     */
    String getName();

    /**
     * @return true if this option is the currently focused option for autocomplete
     */
    Optional<Boolean> isFocused();

    Optional<String> getAsString();

    Optional<Integer> getAsInt();

    Optional<Double> getAsDouble();

    Optional<Long> getAsLong();

    Optional<Boolean> getAsBoolean();

    Member getAsMember();

    User getAsUser();

    Channel getAsChannel();

    TextChannel getAsTextChannel();

    VoiceChannel getAsVoiceChannel();

    NewsChannel getAsNewsChannel();

    Role getAsRole();
}
