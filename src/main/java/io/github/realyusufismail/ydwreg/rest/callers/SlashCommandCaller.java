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

package io.github.realyusufismail.ydwreg.rest.callers;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.option.CommandType;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.Option;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.OptionExtender;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class SlashCommandCaller {
    private final YDWReg ydw;

    private final OkHttpClient client;

    private String name;
    private String description;
    private final Integer commandType = CommandType.CHAT_INPUT.getValue();
    private Collection<Option> options;
    private Collection<OptionExtender> optionExtenders;

    public SlashCommandCaller(@NotNull YDW ydw) {
        this.ydw = (YDWReg) ydw;
        this.client = ((YDWReg) ydw).getHttpClient();
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOptions(Collection<Option> options) {
        this.options = options;
    }

    public void setOptionExtenders(Collection<OptionExtender> optionExtenders) {
        this.optionExtenders = optionExtenders;
    }
}
