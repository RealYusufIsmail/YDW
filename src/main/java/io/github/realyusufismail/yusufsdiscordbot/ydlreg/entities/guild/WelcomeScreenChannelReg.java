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
import org.jetbrains.annotations.NotNull;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.emoji.Emoji;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Channel;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.WelcomeScreenChannel;

public class WelcomeScreenChannelReg implements WelcomeScreenChannel {
    private final YDL ydl;

    private final Channel channel;
    private final String description;
    private final Emoji emoji;

    public WelcomeScreenChannelReg(@NotNull JsonNode welcome, long guildId, @NotNull YDL ydl) {
        this.ydl = ydl;

        this.channel = ydl.getChannel(welcome.get("channel_id").asLong());
        this.description = welcome.get("description").asText();
        this.emoji = ydl.getRest()
            .getEmojiCaller()
            .getGuildEmoji(guildId, welcome.get("emoji_id").asLong());
    }

    @Override
    public YDL getYDL() {
        return ydl;
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
