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
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.emoji.Emoji;
import io.github.realyusufismail.ydw.entities.guild.WelcomeScreenChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WelcomeScreenChannelReg implements WelcomeScreenChannel {
    @NotNull
    private final YDW ydw;

    private final Channel channel;
    private final String description;
    @Nullable
    private final Emoji emoji;

    public WelcomeScreenChannelReg(@NotNull JsonNode welcome, long guildId, @NotNull YDW ydw) {
        this.ydw = ydw;

        this.channel = ydw.getChannel(welcome.get("channel_id").asLong());
        this.description = welcome.get("description").asText();
        this.emoji = ydw.getRest()
            .getEmojiCaller()
            .getGuildEmoji(guildId, welcome.get("emoji_id").asLong());
    }

    @Override
    public YDW getYDW() {
        return ydw;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Emoji getEmoji() {
        return emoji;
    }
}
