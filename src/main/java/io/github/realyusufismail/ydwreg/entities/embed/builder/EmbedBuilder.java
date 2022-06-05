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

package io.github.realyusufismail.ydwreg.entities.embed.builder;

import com.google.common.base.Objects;
import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydw.entities.embed.objects.Image;
import io.github.realyusufismail.ydw.entities.embed.objects.*;
import io.github.realyusufismail.ydwreg.entities.embed.EmbedReg;
import io.github.realyusufismail.ydwreg.entities.embed.objects.*;
import io.github.realyusufismail.ydwreg.util.Verify;
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
    @NotNull
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

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setTitle(@NotNull String title) {
        Verify.verify(title.length() <= MAX_TITLE_LENGTH, "Title is too long");
        this.title = title;
        return this;
    }

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setDescription(@NotNull String description) {
        Verify.verify(description.length() <= MAX_DESCRIPTION_LENGTH, "Description is too long");
        this.description = description;
        return this;
    }

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setUrl(@NotNull String url) {
        this.url = url;
        return this;
    }

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setTimestamp(@NotNull String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setColor(@NotNull Color color) {
        this.color = color;
        return this;
    }

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setFooter(@NotNull String text, String iconUrl) {
        Verify.verify(text.length() <= MAX_FOOTER_LENGTH, "Footer text is too long");
        this.footer = new FooterReg(text, iconUrl, null);
        return this;
    }

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setImage(String url) {
        this.image = new ImageReg(url, null, null, null);
        return this;
    }

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setThumbnail(String url) {
        this.thumbnail = new ThumbnailReg(url, null, null, null);
        return this;
    }

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setVideo(String url) {
        this.video = new VideoReg(url, null, null, null);
        return this;
    }

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setProvider(@NotNull String name, @NotNull String url) {
        this.provider = new ProviderReg(name, url);
        return this;
    }

    @NotNull
    @CheckReturnValue
    public EmbedBuilder setAuthor(@NotNull String name, String url, String iconUrl) {
        Verify.verify(name.length() <= MAX_AUTHOR_NAME_LENGTH, "Author name is too long");
        this.author = new AuthorReg(name, url, iconUrl, null);
        return this;
    }

    @NotNull
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
    public EmbedBuilder addField(@NotNull String name, @NotNull String value) {
        return addField(name, value, false);
    }

    @NotNull
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
        return (title == null || title.trim().isEmpty()) && description.length() == 0 && url == null
                && timestamp == null && color == null && footer == null && image == null
                && thumbnail == null && video == null && provider == null && author == null
                && fields.isEmpty();
    }

    public @NotNull Embed build() {
        if (isEmpty())
            throw new IllegalArgumentException("Embed cannot be empty.");
        return new EmbedReg(title, description, Embed.EmbedType.RICH, url, timestamp, color, footer,
                image, thumbnail, video, provider, author, fields);
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

    @NotNull
    @Override
    public String toString() {
        return "EmbedBuilder{" + "title='" + title + '\'' + ", description='" + description + '\''
                + ", url='" + url + '\'' + ", timestamp='" + timestamp + '\'' + ", color=" + color
                + ", footer=" + footer + ", image=" + image + ", thumbnail=" + thumbnail
                + ", video=" + video + ", provider=" + provider + ", author=" + author + ", fields="
                + fields + '}';
    }
}
