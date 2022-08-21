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
package io.github.realyusufismail.cache.guild;

import com.fasterxml.jackson.databind.JsonNode;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.set.TLongSet;
import gnu.trove.set.hash.TLongHashSet;
import io.github.realyusufismail.ydwreg.YDWReg;

public class GuildCacheHandler {
    private final YDWReg ydw;
    private final TLongObjectMap<GuildStarter> guildStarters = new TLongObjectHashMap<>();
    private final TLongSet unavailableGuilds = new TLongHashSet();

    public GuildCacheHandler(YDWReg ydw) {
        this.ydw = ydw;
    }

    public void onCreate(long id, JsonNode jsonNode) {
        //need to check if the guild is available or not
        boolean available = jsonNode.hasNonNull("unavailable") && jsonNode.get("unavailable").asBoolean();

        if (available && unavailableGuilds.contains(id) && !guildStarters.containsKey(id)) {
            unavailableGuilds.remove(id);
            guildStarters.put(id, new GuildStarter(id, this, Type.CREATING));
        }
    }

    public void onDelete() {
        
    }

    public void onMemberChunk() {

    }

    public void onMemberJoin() {

    }

    public void onMemberLeave() {

    }

    enum Type {
        INITIALIZING, CHUNKING, CREATING, READY, UNAVAILABLE, REMOVING
    }
}
