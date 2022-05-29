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

package yusufsdiscordbot.ydlreg.entities.embed.builder;

import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.entities.embed.Embed;
import yusufsdiscordbot.ydl.entities.embed.objects.*;
import yusufsdiscordbot.ydl.entities.embed.objects.Image;
import yusufsdiscordbot.ydlreg.entities.embed.EmbedReg;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


// TODO: requires full rewrite.
public class EmbedBuilder {
    /**
     * Tile max length is 256 characters.
     */
    private static final int MAX_TITLE_LENGTH = 256;
    /**
     * Description max length is 2048 characters.
     */
    private static final int MAX_DESCRIPTION_LENGTH = 4096;
    /**
     * Footer max length is 2048 characters.
     */
    private static final int MAX_FOOTER_LENGTH = 2048;
    /**
     * Field name max length is 256 characters.
     */
    private static final int MAX_FIELD_NAME_LENGTH = 256;
    /**
     * Field value max length is 1024 characters.
     */
    private static final int MAX_FIELD_VALUE_LENGTH = 1024;
    /**
     * Max fields per embed is 25.
     */
    private static final int MAX_FIELDS = 25;
    /**
     * Max length for author name is 256 characters.
     */
    private static final int MAX_AUTHOR_NAME_LENGTH = 256;

    private final String title;
    private final String description;
    private final String url;
    private final String timestamp;
    private final Color color;
    private final Footer footer;
    private final Image image;
    private final Thumbnail thumbnail;
    private final Video video;
    private final Provider provider;
    private final Author author;
    private final List<Fields> fields = new ArrayList<>();

    public EmbedBuilder() {
        this(null);
    }

    public EmbedBuilder(Embed embed) {
        this.title = embed.getTitle().get();
        this.description = embed.getDescription().get();
        this.url = embed.getUrl().get();
        this.timestamp = embed.getTimeStamp().get().toString();
        this.color = embed.getColour().get();
        this.footer = embed.getFooter().get();
        this.image = embed.getImage().get();
        this.thumbnail = embed.getThumbnail().get();
        this.video = embed.getVideo().get();
        this.provider = embed.getProvider().get();
        this.author = embed.getAuthor().get();
        for(Fields field : embed.getFields().get()) {
            this.fields.add(field);
        }
    }

    public EmbedBuilder setTitle(@NotNull String title) {
        this.title = title;
        return this;
    }

    public EmbedBuilder setDescription(@NotNull String description) {
        this.description = description;
        return this;
    }

    public @NotNull Embed build() {
        // TODO: implement
        return new EmbedReg(title, description, url,  timestamp, color, );
    }



}
