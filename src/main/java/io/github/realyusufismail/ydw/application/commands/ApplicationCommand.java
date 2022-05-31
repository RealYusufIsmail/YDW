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

package io.github.realyusufismail.ydw.application.commands;

import io.github.realyusufismail.ydw.application.commands.option.CommandOption;
import io.github.realyusufismail.ydw.application.commands.option.CommandType;
import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface ApplicationCommand extends SnowFlake, GenericEntity {

    /**
     * @return the type of command, defaults 1 if not set
     */
    Optional<CommandType> getCommandType();


    /**
     * @return unique id of the parent application
     */
    @NotNull
    SnowFlake getApplicationIdLong();

    /**
     * @return The Guild object for the guild this command is enabled in.
     */
    @NotNull
    Optional<Guild> getGuild();

    /**
     * Gets the name of the command.
     *
     * @return 1-32 character name.
     */
    String getName();

    /**
     * Gets the description of the command.
     *
     * @return 1-100 characters description.
     */
    String getDescription();

    /**
     * Gets the set of permissions represented as a bit set
     *
     * @return The permissions of the command.
     */
    Optional<String[]> getDefaultMemberPermissions();

    /**
     * Indicates whether the command is available in DMs with the app, only for globally-scoped
     * commands. By default, commands are visible.
     *
     * @return true if the command is visible in DMs, false otherwise.
     */
    boolean isDmVisible();

    List<CommandOption> getOptions();

    /**
     * @return autoincrement version identifier updated during substantial record changes
     */
    @NotNull
    SnowFlake getVersion();
}
