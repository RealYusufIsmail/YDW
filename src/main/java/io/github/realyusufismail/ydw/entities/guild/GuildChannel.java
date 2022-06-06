package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.Guild;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface GuildChannel extends Channel, Comparable<GuildChannel> {
    Optional<Guild> getGuild();

    default int compareTo(@NotNull GuildChannel o) {
        return Long.compare(getGuild().get().getIdLong(), o.getGuild().get().getIdLong());
    }
}
