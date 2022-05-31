/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
 */

package io.github.realyusufismail.ydwreg.entities.sticker;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.entities.sticker.StickerPack;
import io.github.realyusufismail.ydwreg.entities.GuildReg;
import io.github.realyusufismail.ydwreg.entities.UserReg;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class StickerReg implements Sticker {
    private final YDW ydw;
    private final long id;

    private final Long packId;
    private final String name;
    private final String description;
    private final String tags;
    private final String asset;
    @NotNull
    private final StickerType type;
    @NotNull
    private final StickerFormatType format;
    private final Boolean available;
    @Nullable
    private final Guild guild;
    @Nullable
    private final User user;
    private final Integer sortValue;

    @Nullable
    private final List<StickerPack> pack;

    public StickerReg(@NotNull JsonNode sticker, long id, @NotNull YDW ydw) {
        this.id = id;
        this.ydw = ydw;

        this.packId = sticker.hasNonNull("pack_id") ? sticker.get("pack_id").asLong() : null;
        this.name = sticker.get("name").asText();
        this.description = sticker.get("description").asText();
        this.tags = sticker.get("tags").asText();
        this.asset = sticker.hasNonNull("asset") ? sticker.get("asset").asText() : null;
        this.type = StickerType.valueOf(sticker.get("type").asInt());
        this.format = StickerFormatType.valueOf(sticker.get("format").asInt());
        this.available =
                sticker.hasNonNull("available") ? sticker.get("available").asBoolean() : null;
        this.guild = sticker.hasNonNull("guild")
                ? new GuildReg(sticker.get("guild"), sticker.get("guild").get("id").asLong(), ydw)
                : null;
        this.user = sticker.hasNonNull("user")
                ? new UserReg(sticker.get("user"), sticker.get("user").get("id").asLong(), ydw)
                : null;
        this.sortValue =
                sticker.hasNonNull("sort_value") ? sticker.get("sort_value").asInt() : null;

        this.pack = ydw.getRest().getStickerCaller().getStickerPacks();
    }


    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @NotNull
    @Override
    public Optional<SnowFlake> getPackIdLong() {
        return Optional.ofNullable(packId).map(SnowFlake::of);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getTags() {
        return tags;
    }

    @Override
    public @NotNull StickerType getType() {
        return type;
    }

    @Override
    public @NotNull StickerFormatType getFormat() {
        return format;
    }

    @NotNull
    @Override
    public Optional<Boolean> isAvailable() {
        return Optional.ofNullable(available);
    }

    @Override
    public @NotNull Optional<Guild> getGuild() {
        return Optional.ofNullable(guild);
    }

    @Override
    public @NotNull Optional<User> getAuthor() {
        return Optional.ofNullable(user);
    }

    @NotNull
    @Override
    public Optional<Integer> getSortValue() {
        return Optional.ofNullable(sortValue);
    }

    @Override
    public List<StickerPack> getStickerPacks() {
        return pack;
    }
}
