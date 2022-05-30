package io.github.realyusufismail.yusufsdiscordbot.ydl.entities;

import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

public interface UnavailableGuild extends SnowFlake, GenericEntity {

    /**
     * Weather this gild is available or not.
     * 
     * @return false if available, true if not.
     */
    Boolean isAvailable();
}
