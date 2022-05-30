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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.embed.builder;

import com.google.common.base.Objects;
import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.embed.Embed;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.embed.objects.Image;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.embed.objects.*;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.embed.EmbedReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.embed.objects.*;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.util.Verify;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


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

    private String title;
    private String description;
    private String url;
    private String timestamp;
    private Color color;
    private Footer footer;
    private Image image;
    private Thumbnail thumbnail;
    private Video video;
    private Provider provider;
    private Author author;
    private List<Fields> fields = new ArrayList<>();

    public EmbedBuilder() {
        this(null);
    }

    public EmbedBuilder(@Nullable Embed embed) {
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
        this.fields.addAll(embed.getFields());
    }

    @CheckReturnValue
    public EmbedBuilder setTitle(@NotNull String title) {
        Verify.verify(title.length() <= MAX_TITLE_LENGTH, "Title is too long");
        this.title = title;
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder setDescription(@NotNull String description) {
        Verify.verify(description.length() <= MAX_DESCRIPTION_LENGTH, "Description is too long");
        this.description = description;
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder setUrl(@NotNull String url) {
        this.url = url;
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder setTimestamp(@NotNull String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder setColor(@NotNull Color color) {
        this.color = color;
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder setFooter(@NotNull String text, String iconUrl) {
        Verify.verify(text.length() <= MAX_FOOTER_LENGTH, "Footer text is too long");
        this.footer = new FooterReg(text, iconUrl, null);
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder setImage(String url) {
        this.image = new ImageReg(url, null, null, null);
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder setThumbnail(String url) {
        this.thumbnail = new ThumbnailReg(url, null, null, null);
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder setVideo(String url) {
        this.video = new VideoReg(url, null, null, null);
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder setProvider(String name, String url) {
        this.provider = new ProviderReg(name, url);
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder setAuthor(@NotNull String name, String url, String iconUrl) {
        Verify.verify(name.length() <= MAX_AUTHOR_NAME_LENGTH, "Author name is too long");
        this.author = new AuthorReg(name, url, iconUrl, null);
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder addField(@NotNull String name, @NotNull String value, boolean inline) {
        Verify.verify(name.length() <= MAX_FIELD_NAME_LENGTH, "Field name is too long");
        Verify.verify(value.length() <= MAX_FIELD_VALUE_LENGTH, "Field value is too long");
        if (this.fields.size() >= MAX_FIELDS) {
            throw new IllegalArgumentException("Maximum fields per embed is 25.");
        }
        this.fields.add(new FieldsReg(name, value, inline));
        return this;
    }

    @CheckReturnValue
    public EmbedBuilder addField(String name, String value) {
        return addField(name, value, false);
    }

    @CheckReturnValue
    public EmbedBuilder addFields(@NotNull List<Fields> fields) {
        for (Fields field : fields) {
            if (field.getName().isPresent() && field.getValue().isPresent()) {
                addField(field.getName().get(), field.getValue().get());
            } else {
                throw new IllegalArgumentException("Fields must have a name and value.");
            }
        }
        return this;
    }

    public boolean isEmpty() {
        return title == null && description == null && url == null && timestamp == null
                && color == null && footer == null && image == null && thumbnail == null
                && video == null && provider == null && author == null && fields.isEmpty();
    }

    public @NotNull Embed build() {
        if (isEmpty())
            throw new IllegalArgumentException("Embed cannot be empty.");
        return new EmbedReg(title, description, url, timestamp, color, footer, image, thumbnail,
                video, provider, author, fields);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EmbedBuilder))
            return false;
        EmbedBuilder that = (EmbedBuilder) o;
        return Objects.equal(title, that.title) && Objects.equal(description, that.description)
                && Objects.equal(url, that.url) && Objects.equal(timestamp, that.timestamp)
                && Objects.equal(color, that.color) && Objects.equal(footer, that.footer)
                && Objects.equal(image, that.image) && Objects.equal(thumbnail, that.thumbnail)
                && Objects.equal(video, that.video) && Objects.equal(provider, that.provider)
                && Objects.equal(author, that.author) && Objects.equal(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, description, url, timestamp, color, footer, image, thumbnail,
                video, provider, author, fields);
    }

    @Override
    public String toString() {
        return "EmbedBuilder{" + "title='" + title + '\'' + ", description='" + description + '\''
                + ", url='" + url + '\'' + ", timestamp='" + timestamp + '\'' + ", color=" + color
                + ", footer=" + footer + ", image=" + image + ", thumbnail=" + thumbnail
                + ", video=" + video + ", provider=" + provider + ", author=" + author + ", fields="
                + fields + '}';
    }
}
