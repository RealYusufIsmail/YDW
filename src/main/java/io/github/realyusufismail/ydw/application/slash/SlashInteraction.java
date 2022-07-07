package io.github.realyusufismail.ydw.application.slash;

import io.github.realyusufismail.ydw.application.Interaction;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

public interface SlashInteraction extends Interaction {

    String getName();

    SnowFlake getInteractionId();
}
