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
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.option.OptionType;
import io.github.realyusufismail.ydw.application.commands.slash.option.SlashOptionGetter;
import io.github.realyusufismail.ydwreg.application.commands.option.CommandOptionMapping;
import io.github.realyusufismail.ydwreg.application.commands.slash.SlashCommandReg;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlashOptionGetterReg extends SlashCommandReg implements SlashOptionGetter {

    private final List<CommandOptionMapping> optionMappings = new ArrayList<>();

    public SlashOptionGetterReg(@NotNull JsonNode application, long id, YDW ydw) {
        super(application, id, ydw);

        if (application.hasNonNull("options")) {
            application.get("options").forEach(option -> {
                optionMappings.add(new CommandOptionMapping(application.get("options"),
                        OptionType.getOptionType(option.get("type").asInt())));
            });
        }
    }

    public List<CommandOptionMapping> getCommandOptions() {
        return optionMappings;
    }
}

