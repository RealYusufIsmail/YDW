/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.activity.ActivityParty;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ActivityPartyReg implements ActivityParty {

    private final String id;
    private final Map<Integer, Integer> size = new HashMap<>();

    public ActivityPartyReg(@NotNull JsonNode party) {
        this.id = party.hasNonNull("id") ? party.get("id").asText() : null;

        if (party.hasNonNull("size")) {
            party.get("size").forEach(sizeNode -> {
                size.put(sizeNode.get("size").asInt(), sizeNode.get("count").asInt());
            });
        }
    }

    @NotNull
    @Override
    public Map<Integer, Integer> getSize() {
        return size;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id != null ? Long.parseLong(id) : null;
    }
}
