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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.embed;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.embed.Embed;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.embed.objects.Image;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.embed.objects.*;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.embed.objects.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmbedReg implements Embed {

    private final String title;
    @Nullable
    private final EmbedType type;
    private final String description;
    private final String url;
    private final String timestamp;
    @Nullable
    private final Color color;
    @Nullable
    private final Footer footer;
    @Nullable
    private final Image image;
    @Nullable
    private final Thumbnail thumbnail;
    @Nullable
    private final Video video;
    @Nullable
    private final Provider provider;
    @Nullable
    private final Author author;
    private final List<Fields> fields = new ArrayList<>();

    public EmbedReg(@NotNull JsonNode embed) {
        this.title = embed.hasNonNull("title") ? embed.get("title").asText() : null;
        this.type = embed.hasNonNull("type") ? EmbedType.valueOf(embed.get("type").asText()) : null;
        this.description =
                embed.hasNonNull("description") ? embed.get("description").asText() : null;
        this.url = embed.hasNonNull("url") ? embed.get("url").asText() : null;
        this.timestamp = embed.hasNonNull("timestamp") ? embed.get("timestamp").asText() : null;
        this.color = embed.hasNonNull("color") ? new Color(embed.get("color").asInt()) : null;
        this.footer = embed.hasNonNull("footer") ? new FooterReg(embed.get("footer")) : null;
        this.image = embed.hasNonNull("image") ? new ImageReg(embed.get("image")) : null;
        this.thumbnail =
                embed.hasNonNull("thumbnail") ? new ThumbnailReg(embed.get("thumbnail")) : null;
        this.video = embed.hasNonNull("video") ? new VideoReg(embed.get("video")) : null;
        this.provider =
                embed.hasNonNull("provider") ? new ProviderReg(embed.get("provider")) : null;
        this.author = embed.hasNonNull("author") ? new AuthorReg(embed.get("author")) : null;

        if (embed.hasNonNull("fields")) {
            for (JsonNode field : embed.get("fields")) {
                fields.add(new FieldsReg(field));
            }
        }
    }

    public EmbedReg(String title, String description, String url, String timestamp, Color color,
            Footer footer, Image image, Thumbnail thumbnail, Video video, Provider provider,
            Author author, @NotNull List<Fields> fields) {
        this.title = title;
        this.type = null;
        this.description = description;
        this.url = url;
        this.timestamp = timestamp;
        this.color = color;
        this.footer = footer;
        this.image = image;
        this.thumbnail = thumbnail;
        this.video = video;
        this.provider = provider;
        this.author = author;
        this.fields.addAll(fields);
    }


    @NotNull
    @Override
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    @NotNull
    @Override
    public Optional<EmbedType> getEmbedType() {
        return Optional.ofNullable(type);
    }

    @NotNull
    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @NotNull
    @Override
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    @NotNull
    @Override
    public Optional<ZonedDateTime> getTimeStamp() {
        return Optional.ofNullable(ZonedDateTime.parse(timestamp));
    }

    @NotNull
    @Override
    public Optional<Color> getColour() {
        return Optional.ofNullable(color);
    }

    @NotNull
    @Override
    public Optional<Footer> getFooter() {
        return Optional.ofNullable(footer);
    }

    @NotNull
    @Override
    public Optional<Image> getImage() {
        return Optional.ofNullable(image);
    }

    @NotNull
    @Override
    public Optional<Thumbnail> getThumbnail() {
        return Optional.ofNullable(thumbnail);
    }

    @NotNull
    @Override
    public Optional<Video> getVideo() {
        return Optional.ofNullable(video);
    }

    @NotNull
    @Override
    public Optional<Provider> getProvider() {
        return Optional.ofNullable(provider);
    }

    @NotNull
    @Override
    public Optional<Author> getAuthor() {
        return Optional.ofNullable(author);
    }

    @NotNull
    @Override
    public List<Fields> getFields() {
        return fields;
    }
}
