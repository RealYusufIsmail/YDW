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

package io.github.realyusufismail.ydwreg.application.commands.option.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.CommandTypes;
import io.github.realyusufismail.ydw.application.interaction.InteractionData;
import io.github.realyusufismail.ydw.application.interaction.resolved.ResolvedData;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydwreg.application.commands.option.interaction.resolved.ResolvedDataReg;
import io.github.realyusufismail.ydwreg.message_components.ComponentType;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
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

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
