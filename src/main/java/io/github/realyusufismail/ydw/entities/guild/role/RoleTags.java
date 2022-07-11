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

package io.github.realyusufismail.ydw.entities.guild.role;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface RoleTags extends GenericEntity {

    /**
     * Gets the id of the bot that owns the role.
     *
     * @return The id of the bot that owns the role.
     */
    @NotNull
    Optional<SnowFlake> getBotId();

    /**
     * Gets the id of the integration this role belongs to/
     *
     * @return The id of the integration this role belongs to.
     */
    @NotNull
    Optional<SnowFlake> getIntegrationId();

    /**
     * Weather the role is a nitro boost role.
     *
     * @return true if the role is a nitro boost role, false otherwise.
     */
    Optional<Boolean> isPremiumSubscriber();
}
