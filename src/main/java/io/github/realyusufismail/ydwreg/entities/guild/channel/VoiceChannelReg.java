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
package io.github.realyusufismail.ydwreg.entities.guild.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.cache.SnowFlakeCache;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.entities.guild.channel.StageChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.VoiceChannel;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.entities.channel.OverwriteReg;
import io.github.realyusufismail.ydwreg.handle.EventCache;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoiceChannelReg extends ChannelReg implements VoiceChannel {
    private final YDW ydw;
    private final long id;

    protected final long guildId;
    private String name;
    private Boolean isNSFW;
    private Integer position;
    private List<Overwrite> permissionOverwrites = new ArrayList<>();
    private Integer bitrate;
    private Integer userLimit;
    private Long parentId;
    private String rtcRegion;

    public VoiceChannelReg(@NotNull JsonNode json, long id, @NotNull YDW ydw) {
        this(null, json, id, ydw);
    }

    public VoiceChannelReg(GuildReg guildReg, @NotNull JsonNode json, long id, @NotNull YDW ydw) {
        super(json, id, ydw);
        this.ydw = ydw;
        this.id = id;

        this.guildId = json.get("guild_id").asLong();
        this.name = json.hasNonNull("name") ? json.get("name").asText() : null;
        this.isNSFW = json.hasNonNull("nsfw") ? json.get("nsfw").asBoolean() : null;
        this.position = json.hasNonNull("position") ? json.get("position").asInt() : null;
        this.bitrate = json.hasNonNull("bitrate") ? json.get("bitrate").asInt() : null;
        this.userLimit = json.hasNonNull("user_limit") ? json.get("user_limit").asInt() : null;
        this.parentId = json.hasNonNull("parent_id") ? json.get("parent_id").asLong() : null;
        this.rtcRegion = json.hasNonNull("rtc_region") ? json.get("rtc_region").asText() : null;

        if (json.hasNonNull("permission_overwrites")) {
            for (JsonNode permissionOverwrite : json.get("permission_overwrites")) {
                this.permissionOverwrites.add(new OverwriteReg(permissionOverwrite,
                        permissionOverwrite.get("id").asLong(), ydw));
            }
        }

        boolean playbackCache = false;
        // cache
        VoiceChannelReg channel = (VoiceChannelReg) getYDW().getVoiceChannelCache().get(id);
        if (channel == null) {
            if (guildReg == null)
                guildReg = (GuildReg) getYDW().getGuildCache().get(guildId);

            SnowFlakeCache<VoiceChannel> guildVoiceView = guildReg.getVoiceChannelCache(),
                    voiceView = getYDW().getVoiceChannelCache();

            channel = this;
            guildVoiceView.put(id, channel);
            playbackCache = voiceView.put(id, channel) == null;
        }

        if (playbackCache)
            getYDW().getEventCache().playbackCache(EventCache.CacheType.CHANNEL, id);
    }

    @Override
    public YDWReg getYDW() {
        return (YDWReg) ydw;
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

    @Override
    public Guild getGuild() {
        return ydw.getGuild(guildId);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<Category> getCategory() {
        return Optional.ofNullable(ydw.getChannel(Category.class, parentId));
    }

    @Override
    public boolean isNSFW() {
        return isNSFW;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public List<Overwrite> getPermissionOverwrites() {
        return permissionOverwrites;
    }

    @Override
    public int getBitrate() {
        return bitrate;
    }

    @Override
    public int getUserLimit() {
        return userLimit;
    }

    @Override
    public String getRTCRegion() {
        return rtcRegion;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setNSFW(Boolean NSFW) {
        isNSFW = NSFW;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setPermissionOverwrites(List<Overwrite> permissionOverwrites) {
        this.permissionOverwrites = permissionOverwrites;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    public void setUserLimit(Integer userLimit) {
        this.userLimit = userLimit;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setRtcRegion(String rtcRegion) {
        this.rtcRegion = rtcRegion;
    }
}
