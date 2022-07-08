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

package io.github.realyusufismail.ydw.entities.embed;

import io.github.realyusufismail.ydw.entities.embed.objects.Image;
import io.github.realyusufismail.ydw.entities.embed.objects.*;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface Embed {
    Optional<String> getTitle();

    Optional<EmbedType> getEmbedType();

    Optional<String> getDescription();

    Optional<String> getUrl();

    Optional<ZonedDateTime> getTimeStamp();

    Optional<Color> getColour();

    Optional<Footer> getFooter();

    Optional<Image> getImage();

    Optional<Thumbnail> getThumbnail();

    Optional<Video> getVideo();

    Optional<Provider> getProvider();

    Optional<Author> getAuthor();

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

        public static EmbedType getEmbedType(String embedType) {
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
