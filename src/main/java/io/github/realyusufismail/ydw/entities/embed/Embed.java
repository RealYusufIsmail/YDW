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
