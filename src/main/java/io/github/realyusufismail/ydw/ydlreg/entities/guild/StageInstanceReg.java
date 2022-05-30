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

package io.github.realyusufismail.ydw.ydlreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.Guild;
import io.github.realyusufismail.ydw.ydl.entities.guild.Channel;
import io.github.realyusufismail.ydw.ydl.entities.guild.StageInstance;
import io.github.realyusufismail.ydw.ydl.entities.guild.StagePrivacyLevel;
import io.github.realyusufismail.ydw.ydlreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

public class StageInstanceReg implements StageInstance {
    @NotNull
    private final YDL ydl;
    private final long id;

    private final Guild guild;
    private final Channel channel;
    private final String topic;
    @NotNull
    private final StagePrivacyLevel privacyLevel;
    @NotNull
    private final Boolean isDiscoverableDisabled;
    @NotNull
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

    @NotNull
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

    @NotNull
    @Override
    public SnowFlake getScheduledEventId() {
        return SnowFlake.of(scheduledEventId);
    }
}
