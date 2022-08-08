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
package io.github.realyusufismail.ydwreg.entities.guild.channel;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.entities.guild.channel.GeneralTextChannel;
import io.github.realyusufismail.ydwreg.action.MessageActionReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GeneralTextChannelReg implements GeneralTextChannel {
    private final YDW ydw;
    private final long id;

    public GeneralTextChannelReg(YDW ydw, long id) {
        this.ydw = ydw;
        this.id = id;
    }

    @Override
    public MessageActionReg sendMessage(String message) {
        var req = ydw.getRest().getChannelCaller().sendMessage(this.id, message);
        return new MessageActionReg(req, ydw);
    }

    @Override
    public MessageActionReg sendEmbedMessage(EmbedBuilder embedBuilder) {
        var req = ydw.getRest().getChannelCaller().sendEmbedMessage(this.id, embedBuilder);
        return new MessageActionReg(req, ydw);
    }

    @NotNull
    @Override
    public Message getMessage(long messageId) {
        return ydw.getRest().getChannelCaller().getMessage(this.id, messageId);
    }

    @Override
    public List<Message> getMessages(int limit) {
        return ydw.getRest().getChannelCaller().getMessages(this.id, limit);
    }

    @NotNull
    @Override
    public MessageActionReg deleteMessage(long messageId) {
        var req = ydw.getRest().getChannelCaller().deleteMessage(this.id, messageId);
        return new MessageActionReg(req, ydw);
    }

    @NotNull
    @Override
    public MessageActionReg deleteMessages(int amount) {
        var req = ydw.getRest().getChannelCaller().deleteMessages(this.id, amount);
        return new MessageActionReg(req, ydw);
    }
}
