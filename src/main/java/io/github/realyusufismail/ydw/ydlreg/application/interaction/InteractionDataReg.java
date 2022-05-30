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

package io.github.realyusufismail.ydw.ydlreg.application.interaction;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydlreg.application.interaction.resolved.ResolvedDataReg;
import io.github.realyusufismail.ydw.ydl.application.commands.option.OptionTypeEnum;
import io.github.realyusufismail.ydw.ydl.application.interaction.InteractionData;
import io.github.realyusufismail.ydw.ydl.application.interaction.resolved.ResolvedData;
import io.github.realyusufismail.ydw.ydlreg.application.commands.option.ApplicationOptionDataReg;
import org.jetbrains.annotations.NotNull;

public class InteractionDataReg extends ApplicationOptionDataReg implements InteractionData {
    private final JsonNode interaction;

    public InteractionDataReg(JsonNode interaction) {
        super(interaction);
        this.interaction = interaction;
    }

    @Override
    public String getName() {
        return interaction.get("name").asText();
    }

    @Override
    public @NotNull OptionTypeEnum getType() {
        return OptionTypeEnum.getOptionType(interaction.get("type").asInt());
    }

    @Override
    public @NotNull ResolvedData getResolvedData() {
        return new ResolvedDataReg(interaction.getAsJsonNode("resolved"));
    }

    @Override
    public String getCustomId() {
        return interaction.get("customId").asText();
    }

    @Override
    public int getComponentType() {
        return interaction.get("componentType").asInt();
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return interaction.get("id").asLong();
    }
}
