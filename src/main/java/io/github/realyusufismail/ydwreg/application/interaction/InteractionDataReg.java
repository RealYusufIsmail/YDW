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

package io.github.realyusufismail.ydwreg.application.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.CommandTypes;
import io.github.realyusufismail.ydw.application.interaction.InteractionData;
import io.github.realyusufismail.ydw.application.interaction.resolved.ResolvedData;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydwreg.application.interaction.resolved.ResolvedDataReg;
import io.github.realyusufismail.ydwreg.message_components.ComponentType;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class InteractionDataReg implements InteractionData {

    private final YDW ydw;
    private final long id;

    private final String name;
    private final CommandTypes type;
    private final ResolvedData resolvedData;
    private final Guild guild;
    private final String customId;
    private final ComponentType componentType;
    private final Long targetId;

    public InteractionDataReg(JsonNode interaction, long id, YDW ydw) {
        this.ydw = ydw;
        this.id = id;
        this.name = interaction.get("name").asText();
        this.type = CommandTypes.getCommandType(interaction.get("type").asInt());
        this.resolvedData = interaction.hasNonNull("resolved")
                ? new ResolvedDataReg(interaction.get("resolved"), ydw)
                : null;
        this.guild = interaction.hasNonNull("guild_id")
                ? ydw.getGuild(interaction.get("guild_id").asLong())
                : null;
        this.customId =
                interaction.hasNonNull("custom_id") ? interaction.get("custom_id").asText() : null;
        this.componentType = interaction.hasNonNull("component_type")
                ? ComponentType.getComponentType(interaction.get("component_type").asInt())
                : null;
        this.targetId =
                interaction.hasNonNull("target_id") ? interaction.get("target_id").asLong() : null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CommandTypes getType() {
        return type;
    }

    @Override
    public Optional<ResolvedData> getResolvedData() {
        return Optional.ofNullable(resolvedData);
    }

    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @Override
    public Optional<String> getCustomId() {
        return Optional.ofNullable(customId);
    }

    @Override
    public Optional<ComponentType> getComponentType() {
        return Optional.ofNullable(componentType);
    }

    @Override
    public Optional<SnowFlake> getTargetId() {
        return Optional.ofNullable(targetId).map(SnowFlake::of);
    }

    @Nullable
    @Override
    public YDW getYDW() {
        return ydw;
    }

    @Nullable
    @Override
    public Long getIdLong() {
        return id;
    }
}
