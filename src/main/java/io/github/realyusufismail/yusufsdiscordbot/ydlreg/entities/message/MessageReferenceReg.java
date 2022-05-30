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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.message;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.message.MessageReference;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.snowflake.SnowFlake;

public class MessageReferenceReg implements MessageReference {
    private final JsonNode message;
    private final YDL ydl;

    public MessageReferenceReg(JsonNode message, YDL ydl) {
        this.message = message;
        this.ydl = ydl;
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
    public SnowFlake getMessageId() {
        return SnowFlake.of(message.get("id").asLong());
    }

    @Override
    public SnowFlake getChannelId() {
        return SnowFlake.of(message.get("channel_id").asLong());
    }

    @Override
    public SnowFlake getGuildId() {
        return SnowFlake.of(message.get("guild_id").asLong());
    }

    /**
     * when sending, whether to error if the referenced message doesn't exist instead of sending as
     * a normal (non-reply) message, default true
     *
     * @return if found false, if not found true
     */
    @Override
    public boolean failIfNotFound() {
        return message.get("fail_if_not_exists").asBoolean();
    }
}
