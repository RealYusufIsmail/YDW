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
package io.github.realyusufismail.ydwreg.handle;

import com.fasterxml.jackson.databind.JsonNode;
import gnu.trove.map.TLongObjectMap;
import io.github.realyusufismail.cache.Cache;

import java.util.EnumMap;
import java.util.List;

public class EventCache {
    private final EnumMap<CacheType, TLongObjectMap<List<CacheNode>>> eventEnum =
            new EnumMap<>(CacheType.class);

    public synchronized void playbackCache(CacheType type, long id) {
        TLongObjectMap<List<CacheNode>> cacheType = this.eventEnum.get(type);
        if (cacheType == null)
            return;

        List<CacheNode> items = cacheType.remove(id);
        if (items != null && !items.isEmpty()) {
            for (CacheNode item : items) {
                item.execute();
            }
        }
    }

    public enum CacheType {
        CHANNEL,
        USER,
        GUILD,
        MEMBER
    }

    private record CacheNode(JsonNode jsonNode, Cache callback) {
        void execute() {
                callback.execute(jsonNode);
            }
        }
}
