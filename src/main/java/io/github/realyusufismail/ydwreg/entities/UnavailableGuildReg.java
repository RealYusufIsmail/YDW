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

package io.github.realyusufismail.ydwreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.UnavailableGuild;
import org.jetbrains.annotations.NotNull;

public class UnavailableGuildReg implements UnavailableGuild {

    private final YDW ydw;
    private final long id;

    @NotNull
    private final Boolean isUnAvailable;

    public UnavailableGuildReg(YDW ydw, long id, @NotNull JsonNode json) {
        this.ydw = ydw;
        this.id = id;

        this.isUnAvailable = json.get("unavailable").asBoolean();
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @Override
    public Boolean isUnAvailable() {
        return isUnAvailable;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }
}
