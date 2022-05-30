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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.emoji.Emoji;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.channel.Reaction;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.emoji.EmojiReg;

public class ReactionReg implements Reaction {
    private final YDL ydl;

    private final Integer count;
    private final Boolean me;
    private final Emoji emoji;

    public ReactionReg(JsonNode reaction, YDL ydl) {
        this.ydl = ydl;

        this.count = reaction.get("count").asInt();
        this.me = reaction.get("me").asBoolean();
        this.emoji =
                new EmojiReg(reaction.get("emoji"), reaction.get("emoji").get("id").asLong(), ydl);
    }

    /**
     * Called when the bot is ready.
     *
     * @return The YDL instance.
     */
    @Override
    public @Nullable YDL getYDL() {
        return ydl;
    }


    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public Boolean isMe() {
        return me;
    }

    @Override
    public Emoji getEmoji() {
        return emoji;
    }
}
