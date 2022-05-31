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

package io.github.realyusufismail.ydwreg.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.action.Action;
import io.github.realyusufismail.ydw.action.MessageAction;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.channel.TextChannel;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.entities.guild.ChannelReg;
import io.github.realyusufismail.ydwreg.rest.callers.MessageCaller;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO : once rest api is done start creating the rest api for this
public class TextChannelReg extends ChannelReg implements TextChannel {
    private final YDW ydw;
    @NotNull
    MessageCaller apiHandler = getYDW().getRest().getMessageRestApi();

    public TextChannelReg(@NotNull JsonNode json, long id, @NotNull YDW ydw) {
        super(json, id, ydw);
        this.ydw = ydw;
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
    public YDWReg getYDW() {
        return (YDWReg) ydw;
    }
}
