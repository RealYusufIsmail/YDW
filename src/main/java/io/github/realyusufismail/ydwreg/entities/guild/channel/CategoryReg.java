package io.github.realyusufismail.ydwreg.entities.guild.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydwreg.entities.ChannelReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CategoryReg extends ChannelReg implements Category {
    private final long id;

    private final String name;
    private final Long parentId;
    private final Boolean nsfw;
    private final Integer position;
    private final Guild guild;

    public CategoryReg(@NotNull JsonNode channelJ, long id, @NotNull YDW ydw) {
        super(channelJ, id, ydw);
        this.id = id;

        this.name = channelJ.hasNonNull("name") ? channelJ.get("name").asText() : null;
        this.parentId =
                channelJ.hasNonNull("parent_id") ? channelJ.get("parent_id").asLong() : null;
        this.nsfw = channelJ.hasNonNull("nsfw") ? channelJ.get("nsfw").asBoolean() : null;
        this.position = channelJ.hasNonNull("position") ? channelJ.get("position").asInt() : null;
        this.guild =
                channelJ.hasNonNull("guild_id") ? ydw.getGuild(channelJ.get("guild_id").asLong())
                        : null;
    }

    @NotNull
    @Override
    public ChannelType getType() {
        return Category.super.getType();
    }

    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @Override
    public Optional<SnowFlake> getParentId() {
        return Optional.ofNullable(parentId).map(SnowFlake::of);
    }

    @Override
    public Optional<Boolean> isNSFW() {
        return Optional.ofNullable(nsfw);
    }

    @Override
    public Optional<Integer> getPosition() {
        return Optional.ofNullable(position);
    }

    @Override
    public Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @Override
    public int compareTo(@NotNull GuildChannel o) {
        return Long.compare(id, o.getIdLong());
    }
}
