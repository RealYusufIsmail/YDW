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

package io.github.realyusufismail.ydwreg.application.commands.slash;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydwreg.application.commands.SlashCommandReg;
import io.github.realyusufismail.ydwreg.entities.channel.TextChannelReg;
import org.jetbrains.annotations.NotNull;

// TODO : once rest api is done start creating the rest api for this
public class SlashCommandInteraction extends SlashCommandReg {
    private final JsonNode JsonNode;
    private final YDW ydw;

    public SlashCommandInteraction(JsonNode slashCommand, JsonNode JsonNode, YDW ydw) {
        super(ydw, slashCommand);
        this.JsonNode = JsonNode;
        this.ydw = ydw;
    }

    public @NotNull TextChannel getTextChannel() {
        return new TextChannelReg(JsonNode.getAsJsonNode("channel"), ydw);
    }
}
