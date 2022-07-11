/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.github.realyusufismail.ydw.application.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
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

    ObjectNode toJson();
}
