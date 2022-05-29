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

package yusufsdiscordbot.ydl.entities.embed;

import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.entities.embed.objects.*;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public interface Embed {
    @NotNull
    String getTitle();

    @NotNull
    EmbedType getEmbedType();

    @NotNull
    String getDescription();

    @NotNull
    String getUrl();

    @NotNull
    ZonedDateTime getTimeStamp();

    @NotNull
    Color getColour();

    @NotNull
    Footer getFooter();

    @NotNull
    Image getImage();

    @NotNull
    Thumbnail getThumbnail();

    @NotNull
    Video getVideo();

    @NotNull
    Provider getProvider();

    @NotNull
    Author getAuthor();

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
        GIF("gifv"),
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

    class Field {
        private final String name;
        private final String value;
        private final boolean inline;

        public Field(String name, String value, boolean inline) {
            this.name = name;
            this.value = value;
            this.inline = inline;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public boolean isInline() {
            return inline;
        }
    }
}
