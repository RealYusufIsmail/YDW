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
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.emoji.Emoji;
import io.github.realyusufismail.ydw.entities.guild.GuildPreview;
import io.github.realyusufismail.ydw.entities.sticker.Sticker;
import io.github.realyusufismail.ydwreg.entities.emoji.EmojiReg;
import io.github.realyusufismail.ydwreg.entities.sticker.StickerReg;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class GuildPreviewReg implements GuildPreview {
    private final YDW ydw;
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

    public GuildPreviewReg(@NotNull JsonNode preview, long id, @NotNull YDW ydw) {
        this.ydw = ydw;
        this.id = id;

        this.name = preview.get("name").asText();
        this.icon = preview.get("icon").asText();
        this.splash = preview.get("splash").asText();
        this.discoverySplash = preview.get("discovery_splash").asText();
        this.approximateMemberCount = preview.get("approximate_member_count").asInt();
        this.approximatePresenceCount = preview.get("approximate_presence_count").asInt();
        this.description = preview.get("description").asText();


        if (preview.hasNonNull("features")) {
            for (JsonNode feature : preview.get("features")) {
                features.add(GuildFeatures.valueOf(feature.asText()));
            }
        }

        if (preview.hasNonNull("emojis")) {
            for (JsonNode emoji : preview.get("emojis")) {
                emojis.add(new EmojiReg(emoji, emoji.get("id").asLong(), ydw));
            }
        }

        if (preview.hasNonNull("stickers")) {
            for (JsonNode sticker : preview.get("stickers")) {
                stickers.add(new StickerReg(sticker, sticker.get("id").asLong(), ydw));
            }
        }
    }

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
