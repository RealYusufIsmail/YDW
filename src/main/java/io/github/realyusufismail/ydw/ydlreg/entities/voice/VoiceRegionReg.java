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

package io.github.realyusufismail.ydw.ydlreg.entities.voice;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.voice.VoiceRegion;
import org.jetbrains.annotations.NotNull;

public class VoiceRegionReg implements VoiceRegion {
    private final String id;
    private final YDL ydl;

    private final String name;
    @NotNull
    private final Boolean optimal;
    @NotNull
    private final Boolean deprecated;
    @NotNull
    private final Boolean custom;

    public VoiceRegionReg(@NotNull JsonNode voice, String id, YDL ydl) {
        this.id = id;
        this.ydl = ydl;

        this.name = voice.get("name").asText();
        this.optimal = voice.get("optimal").asBoolean();
        this.deprecated = voice.get("deprecated").asBoolean();
        this.custom = voice.get("custom").asBoolean();
    }

    @Override
    public YDL getYDL() {
        return ydl;
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
