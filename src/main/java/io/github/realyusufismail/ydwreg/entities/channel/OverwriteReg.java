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
package io.github.realyusufismail.ydwreg.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import org.jetbrains.annotations.NotNull;

public class OverwriteReg implements Overwrite {
    @NotNull
    private final JsonNode json;
    private final long id;
    private final YDW ydw;

    private Overwrite.OverwriteType type;
    private String allow;
    private String deny;

    public OverwriteReg(@NotNull JsonNode json, long id, YDW ydw) {
        this.json = json;
        this.id = id;
        this.ydw = ydw;

        this.setType(OverwriteType.getOverwriteType(json.get("type").asInt()));
        this.setAllow(json.get("allow").asText());
        this.setDeny(json.get("deny").asText());
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public OverwriteType getType() {
        return type;
    }

    @NotNull
    private OverwriteReg setType(OverwriteType type) {
        this.type = type;
        return this;
    }

    @Override
    public String getAllow() {
        return allow;
    }

    @NotNull
    private OverwriteReg setAllow(String allow) {
        this.allow = allow;
        return this;
    }

    @Override
    public String getDeny() {
        return deny;
    }

    @NotNull
    private OverwriteReg setDeny(String deny) {
        this.deny = deny;
        return this;
    }
}
