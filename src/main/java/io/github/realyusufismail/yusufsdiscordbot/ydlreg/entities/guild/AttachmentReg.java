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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.Attachment;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class AttachmentReg implements Attachment {
    private final YDL ydl;
    private final long id;

    private final String fileName;
    private final String description;
    private final String cotentType;
    @NotNull
    private final Integer size;
    private final String url;
    private final String proxyUrl;
    private final Integer height;
    private final Integer width;
    private final Boolean emphemeral;


    public AttachmentReg(@NotNull JsonNode attachment, long id, YDL ydl) {
        this.ydl = ydl;
        this.id = id;

        this.fileName = attachment.get("filename").asText();
        this.description =
                attachment.hasNonNull("description") ? attachment.get("description").asText()
                        : null;
        this.cotentType =
                attachment.hasNonNull("content_type") ? attachment.get("content_type").asText()
                        : null;
        this.size = attachment.get("size").asInt();
        this.url = attachment.get("url").asText();
        this.proxyUrl = attachment.get("proxy_url").asText();
        this.height = attachment.hasNonNull("height") ? attachment.get("height").asInt() : null;
        this.width = attachment.hasNonNull("width") ? attachment.get("width").asInt() : null;
        this.emphemeral =
                attachment.hasNonNull("emphemeral") ? attachment.get("emphemeral").asBoolean()
                        : null;
    }


    /**
     * @return The core long of this api.
     */
    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public YDL getYDL() {
        return ydl;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @NotNull
    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @NotNull
    @Override
    public Optional<String> getContentType() {
        return Optional.ofNullable(cotentType);
    }

    @Override
    public Long getSize() {
        return size.longValue();
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public String getProxyURL() {
        return proxyUrl;
    }

    @NotNull
    @Override
    public Optional<Integer> getHeight() {
        return Optional.ofNullable(height);
    }

    @NotNull
    @Override
    public Optional<Integer> getWidth() {
        return Optional.ofNullable(width);
    }

    @NotNull
    @Override
    public Optional<Boolean> isEphemeral() {
        return Optional.ofNullable(emphemeral);
    }
}
