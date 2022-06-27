package io.github.realyusufismail.ydw.entities.guild.channel;

import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface Category extends GuildChannel, SnowFlake {
    @NotNull
    @Override
    default ChannelType getType() {
        return ChannelType.GUILD_CATEGORY;
    }

    @Override
    Optional<String> getName();

    @Override
    Optional<SnowFlake> getParentId();

    @Override
    Optional<Boolean> isNSFW();

    @Override
    Optional<Integer> getPosition();

    @Override
    Optional<Guild> getGuild();
}
