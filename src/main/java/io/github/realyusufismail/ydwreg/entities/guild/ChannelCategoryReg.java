package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.channel.ChannelType;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import io.github.realyusufismail.ydw.entities.guild.ChannelCategory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChannelCategoryReg extends ChannelReg implements ChannelCategory {

    private final long categoryId;

    private final List<Overwrite> permissionOverwrites = new ArrayList<>();
    private final String name;
    private final Boolean nsfw;
    private final Integer position;
    private final ChannelType type;

    public ChannelCategoryReg(@NotNull JsonNode chanelCategory, long id, @NotNull YDW ydw) {
        super(chanelCategory, id, ydw);

        this.categoryId = id;
        this.name = chanelCategory.hasNonNull("name") ? chanelCategory.get("name").asText() : null;
        this.nsfw =
                chanelCategory.hasNonNull("nsfw") ? chanelCategory.get("nsfw").asBoolean() : null;
        this.position =
                chanelCategory.hasNonNull("position") ? chanelCategory.get("position").asInt()
                        : null;
        this.type = ChannelType.getChannelType(chanelCategory.get("type").asInt());
    }

    @NotNull
    @Override
    public List<Overwrite> getPermissionOverwrites() {
        return permissionOverwrites;
    }

    @NotNull
    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @NotNull
    @Override
    public Optional<Boolean> isNSFW() {
        return Optional.ofNullable(nsfw);
    }

    @NotNull
    @Override
    public Optional<Integer> getPosition() {
        return Optional.ofNullable(position);
    }

    @NotNull
    @Override
    public ChannelType getType() {
        return type;
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return categoryId;
    }
}
