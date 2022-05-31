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

package io.github.realyusufismail.ydw.entities.guild.channel;

import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.ydw.action.Action;
import io.github.realyusufismail.ydw.action.MessageAction;
import io.github.realyusufismail.ydw.entities.GenericEntity;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TextChannel extends SnowFlake, GenericEntity, BaseChannel {
    @CheckReturnValue
    @NotNull
    MessageAction sendMessage(String message);

    @CheckReturnValue
    @NotNull
    MessageAction sendEmbedMessage(EmbedBuilder embedBuilder);

    @NotNull
    Message getMessage(@NotNull String messageId);

    @NotNull
    Message getMessage(long messageId);

    @Nullable
    Action deleteMessage(@NotNull String messageId);

    @Nullable
    Action deleteMessage(long messageId);

    /**
     * @param min The minimum amount of messages to be deleted.
     * @param max The maximum amount of messages to be deleted.
     * @return The amount of messages deleted.
     * @throws IllegalArgumentException If the max amount is less than the min amount.
     * @throws IllegalArgumentException If the max amount is less than 1.
     * @throws IllegalArgumentException If the min amount is less than 2.
     * @throws IllegalArgumentException If the max amount is greater than 200.
     */
    @Nullable
    Action deleteMessages(int min, int max);
}
