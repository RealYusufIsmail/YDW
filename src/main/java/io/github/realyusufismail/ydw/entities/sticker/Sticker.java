/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
