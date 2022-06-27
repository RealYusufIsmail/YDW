package io.github.realyusufismail.ydw.entities.guild.channel;

import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ThreadChannel extends GuildChannel {
    @NotNull
    @Override
    ChannelType getType();

    @Override
    Optional<Category> getCategory();
}

