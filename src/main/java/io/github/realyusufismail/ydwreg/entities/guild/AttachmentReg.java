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

package io.github.realyusufismail.ydwreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Attachment;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class AttachmentReg implements Attachment {
    private final YDW ydw;
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


    public AttachmentReg(@NotNull JsonNode attachment, long id, YDW ydw) {
        this.ydw = ydw;
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
                attachment.hasNonNull("ephemeral") ? attachment.get("ephemeral").asBoolean() : null;
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
    public YDW getYDW() {
        return ydw;
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

    @Override
    public ObjectNode toJson() {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        json.put("id", id);
        json.put("filename", fileName);

        if (description != null)
            json.put("description", description);

        if (cotentType != null)
            json.put("content_type", cotentType);

        json.put("size", size);
        json.put("url", url);
        json.put("proxy_url", proxyUrl);

        if (height != null)
            json.put("height", height);

        if (width != null)
            json.put("width", width);

        if (emphemeral != null)
            json.put("ephemeral", emphemeral);

        return json;
    }
}
