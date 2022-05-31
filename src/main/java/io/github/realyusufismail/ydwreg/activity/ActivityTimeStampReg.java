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

package io.github.realyusufismail.ydwreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.activity.ActivityTimeStamp;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ActivityTimeStampReg implements ActivityTimeStamp {

    private final Integer start;
    private final Integer end;

    public ActivityTimeStampReg(@NotNull JsonNode timeStamp) {
        this.start = timeStamp.hasNonNull("start") ? timeStamp.get("start").asInt() : null;
        this.end = timeStamp.hasNonNull("end") ? timeStamp.get("end").asInt() : null;
    }


    @NotNull
    @Override
    public Optional<Integer> getStart() {
        return Optional.ofNullable(start);
    }

    @NotNull
    @Override
    public Optional<Integer> getEnd() {
        return Optional.ofNullable(end);
    }
}
