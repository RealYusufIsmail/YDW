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

package io.github.realyusufismail.ydw.ydlreg.application.commands;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydlreg.application.commands.slash.option.SlashCommandOptionReg;
import io.github.realyusufismail.ydw.ydl.application.commands.reply.IReply;
import io.github.realyusufismail.ydw.ydl.application.commands.slash.SlashCommand;

public class SlashCommandReg extends SlashCommandOptionReg implements SlashCommand, IReply {
    private final YDL ydl;

    public SlashCommandReg(YDL ydl, long id, JsonNode option) {
        this.ydl = ydl;
    }


    /**
     * @return The core long of this api.
     */
    @Override
    public Long getIdLong() {
        return id;
    }
}
