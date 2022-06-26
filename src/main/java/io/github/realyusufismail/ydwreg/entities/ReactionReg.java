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

package io.github.realyusufismail.ydwreg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Reaction;
import io.github.realyusufismail.ydw.entities.emoji.Emoji;
import io.github.realyusufismail.ydwreg.entities.emoji.EmojiReg;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReactionReg implements Reaction {
    private final YDW ydw;

    @NotNull
    private final Integer count;
    @NotNull
    private final Boolean me;
    @NotNull
    private final Emoji emoji;

    public ReactionReg(@NotNull JsonNode reaction, @NotNull YDW ydw) {
        this.ydw = ydw;

        this.count = reaction.get("count").asInt();
        this.me = reaction.get("me").asBoolean();
        this.emoji =
                new EmojiReg(reaction.get("emoji"), reaction.get("emoji").get("id").asLong(), ydw);
    }

    /**
     * Called when the bot is ready.
     *
     * @return The ydw instance.
     */
    @Override
    public @Nullable YDW getYDW() {
        return ydw;
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
