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
package io.github.realyusufismail.ydwreg.entities.guild.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.cache.snowflake.SnowflakeCache;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.NewsChannel;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.entities.guild.GuildChannelReg;
import org.jetbrains.annotations.NotNull;

public class NewsChannelReg extends GuildChannelReg implements NewsChannel {
    private final Long id;

    public NewsChannelReg(@NotNull JsonNode messageJson, long id, @NotNull YDW ydw) {
        this(null, messageJson, id, ydw);
    }

    public NewsChannelReg(GuildReg guildReg, @NotNull JsonNode json, long id, @NotNull YDW ydw) {
        super(guildReg, json, id, ydw);
        this.id = id;

        long guildId = json.get("guild_id").asLong();
        NewsChannelReg channel = (NewsChannelReg) getYDW().getNewsChannelCache().get(id);
        if (channel == null) {
            if (guildReg == null) {
                guildReg = (GuildReg) getYDW().getGuildCache().get(guildId);
                SnowflakeCache<NewsChannel> guildTextCache = getYDW().getNewsChannelCache(),
                        textCache = getYDW().getNewsChannelCache();

                channel = new NewsChannelReg(guildReg, json, id, ydw);
                guildTextCache.getCacheMap().put(id, channel);
                textCache.getCacheMap().put(id, channel);
            }
        }
    }

    @NotNull
    public Long getIdLong() {
        return id;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(id, o.getIdLong());
    }
}
