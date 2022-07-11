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

package io.github.realyusufismail.ydw.team;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface Team extends SnowFlake, GenericEntity {

    /**
     * Gets the hash icon of the team.
     *
     * @return The hash icon of the team.
     */
    Optional<String> getIcon();

    /**
     * Gets the list of members who are in the team.
     *
     * @return The list of members who are in the team. If the list is empty, the team is empty.
     */
    @NotNull
    List<TeamMember> getMembers();

    /**
     * Gets the name of the team.
     *
     * @return The name of the team.
     */
    String getName();

    /**
     * Gets the id of the owner of the team.
     *
     * @return The id of the owner of the team.
     */
    @NotNull
    SnowFlake getOwnerId();
}
