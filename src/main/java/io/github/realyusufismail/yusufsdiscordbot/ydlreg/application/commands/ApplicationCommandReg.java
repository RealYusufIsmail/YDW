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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.application.commands;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.application.commands.ApplicationCommand;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.application.commands.option.ApplicationOptionDataReg;
import org.jetbrains.annotations.NotNull;

public class ApplicationCommandReg extends ApplicationOptionDataReg implements ApplicationCommand {

    private final long id;

    public ApplicationCommandReg(JsonNode application, long id) {
        super(application);
        this.id = id;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
