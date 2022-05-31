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
import io.github.realyusufismail.ydw.application.commands.option.OptionTypeEnum;
import io.github.realyusufismail.ydw.application.interaction.InteractionData;
import io.github.realyusufismail.ydw.application.interaction.resolved.ResolvedData;
import io.github.realyusufismail.ydwreg.application.commands.option.ApplicationOptionDataReg;
import io.github.realyusufismail.ydwreg.application.interaction.resolved.ResolvedDataReg;
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
