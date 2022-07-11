/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydw.application.interaction;

import io.github.realyusufismail.ydw.application.commands.CommandTypes;
import io.github.realyusufismail.ydw.application.interaction.resolved.ResolvedData;
import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydwreg.message_components.ComponentType;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

import java.util.Optional;

/// TODO: add values and components. see here
/// https://discord.com/developers/docs/interactions/message-components#select-menu-object-select-option-structure
public interface InteractionData extends SnowFlake, GenericEntity {
    String getName();

    CommandTypes getType();

    Optional<ResolvedData> getResolvedData();

    Optional<Guild> getGuild();

    Optional<String> getCustomId();

    Optional<ComponentType> getComponentType();

    Optional<SnowFlake> getTargetId();

}
