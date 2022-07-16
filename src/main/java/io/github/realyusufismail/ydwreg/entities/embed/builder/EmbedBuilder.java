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
package io.github.realyusufismail.ydwreg.entities.embed.builder;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Objects;
import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydw.entities.embed.objects.Image;
import io.github.realyusufismail.ydw.entities.embed.objects.*;
import io.github.realyusufismail.ydwreg.entities.embed.objects.*;
import io.github.realyusufismail.ydwreg.util.ColourUtil;
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
    @NotNull
    private final List<Fields> fields = new ArrayList<>();
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

    public EmbedBuilder() {
        this(null);
    }

    public EmbedBuilder(@Nullable Embed embed) {
        if (embed != null) {
            if (embed.getTitle().isPresent())
                this.title = embed.getTitle().get();
            if (embed.getDescription().isPresent())
                this.description = embed.getDescription().get();
            if (embed.getUrl().isPresent())
                this.url = embed.getUrl().get();
            if (embed.getTimeStamp().isPresent())
                this.timestamp = embed.getTimeStamp().get().toString();
            if (embed.getColour().isPresent())
                this.color = embed.getColour().get();
            if (embed.getFooter().isPresent())
                this.footer = embed.getFooter().get();
            if (embed.getImage().isPresent())
                this.image = embed.getImage().get();
            if (embed.getThumbnail().isPresent())
                this.thumbnail = embed.getThumbnail().get();
            if (embed.getVideo().isPresent())
                this.video = embed.getVideo().get();
            if (embed.getProvider().isPresent())
                this.provider = embed.getProvider().get();
            if (embed.getAuthor().isPresent())
                this.author = embed.getAuthor().get();
            if (!embed.getFields().isEmpty()) {
                this.fields.addAll(embed.getFields());
            }
        }
    }

    @NotNull
    public EmbedBuilder setTitle(@NotNull String title) {
        Verify.verify(title.length() <= MAX_TITLE_LENGTH, "Title is too long");
        this.title = title;
        return this;
    }

    @NotNull
    public EmbedBuilder setDescription(@NotNull String description) {
        Verify.verify(description.length() <= MAX_DESCRIPTION_LENGTH, "Description is too long");
        this.description = description;
        return this;
    }

    @NotNull
    public EmbedBuilder setUrl(@NotNull String url) {
        this.url = url;
        return this;
    }

    @NotNull
    public EmbedBuilder setTimestamp(@NotNull String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @NotNull
    public EmbedBuilder setColor(@NotNull Color color) {
        this.color = color;
        return this;
    }

    @NotNull
    public EmbedBuilder setFooter(@NotNull String text, String iconUrl) {
        Verify.verify(text.length() <= MAX_FOOTER_LENGTH, "Footer text is too long");
        this.footer = new FooterReg(text, iconUrl, null);
        return this;
    }

    @NotNull
    public EmbedBuilder setImage(String url) {
        this.image = new ImageReg(url, null, null, null);
        return this;
    }

    @NotNull
    public EmbedBuilder setThumbnail(String url) {
        this.thumbnail = new ThumbnailReg(url, null, null, null);
        return this;
    }

    @NotNull
    public EmbedBuilder setVideo(String url) {
        this.video = new VideoReg(url, null, null, null);
        return this;
    }

    @NotNull
    public EmbedBuilder setProvider(@NotNull String name, @NotNull String url) {
        this.provider = new ProviderReg(name, url);
        return this;
    }

    @NotNull
    public EmbedBuilder setAuthor(@NotNull String name, String url, String iconUrl) {
        Verify.verify(name.length() <= MAX_AUTHOR_NAME_LENGTH, "Author name is too long");
        this.author = new AuthorReg(name, url, iconUrl, null);
        return this;
    }

    @NotNull
    public EmbedBuilder addField(@NotNull String name, @NotNull String value, boolean inline) {
        Verify.verify(name.length() <= MAX_FIELD_NAME_LENGTH, "Field name is too long");
        Verify.verify(value.length() <= MAX_FIELD_VALUE_LENGTH, "Field value is too long");
        if (this.fields.size() >= MAX_FIELDS) {
            throw new IllegalArgumentException("Maximum fields per embed is 25.");
        }
        this.fields.add(new FieldsReg(name, value, inline));
        return this;
    }

    public EmbedBuilder addField(@NotNull String name, @NotNull String value) {
        return addField(name, value, false);
    }

    @NotNull
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

    public @NotNull EmbedBuilder build() {
        return this;
    }

    public ObjectNode toJson() {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        if (title != null)
            json.put("title", title);


        if (description != null)
            json.put("description", description);


        if (url != null)
            json.put("url", url);


        if (timestamp != null)
            json.put("timestamp", timestamp);


        if (color != null) {
            json.put("color", ColourUtil.convertRGB(color));
        }

        if (footer != null)
            json.set("footer", footer.toJson());

        if (image != null)
            json.set("image", image.toJson());

        if (thumbnail != null)
            json.set("thumbnail", thumbnail.toJson());

        if (video != null)
            json.set("video", video.toJson());

        if (provider != null)
            json.set("provider", provider.toJson());

        if (author != null)
            json.set("author", author.toJson());

        if (!fields.isEmpty()) {
            ArrayNode fieldsJson = json.putArray("fields");
            for (Fields field : fields) {
                fieldsJson.add(field.toJson());
            }
        }
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EmbedBuilder that))
            return false;
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
