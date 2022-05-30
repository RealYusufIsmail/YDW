/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.sticker;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.Guild;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.User;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.sticker.Sticker;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.sticker.StickerPack;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.GuildReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.UserReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class StickerReg implements Sticker {
    private final YDL ydl;
    private final long id;

    private final Long packId;
    private final String name;
    private final String description;
    private final String tags;
    private final String asset;
    private final StickerType type;
    private final StickerFormatType format;
    private final Boolean available;
    private final Guild guild;
    private final User user;
    private final Integer sortValue;

    private final List<StickerPack> pack;

    public StickerReg(JsonNode sticker, long id, YDL ydl) {
        this.id = id;
        this.ydl = ydl;

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
                ? new GuildReg(sticker.get("guild"), sticker.get("guild").get("id").asLong(), ydl)
                : null;
        this.user = sticker.hasNonNull("user")
                ? new UserReg(sticker.get("user"), sticker.get("user").get("id").asLong(), ydl)
                : null;
        this.sortValue =
                sticker.hasNonNull("sort_value") ? sticker.get("sort_value").asInt() : null;

        this.pack = ydl.getRest().getStickerCaller().getStickerPacks();
    }


    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public YDL getYDL() {
        return ydl;
    }

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

    @Override
    public Optional<Integer> getSortValue() {
        return Optional.ofNullable(sortValue);
    }

    @Override
    public List<StickerPack> getStickerPacks() {
        return pack;
    }
}
