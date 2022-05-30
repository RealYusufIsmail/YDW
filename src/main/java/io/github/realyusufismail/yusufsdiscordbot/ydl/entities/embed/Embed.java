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

package io.github.realyusufismail.yusufsdiscordbot.ydl.entities.embed;

import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.embed.objects.Image;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.embed.objects.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface Embed {
    @NotNull
    Optional<String> getTitle();

    @NotNull
    Optional<EmbedType> getEmbedType();

    @NotNull
    Optional<String> getDescription();

    @NotNull
    Optional<String> getUrl();

    @NotNull
    Optional<ZonedDateTime> getTimeStamp();

    @NotNull
    Optional<Color> getColour();

    @NotNull
    Optional<Footer> getFooter();

    @NotNull
    Optional<Image> getImage();

    @NotNull
    Optional<Thumbnail> getThumbnail();

    @NotNull
    Optional<Video> getVideo();

    @NotNull
    Optional<Provider> getProvider();

    @NotNull
    Optional<Author> getAuthor();

    @NotNull
    List<Fields> getFields();

    enum EmbedType {
        /**
         * generic embed rendered from embed attributes
         */
        RICH("rich"),
        /**
         * image embed
         */
        IMAGE("image"),
        /**
         * video embed
         */
        VIDEO("video"),
        /**
         * animated gif image embed rendered as a video embed
         */
        GIF("gif"),
        /**
         * article embed
         */
        ARTICLE("article"),
        /**
         * link embed
         */
        LINK("link"),
        /**
         * For future use or if invalid.
         */
        UNKNOWN("unknown");

        private final String embedTypes;

        EmbedType(String embedTypes) {
            this.embedTypes = embedTypes;
        }

        public static @NotNull EmbedType getEmbedType(String embedType) {
            for (EmbedType type : values()) {
                if (Objects.equals(type.getEmbedType(), embedType)) {
                    return type;
                }
            }
            return UNKNOWN;
        }

        public String getEmbedType() {
            return embedTypes;
        }
    }

}
