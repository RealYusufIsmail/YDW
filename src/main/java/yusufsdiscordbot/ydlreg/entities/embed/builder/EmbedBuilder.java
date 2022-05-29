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
import yusufsdiscordbot.ydl.entities.guild.Role;
import yusufsdiscordbot.ydlreg.entities.embed.objects.FieldsReg;
import yusufsdiscordbot.ydlreg.util.Verify;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

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
    private Embed.EmbedType embedType;
    private String description;
    private ZonedDateTime timestamp;
    private int color;
    private Footer footer;
    private Image image;
    private Thumbnail thumbnail;
    private Video video;
    private Provider provider;
    private Author author;
    private List<Fields> fields;

    public String getTitle() {
        return title;
    }

    public @NotNull EmbedBuilder setTitle(@NotNull String title) {
        Verify.checkLength(title, MAX_TITLE_LENGTH);
        this.title = title;
        return this;
    }

    public @NotNull EmbedBuilder setEmbedType(@NotNull Embed.EmbedType embedType) {
        this.embedType = embedType;
        return this;
    }

    public @NotNull EmbedBuilder setDescription(@NotNull String description) {
        Verify.checkLength(description, MAX_DESCRIPTION_LENGTH);
        this.description = description;
        return this;
    }

    public @NotNull EmbedBuilder setTimeStamp(@NotNull ZonedDateTime timeStamp) {
        this.timestamp = timeStamp;
        return this;
    }

    public @NotNull EmbedBuilder setColour(@NotNull Color colour) {
        this.color = colour.getRGB();
        return this;
    }

    public @NotNull EmbedBuilder setFooter(@NotNull String text) {
        Verify.checkLength(text, MAX_FOOTER_LENGTH);
        footer.setText(text);
        return this;
    }

    public @NotNull EmbedBuilder setFooter(@NotNull String text, @NotNull String iconUrl) {
        Verify.checkLength(text, MAX_FOOTER_LENGTH);
        footer.setText(text);
        footer.setIconUrl(iconUrl);
        return this;
    }

    public @NotNull EmbedBuilder setFooter(@NotNull String text, @NotNull String iconUrl,
            @NotNull String proxyIconUrl) {
        Verify.checkLength(text, MAX_FOOTER_LENGTH);
        footer.setText(text);
        footer.setIconUrl(iconUrl);
        footer.setProxyIconUrl(proxyIconUrl);
        return this;
    }

    public @NotNull EmbedBuilder setImage(String url) {
        image.setUrl(url);
        return this;
    }

    public @NotNull EmbedBuilder setImage(String url, String proxyUrl) {
        image.setUrl(url);
        image.setProxyUrl(proxyUrl);
        return this;
    }

    public @NotNull EmbedBuilder setImage(String url, String proxyUrl, int height, int width) {
        image.setUrl(url);
        image.setProxyUrl(proxyUrl);
        image.setHeight(height);
        image.setWidth(width);
        return this;
    }

    public @NotNull EmbedBuilder setThumbnail(String url) {
        thumbnail.setUrl(url);
        return this;
    }

    public @NotNull EmbedBuilder setThumbnail(String url, String proxyUrl) {
        thumbnail.setUrl(url);
        thumbnail.setProxyUrl(proxyUrl);
        return this;
    }

    public @NotNull EmbedBuilder setThumbnail(String url, String proxyUrl, int height, int width) {
        thumbnail.setUrl(url);
        thumbnail.setProxyUrl(proxyUrl);
        thumbnail.setHeight(height);
        thumbnail.setWidth(width);
        return this;
    }


    public @NotNull EmbedBuilder setVideo(String url) {
        video.setUrl(url);
        return this;
    }

    public @NotNull EmbedBuilder setVideo(String url, String proxyUrl) {
        video.setUrl(url);
        video.setProxyUrl(proxyUrl);
        return this;
    }

    public @NotNull EmbedBuilder setVideo(String url, String proxyUrl, int height, int width) {
        video.setUrl(url);
        video.setProxyUrl(proxyUrl);
        video.setHeight(height);
        video.setWidth(width);
        return this;
    }

    public @NotNull EmbedBuilder setProvider(String name, String url) {
        provider.setName(name);
        provider.setUrl(url);
        return this;
    }

    public @NotNull EmbedBuilder setAuthor(@NotNull String name) {
        Verify.checkLength(name, MAX_AUTHOR_NAME_LENGTH);
        author.setName(name);
        return this;
    }

    public @NotNull EmbedBuilder setAuthor(@NotNull String name, String url) {
        Verify.checkLength(name, MAX_AUTHOR_NAME_LENGTH);
        author.setName(name);
        return this;
    }

    public @NotNull EmbedBuilder setAuthor(@NotNull String name, String url, String iconUrl) {
        Verify.checkLength(name, MAX_AUTHOR_NAME_LENGTH);
        author.setName(name);
        author.setUrl(url);
        author.setIconUrl(iconUrl);
        return this;
    }

    public @NotNull EmbedBuilder setAuthor(@NotNull String name, String url, String iconUrl,
            String proxyIconUrl) {
        Verify.checkLength(name, MAX_AUTHOR_NAME_LENGTH);
        author.setName(name);
        author.setUrl(url);
        author.setIconUrl(iconUrl);
        author.setProxyIconUrl(proxyIconUrl);
        return this;
    }

    public @NotNull EmbedBuilder setField(@NotNull String name, @NotNull String value,
            boolean inline) {
        Verify.checkIfNull(name, "name");
        Verify.checkIfNull(value, "value");
        Verify.checkLength(name, MAX_FIELD_NAME_LENGTH);
        Verify.checkLength(value, MAX_FIELD_VALUE_LENGTH);
        fields.add(new FieldsReg(name, value, inline));
        return this;
    }

    public @NotNull EmbedBuilder build() {
        if (isEmpty()) {
            throw new IllegalStateException("Unable to build an empty embed.");
        }
        return this;
    }


    public @NotNull String stringify() {
        return "embeds:" + "[{" + "author:" + author + "title" + ":" + title + "," + "description"
                + ":" + description + "," + "type" + ":" + embedType + "," + "timestamp" + ":"
                + timestamp + "," + "color" + ":" + color + "," + "footer" + ":" + footer + ","
                + "image" + ":" + image + "," + "thumbnail" + ":" + thumbnail + "," + "video" + ":"
                + video + "," + "provider" + ":" + provider + "," + "fields" + ":" + fields + "}]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EmbedBuilder that))
            return false;
        return color == that.color && Objects.equals(title, that.title)
                && embedType == that.embedType && Objects.equals(description, that.description)
                && Objects.equals(timestamp, that.timestamp) && Objects.equals(footer, that.footer)
                && Objects.equals(image, that.image) && Objects.equals(thumbnail, that.thumbnail)
                && Objects.equals(video, that.video) && Objects.equals(provider, that.provider)
                && Objects.equals(author, that.author) && Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, embedType, description, timestamp, color, footer, image,
                thumbnail, video, provider, author, fields);
    }

    // checks if the embed is empty
    public boolean isEmpty() {
        return (title == null || title.trim().isEmpty()) && description.length() == 0
                && timestamp == null && color == Role.DEFAULT_COLOR_RAW && footer == null
                && image == null && thumbnail == null && video == null && provider == null
                && author == null && fields.isEmpty();
    }
}
