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

package io.github.realyusufismail.ydwreg.entities.message;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.message.MessageReference;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MessageReferenceReg implements MessageReference {
    private final JsonNode message;
    private final YDW ydw;

    public MessageReferenceReg(JsonNode message, YDW ydw) {
        this.message = message;
        this.ydw = ydw;
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

    @NotNull
    @Override
    public SnowFlake getMessageId() {
        return SnowFlake.of(message.get("id").asLong());
    }

    @NotNull
    @Override
    public SnowFlake getChannelId() {
        return SnowFlake.of(message.get("channel_id").asLong());
    }

    @NotNull
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
