/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

        this.channel = ydw.getChannel(Channel.class, welcome.get("channel_id").asLong());
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
