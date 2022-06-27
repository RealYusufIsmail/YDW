package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;

import java.util.Optional;

public interface GuildChannel extends Channel, Comparable<GuildChannel> {
    Optional<Guild> getGuild();
}
