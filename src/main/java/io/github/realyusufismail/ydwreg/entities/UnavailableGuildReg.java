/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
