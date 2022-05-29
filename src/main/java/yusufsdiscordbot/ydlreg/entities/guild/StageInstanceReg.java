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

package yusufsdiscordbot.ydlreg.entities.guild;

import api.ydl.snowflake.SnowFlake;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.Guild;
import yusufsdiscordbot.ydl.entities.guild.Channel;
import yusufsdiscordbot.ydl.entities.guild.StageInstance;
import yusufsdiscordbot.ydl.entities.guild.StagePrivacyLevel;

public class StageInstanceReg implements StageInstance {
    private final YDL ydl;
    private final long id;

    private final Guild guild;
    private final Channel channel;
    private final String topic;
    private final StagePrivacyLevel privacyLevel;
    private final Boolean isDiscoverableDisabled;
    private final Long scheduledEventId;

    public StageInstanceReg(@NotNull JsonNode stage, long id, @NotNull YDL ydl) {
        this.ydl = ydl;
        this.id = id;

        this.guild = ydl.getGuild(stage.get("guildId").asLong());
        this.channel = ydl.getChannel(stage.get("channelId").asLong());
        this.topic = stage.get("topic").asText();
        this.privacyLevel = StagePrivacyLevel.valueOf(stage.get("privacyLevel").asText());
        this.isDiscoverableDisabled = stage.get("isDiscoverableDisabled").asBoolean();
        this.scheduledEventId = stage.get("ScheduledEvent").asLong();
    }

    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public StagePrivacyLevel getPrivacyLevel() {
        return privacyLevel;
    }

    @Override
    public Boolean isDiscoverableDisabled() {
        return isDiscoverableDisabled;
    }

    @Override
    public SnowFlake getScheduledEventId() {
        return SnowFlake.of(scheduledEventId);
    }
}
