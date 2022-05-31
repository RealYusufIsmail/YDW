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

package io.github.realyusufismail.ydwreg.application.commands.slash.option;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.application.commands.slash.option.SlashCommandOptionChoice;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.application.commands.option.CommandOptionChoiceReg;
import io.github.realyusufismail.ydwreg.rest.callers.SlashCommandCaller;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlashCommandOptionChoiceReg extends CommandOptionChoiceReg
        implements SlashCommandOptionChoice {
    @NotNull
    private final YDWReg ydw;

    private final SlashCommandCaller caller = getYDW().getRest().getSlashCommandCaller();

    public SlashCommandOptionChoiceReg(@NotNull JsonNode node, @NotNull YDWReg ydw) {
        super(node);
        this.ydw = ydw;
    }

    @Nullable
    @Override
    public SlashCommandOptionChoice setName(String name) {
        return null;
    }

    @Nullable
    @Override
    public SlashCommandOptionChoice setValue(long value) {
        return null;
    }

    @Nullable
    @Override
    public SlashCommandOptionChoice setValue(String value) {
        return null;
    }

    @Nullable
    @Override
    public SlashCommandOptionChoice setValue(double value) {
        return null;
    }

    @Nullable
    private YDWReg getYDW() {
        return ydw;
    }
}
