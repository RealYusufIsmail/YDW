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
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.action.Action;
import io.github.realyusufismail.yusufsdiscordbot.ydl.action.MessageAction;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Message;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.channel.TextChannel;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.YDLReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.guild.ChannelReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.rest.callers.MessageCaller;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO : once rest api is done start creating the rest api for this
public class TextChannelReg extends ChannelReg implements TextChannel {
    private final YDL ydl;
    @NotNull
    MessageCaller apiHandler = getYDL().getRest().getMessageRestApi();

    public TextChannelReg(@NotNull JsonNode json, long id, @NotNull YDL ydl) {
        super(json, id, ydl);
        this.ydl = ydl;
    }

    @Override
    public @NotNull MessageAction sendMessage(String message) {
        return null;
    }

    @Override
    public @NotNull MessageAction sendEmbedMessage(@NotNull EmbedBuilder embedBuilder) {
        // apiHandler.sendMessage(getId(), embedBuilder.build());
        // return new MessageActionReg();
        return null;
    }


    @Override
    public @NotNull Message getMessage(@NotNull String messageId) {
        // return apiHandler.getMessage(getId(), messageId);
        return null;
    }

    @Override
    public @NotNull Message getMessage(long messageId) {
        // return apiHandler.getMessage(getId(), messageId);
        return null;
    }

    @Override
    public @Nullable Action deleteMessage(@NotNull String messageId) {
        return null;
    }

    @Override
    public @Nullable Action deleteMessage(long messageId) {
        return null;
    }


    @Override
    public @Nullable Action deleteMessages(int min, int max) {
        return null;
    }

    @Nullable
    @Override
    public YDLReg getYDL() {
        return (YDLReg) ydl;
    }
}
