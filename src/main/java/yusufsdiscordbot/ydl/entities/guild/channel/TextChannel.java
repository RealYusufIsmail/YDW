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

package yusufsdiscordbot.ydl.entities.guild.channel;

import api.ydl.CheckReturnValue;
import api.ydl.snowflake.SnowFlake;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yusufsdiscordbot.ydl.entities.GenericEntity;
import yusufsdiscordbot.ydl.entities.guild.Message;
import yusufsdiscordbot.ydlreg.action.Action;
import yusufsdiscordbot.ydlreg.action.MessageAction;
import yusufsdiscordbot.ydlreg.entities.embed.builder.EmbedBuilder;

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
