/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
