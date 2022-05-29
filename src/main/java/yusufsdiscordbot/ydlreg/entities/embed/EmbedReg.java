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

package yusufsdiscordbot.ydlreg.entities.embed;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import yusufsdiscordbot.ydl.entities.embed.Embed;
import yusufsdiscordbot.ydl.entities.embed.objects.*;
import yusufsdiscordbot.ydlreg.entities.embed.objects.*;
import yusufsdiscordbot.ydlreg.json.JsonUtils;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmbedReg implements Embed {
    private final JsonNode embed;

    public EmbedReg(JsonNode embed) {
        this.embed = embed;
    }

    @Override
    public @NotNull String getTitle() {
        return embed.get("title").asText();
    }

    @Override
    public @NotNull EmbedType getEmbedType() {
        return EmbedType.getEmbedType(embed.get("type").asText());
    }

    @Override
    public @NotNull String getDescription() {
        return embed.get("description").asText();
    }

    @Override
    public @NotNull String getUrl() {
        return embed.get("url").asText();
    }

    @Override
    public @NotNull ZonedDateTime getTimeStamp() {
        return ZonedDateTime.parse(embed.get("timestamp").asText());
    }

    @Override
    public @NotNull Color getColour() {
        return new Color(embed.get("color").asInt());
    }

    @Override
    public @NotNull Footer getFooter() {
        return new FooterReg(embed.getAsJsonNode("footer"));
    }

    @Override
    public @NotNull Image getImage() {
        return new ImageReg(embed.getAsJsonNode("image"));
    }


    @Override
    public @NotNull Thumbnail getThumbnail() {
        return new ThumbnailReg(embed.getAsJsonNode("thumbnail"));
    }

    @Override
    public @NotNull Video getVideo() {
        return new VideoReg(embed.getAsJsonNode("video"));
    }

    @Override
    public @NotNull Provider getProvider() {
        return new ProviderReg(embed.getAsJsonNode("provider"));
    }

    @Override
    public @NotNull Author getAuthor() {
        return new AuthorReg(embed.getAsJsonNode("author"));
    }

    @Override
    public @NotNull List<Fields> getFields() {
        return new ArrayList<>(JsonUtils.stream(embed.get("fields").getAsArrayNode())
            .map(field -> new FieldsReg(field.getAsJsonNode()))
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
    }
}
