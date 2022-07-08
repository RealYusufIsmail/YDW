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

package io.github.realyusufismail.ydwreg.entities.embed;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.entities.embed.Embed;
import io.github.realyusufismail.ydw.entities.embed.objects.Image;
import io.github.realyusufismail.ydw.entities.embed.objects.*;
import io.github.realyusufismail.ydwreg.entities.embed.objects.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmbedReg implements Embed {

    private final String title;
    private final EmbedType type;
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

    @Override
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    @Override
    public Optional<EmbedType> getEmbedType() {
        return Optional.ofNullable(type);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @Override
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    @Override
    public Optional<ZonedDateTime> getTimeStamp() {
        return Optional.ofNullable(ZonedDateTime.parse(timestamp));
    }

    @Override
    public Optional<Color> getColour() {
        return Optional.ofNullable(color);
    }

    @Override
    public Optional<Footer> getFooter() {
        return Optional.ofNullable(footer);
    }

    @Override
    public Optional<Image> getImage() {
        return Optional.ofNullable(image);
    }

    @Override
    public Optional<Thumbnail> getThumbnail() {
        return Optional.ofNullable(thumbnail);
    }

    @Override
    public Optional<Video> getVideo() {
        return Optional.ofNullable(video);
    }

    @Override
    public Optional<Provider> getProvider() {
        return Optional.ofNullable(provider);
    }

    @Override
    public Optional<Author> getAuthor() {
        return Optional.ofNullable(author);
    }

    @Override
    public List<Fields> getFields() {
        return fields;
    }
}
