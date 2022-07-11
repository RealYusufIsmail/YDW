/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
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
