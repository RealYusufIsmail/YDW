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

package yusufsdiscordbot.ydlreg.application.commands.slash.option;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.application.commands.slash.option.SlashCommandOptionChoice;
import yusufsdiscordbot.ydlreg.YDLReg;
import yusufsdiscordbot.ydlreg.application.commands.option.CommandOptionChoiceReg;
import yusufsdiscordbot.ydlreg.rest.callers.SlashCommandCaller;

public class SlashCommandOptionChoiceReg extends CommandOptionChoiceReg
        implements SlashCommandOptionChoice {
    private final YDLReg ydl;

    private final SlashCommandCaller caller = getYDL().getRest().getSlashCommandCaller();

    public SlashCommandOptionChoiceReg(@NotNull JsonNode node, @NotNull YDLReg ydl) {
        super(node);
        this.ydl = ydl;
    }

    @Override
    public SlashCommandOptionChoice setName(String name) {
        return null;
    }

    @Override
    public SlashCommandOptionChoice setValue(long value) {
        return null;
    }

    @Override
    public SlashCommandOptionChoice setValue(String value) {
        return null;
    }

    @Override
    public SlashCommandOptionChoice setValue(double value) {
        return null;
    }

    private YDLReg getYDL() {
        return ydl;
    }
}
