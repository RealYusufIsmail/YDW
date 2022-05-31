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

import java.util.List;
import java.util.Optional;

public interface CommandInteractionDataOption {

    /**
     * Gets the name of the parameter.
     * 
     * @return the name of the parameter.
     */
    String getName();

    /**
     * Gets the type of the option.
     * 
     * @return the type of the option.
     */
    OptionTypeEnum getType();

    /**
     * Gets the value of the option used by the user. Can be String, Integer, and Double.
     *
     * @return the value of the option used by the user.
     */
    Optional<Object> getValue();

    /**
     * If this option is a group or subcommand it will be present. Otherwise, it will be empty.
     * 
     * @return the subcommand or group of this option.
     */
    List<CommandInteractionDataOption> getOptions();

    /**
     * @return true if this option is the currently focused option for autocomplete
     */
    Optional<Boolean> isFocused();
}
