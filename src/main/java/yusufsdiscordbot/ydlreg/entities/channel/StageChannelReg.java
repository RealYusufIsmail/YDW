/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package yusufsdiscordbot.ydlreg.entities.channel;

import api.ydl.cache.util.HookLock;
import api.ydl.snowflake.reg.SnowFlakeCacheRetrieverReg;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.guild.GuildChannel;
import yusufsdiscordbot.ydl.entities.guild.channel.StageChannel;
import yusufsdiscordbot.ydlreg.YDLReg;
import yusufsdiscordbot.ydlreg.entities.GuildReg;
import yusufsdiscordbot.ydlreg.entities.guild.ChannelReg;

public class StageChannelReg extends ChannelReg implements StageChannel {
    private final YDL ydl;

    public StageChannelReg(JsonNode json, long id, YDL ydl) {
        super(json, id, ydl);
        this.ydl = ydl;
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(getIdLong(), o.getIdLong());
    }

    @Override
    public YDLReg getYDL() {
        return (YDLReg) ydl;
    }

    public StageChannel build(JsonNode object, long guildId) {
        return build(null, object, guildId);
    }

    public StageChannel build(GuildReg guildReg, JsonNode object, long guildId) {
        boolean playbackCache = false;
        final long id = object.get("id").asLong();
        StageChannelReg channelReg =
                (StageChannelReg) getYDL().getStageChannelCacheReg().get(object.get("id").asLong());
        if (channelReg == null) {
            if (guildReg == null)
                guildReg = (GuildReg) getYDL().getGuildCacheReg().get(guildId);
            SnowFlakeCacheRetrieverReg<StageChannel> channelRetriever =
                    guildReg.getStageChannelsReg(),
                    textRetriever = getYDL().getStageChannelCacheReg();

            try (HookLock guildLock = channelRetriever.writeLock();
                    HookLock textLock = textRetriever.writeLock()) {
                channelReg = new StageChannelReg(id, getYDL());
                channelRetriever.getMap().put(id, channelReg);
                playbackCache = textRetriever.getMap().put(id, channelReg) == null;
            }
        }
        return channelReg;
    }
}
