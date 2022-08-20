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
import gnu.trove.map.hash.TLongObjectHashMap;
import io.github.realyusufismail.cache.Cache;
import io.github.realyusufismail.ydwreg.YDWReg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Heavily inspired from <a href=
 * "https://github.com/DV8FromTheWorld/JDA/blob/ebe3d3aa26ca8ca41aa95e759f00c553efa58570/src/main/java/net/dv8tion/jda/internal/handle/EventCache.java"
 * target="_blank">JDA</a>
 */
public class EventCache {
    public static final Logger logger = LoggerFactory.getLogger(EventCache.class);
    private final EnumMap<CacheType, TLongObjectMap<List<CacheNode>>> eventEnum =
            new EnumMap<>(CacheType.class);

    public synchronized void cache(CacheType type, long id, JsonNode event, Cache cache) {
        TLongObjectMap<List<CacheNode>> triggerCache =
                eventEnum.computeIfAbsent(type, k -> new TLongObjectHashMap<>());

        List<CacheNode> items = triggerCache.get(id);
        if (items == null) {
            items = new LinkedList<>();
            triggerCache.put(id, items);
        }

        items.add(new CacheNode(event, cache));
    }

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

    public synchronized void clear(CacheType type, long id) {
        TLongObjectMap<List<CacheNode>> typeCache = this.eventEnum.get(type);
        if (typeCache == null)
            return;

        List<CacheNode> events = typeCache.remove(id);
        if (events != null)
            YDWReg.logger.debug("Clearing cache for type {} with ID {} (Size: {})", type, id,
                    events.size());
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
