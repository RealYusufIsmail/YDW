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

package io.github.realyusufismail.ydw.entities.sticker;

import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerFormatType;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerType;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
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
