package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

public interface GuildApplicationCommandPermission extends SnowFlake {
    SnowFlake getApplicationId();

    Guild getGuild();
}
