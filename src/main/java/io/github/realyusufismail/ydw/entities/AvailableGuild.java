package io.github.realyusufismail.ydw.entities;

import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

public interface AvailableGuild extends SnowFlake, GenericEntity {
    /**
     * Weather this guild is available or not.
     *
     * @return false if available, true if not.
     */
    Boolean isAvailable();
}

