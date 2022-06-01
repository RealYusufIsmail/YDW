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

package io.github.realyusufismail.ydw.application.interaction;

import io.github.realyusufismail.ydw.application.commands.option.OptionType;
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

    OptionType getType();

    Optional<ResolvedData> getResolvedData();

    Optional<Guild> getGuild();

    Optional<String> getCustomId();

    Optional<ComponentType> getComponentType();

    Optional<SnowFlake> getTargetId();

}
