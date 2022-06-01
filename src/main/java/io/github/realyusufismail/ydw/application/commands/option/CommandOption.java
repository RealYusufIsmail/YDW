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

import io.github.realyusufismail.ydw.entities.guild.channel.ChannelType;
import io.github.realyusufismail.ydwreg.application.commands.option.CommandOptionMapping;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public interface CommandOption {
    OptionType getType();

    String getName();

    String getDescription();

    Optional<Boolean> isRequired();

    List<CommandOptionChoice> getChoices();

    List<CommandOptionMapping> getOptions();

    EnumSet<ChannelType> getChannelTypes();

    /**
     * If the option is an INTEGER or NUMBER type, the minimum value permitted.
     * 
     * @return the minimum value
     */
    Optional<Integer> getMinValue();

    /**
     * If the option is an INTEGER or NUMBER type, the maximum value permitted.
     * 
     * @return the maximum value
     */
    Optional<Integer> getMaxValue();

    Boolean isAutoComplete();
}
