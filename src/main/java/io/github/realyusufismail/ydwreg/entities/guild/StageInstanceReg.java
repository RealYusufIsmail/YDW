/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
 */

package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.guild.StageInstance;
import io.github.realyusufismail.ydw.entities.guild.StagePrivacyLevel;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

public class StageInstanceReg implements StageInstance {
    @NotNull
    private final YDW ydw;
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

    public StageInstanceReg(@NotNull JsonNode stage, long id, @NotNull YDW ydw) {
        this.ydw = ydw;
        this.id = id;

        this.guild = ydw.getGuild(stage.get("guildId").asLong());
        this.channel = ydw.getChannel(stage.get("channelId").asLong());
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
