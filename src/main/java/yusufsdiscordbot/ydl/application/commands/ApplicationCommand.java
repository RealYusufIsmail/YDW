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

package yusufsdiscordbot.ydl.application.commands;

import api.ydl.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.application.commands.option.ApplicationCommandOption;
import yusufsdiscordbot.ydl.application.commands.option.CommandType;
import yusufsdiscordbot.ydl.entities.Guild;

import java.util.List;
import java.util.Optional;

public interface ApplicationCommand extends SnowFlake {

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

    List<ApplicationCommandOption> getOptions();

    /**
     * @return autoincrement version identifier updated during substantial record changes
     */
    @NotNull
    SnowFlake getVersion();
}
