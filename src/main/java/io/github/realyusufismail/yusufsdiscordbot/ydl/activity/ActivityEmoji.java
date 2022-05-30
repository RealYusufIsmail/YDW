package io.github.realyusufismail.yusufsdiscordbot.ydl.activity;

import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

import java.util.Optional;

public interface ActivityEmoji extends SnowFlake {

    /**
     * Gets the name of the emoji.
     * 
     * @return The name of the emoji.
     */
    String getName();

    /**
     * Weather the emoji is a gif/animated.
     * 
     * @return True if the emoji is a gif/animated.
     */
    Optional<Boolean> isAnimated();
}
