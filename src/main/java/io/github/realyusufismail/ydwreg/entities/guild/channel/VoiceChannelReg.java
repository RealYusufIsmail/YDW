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
import io.github.realyusufismail.ydw.entities.guild.channel.VoiceChannel;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.entities.guild.GuildChannelReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoiceChannelReg extends GuildChannelReg implements VoiceChannel {
    private final YDW ydw;
    private final Long id;

    private final Integer bitrate;
    private final Integer userLimit;
    private final String rtcRegion;

    public VoiceChannelReg(@NotNull JsonNode json, long id, @NotNull YDW ydw) {
        this(null, json, id, ydw);
    }

    public VoiceChannelReg(GuildReg guildReg, @NotNull JsonNode json, long id, @NotNull YDW ydw) {
        super(json, id, ydw);
        this.ydw = ydw;
        this.id = id;

        long guildId = json.get("guild_id").asLong();
        VoiceChannelReg channel = (VoiceChannelReg) getYDW().getVoiceChannelCache().get(id);
        if (channel == null) {
            if (guildReg == null) {
                guildReg = (GuildReg) getYDW().getGuildCache().get(guildId);
                SnowflakeCache<VoiceChannel> guildTextCache = getYDW().getVoiceChannelCache(),
                        textCache = getYDW().getVoiceChannelCache();

                channel = new VoiceChannelReg(guildReg, json, id, ydw);
                guildTextCache.getCacheMap().put(id, channel);
                textCache.getCacheMap().put(id, channel);
            }
        }

        this.bitrate = json.hasNonNull("bitrate") ? json.get("bitrate").asInt() : null;
        this.userLimit = json.hasNonNull("user_limit") ? json.get("user_limit").asInt() : null;
        this.rtcRegion = json.hasNonNull("rtc_region") ? json.get("rtc_region").asText() : null;
    }


    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(id, o.getIdLong());
    }

    @Nullable
    @Override
    public YDWReg getYDW() {
        return (YDWReg) ydw;
    }

    @Override
    public Integer getBitrate() {
        return bitrate;
    }

    @Override
    public Integer getUserLimit() {
        return userLimit;
    }

    @Override
    public String getRTCRegion() {
        return rtcRegion;
    }
}
