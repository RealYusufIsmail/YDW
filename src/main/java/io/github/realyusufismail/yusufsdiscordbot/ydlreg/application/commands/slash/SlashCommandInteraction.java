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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.application.commands.slash;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.channel.TextChannel;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.application.commands.SlashCommandReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.channel.TextChannelReg;

// TODO : once rest api is done start creating the rest api for this
public class SlashCommandInteraction extends SlashCommandReg {
    private final JsonNode JsonNode;
    private final YDL ydl;

    public SlashCommandInteraction(JsonNode slashCommand, JsonNode JsonNode, YDL ydl) {
        super(ydl, slashCommand);
        this.JsonNode = JsonNode;
        this.ydl = ydl;
    }

    public @NotNull TextChannel getTextChannel() {
        return new TextChannelReg(JsonNode.getAsJsonNode("channel"), ydl);
    }
}
