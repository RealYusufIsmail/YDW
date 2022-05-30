package io.github.realyusufismail.ydw.ydl.activity;

import io.github.realyusufismail.ydw.ydlreg.snowflake.SnowFlake;

import java.util.Map;

public interface ActivityParty extends SnowFlake {

    /**
     * This is used to show the party's current and maximum size.
     * 
     * @return The current and maximum size of the party.
     */
    Map<Integer, Integer> getSize();
}
