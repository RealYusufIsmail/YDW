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

package io.github.realyusufismail.ydw.ydl.entities.sticker;

import io.github.realyusufismail.ydw.ydl.entities.GenericEntity;
import io.github.realyusufismail.ydw.ydl.entities.Guild;
import io.github.realyusufismail.ydw.ydl.entities.User;
import io.github.realyusufismail.ydw.ydlreg.entities.sticker.StickerFormatType;
import io.github.realyusufismail.ydw.ydlreg.entities.sticker.StickerType;
import io.github.realyusufismail.ydw.ydlreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface Sticker extends SnowFlake, GenericEntity {
    /**
     * for standard stickers, id of the pack the sticker is from
     *
     * @return the pack id
     */
    Optional<SnowFlake> getPackIdLong();

    /**
     * @return name of the sticker
     */
    String getName();

    /**
     * @return description of the sticker
     */
    String getDescription();

    /**
     * @return autocomplete/suggestion tags for the sticker (max 200 characters)
     */
    String getTags();

    /**
     * @return the type of sticker
     */
    @NotNull
    StickerType getType();

    /**
     * @return the format of the sticker
     */
    @NotNull
    StickerFormatType getFormat();

    /**
     * @return whether this guild sticker can be used, may be false due to loss of Server Boost
     */
    Optional<Boolean> isAvailable();

    /**
     * @return id of the guild that owns this sticker
     */
    @NotNull
    Optional<Guild> getGuild();

    /**
     * @return the user that uploaded the guild sticker
     */
    @NotNull
    Optional<User> getAuthor();

    /**
     * @return the standard sticker's sort order within its pack.
     */
    Optional<Integer> getSortValue();


    /**
     * Gets the list of the defualt stickers available to nitro users
     *
     * @return the list of sticker packs available to Nitro subscribers.
     */
    List<StickerPack> getStickerPacks();
}
