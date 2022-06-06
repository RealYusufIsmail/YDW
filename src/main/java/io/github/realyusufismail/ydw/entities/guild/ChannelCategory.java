package io.github.realyusufismail.ydw.entities.guild;

import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface ChannelCategory extends GuildChannel, SnowFlake {
    @Override
    List<Overwrite> getPermissionOverwrites();

    @Override
    Optional<String> getName();

    @Override
    Optional<Boolean> isNSFW();

    @Override
    Optional<Integer> getPosition();

    @NotNull
    @Override
    ChannelType getType();
}
