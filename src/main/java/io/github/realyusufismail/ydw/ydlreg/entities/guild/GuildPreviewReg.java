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

package io.github.realyusufismail.ydw.ydlreg.entities.guild;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.emoji.Emoji;
import io.github.realyusufismail.ydw.ydl.entities.guild.GuildPreview;
import io.github.realyusufismail.ydw.ydlreg.entities.sticker.StickerReg;
import io.github.realyusufismail.ydw.ydl.entities.sticker.Sticker;
import io.github.realyusufismail.ydw.ydlreg.entities.emoji.EmojiReg;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class GuildPreviewReg implements GuildPreview {
    private final YDL ydl;
    private final long id;

    private final String name;
    private final String icon;
    private final String splash;
    private final String discoverySplash;
    private final List<Emoji> emojis = new ArrayList<>();
    private final List<Sticker> stickers = new ArrayList<>();
    private final EnumSet<GuildFeatures> features = EnumSet.noneOf(GuildFeatures.class);
    @NotNull
    private final Integer approximateMemberCount;
    @NotNull
    private final Integer approximatePresenceCount;
    private final String description;

    public GuildPreviewReg(@NotNull JsonNode preview, long id, @NotNull YDL ydl) {
        this.ydl = ydl;
        this.id = id;

        this.name = preview.get("name").asText();
        this.icon = preview.get("icon").asText();
        this.splash = preview.get("splash").asText();
        this.discoverySplash = preview.get("discovery_splash").asText();
        this.approximateMemberCount = preview.get("approximate_member_count").asInt();
        this.approximatePresenceCount = preview.get("approximate_presence_count").asInt();
        this.description = preview.get("description").asText();


        if (preview.has("features")) {
            for (JsonNode feature : preview.get("features")) {
                features.add(GuildFeatures.valueOf(feature.asText()));
            }
        }

        if (preview.has("emojis")) {
            for (JsonNode emoji : preview.get("emojis")) {
                emojis.add(new EmojiReg(emoji, emoji.get("id").asLong(), ydl));
            }
        }

        if (preview.has("stickers")) {
            for (JsonNode sticker : preview.get("stickers")) {
                stickers.add(new StickerReg(sticker, sticker.get("id").asLong(), ydl));
            }
        }
    }

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
    public String getName() {
        return name;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getSplash() {
        return splash;
    }

    @Override
    public String getDiscoverySplash() {
        return discoverySplash;
    }

    @NotNull
    @Override
    public List<Emoji> getEmojis() {
        return emojis;
    }

    @NotNull
    @Override
    public EnumSet<GuildFeatures> getFeatures() {
        return features;
    }

    @Override
    public Integer getApproximateMemberCount() {
        return approximateMemberCount;
    }

    @Override
    public Integer getApproximatePresenceCount() {
        return approximatePresenceCount;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @NotNull
    @Override
    public List<Sticker> getStickers() {
        return stickers;
    }
}
