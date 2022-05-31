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

package io.github.realyusufismail.ydwreg.entities.voice;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.voice.VoiceRegion;
import org.jetbrains.annotations.NotNull;

public class VoiceRegionReg implements VoiceRegion {
    private final String id;
    private final YDW ydw;

    private final String name;
    @NotNull
    private final Boolean optimal;
    @NotNull
    private final Boolean deprecated;
    @NotNull
    private final Boolean custom;

    public VoiceRegionReg(@NotNull JsonNode voice, String id, YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.name = voice.get("name").asText();
        this.optimal = voice.get("optimal").asBoolean();
        this.deprecated = voice.get("deprecated").asBoolean();
        this.custom = voice.get("custom").asBoolean();
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isOptimal() {
        return optimal;
    }

    @Override
    public Boolean isDeprecated() {
        return deprecated;
    }

    @Override
    public Boolean isCustom() {
        return custom;
    }
}
