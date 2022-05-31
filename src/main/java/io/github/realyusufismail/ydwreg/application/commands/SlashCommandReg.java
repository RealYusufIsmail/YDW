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

package io.github.realyusufismail.ydwreg.application.commands;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.reply.IReply;
import io.github.realyusufismail.ydw.application.commands.slash.SlashCommand;
import io.github.realyusufismail.ydwreg.application.commands.slash.option.SlashCommandOptionReg;

public class SlashCommandReg extends SlashCommandOptionReg implements SlashCommand, IReply {
    private final YDW ydw;

    public SlashCommandReg(YDW ydw, long id, JsonNode option) {
        this.ydw = ydw;
    }


    /**
     * @return The core long of this api.
     */
    @Override
    public Long getIdLong() {
        return id;
    }
}
