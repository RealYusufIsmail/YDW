package io.github.realyusufismail.ydw.entities.automod;

import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

public interface AutoModeration extends SnowFlake {
    Guild getGuild();

    String getName();

    SnowFlake getCreatorId();

    EventType getEventType();

    TriggerType getTriggerType();

    TriggerMetadata getTriggerMetadata();
}
