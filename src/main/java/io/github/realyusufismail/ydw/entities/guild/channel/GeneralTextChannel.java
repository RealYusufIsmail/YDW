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
package io.github.realyusufismail.ydw.entities.guild.channel;

import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydwreg.action.MessageActionReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// TODO: add methods that are common to text and thread channels
public interface GeneralTextChannel {
    MessageActionReg sendMessage(String message);

    MessageActionReg sendEmbedMessage(EmbedBuilder embedBuilder);

    @NotNull
    Message getMessage(long messageId);

    @NotNull
    default Message getMessage(@NotNull String messageId) {
        return getMessage(Long.parseUnsignedLong(messageId));
    }

    List<Message> getMessages(int limit);

    @NotNull
    default MessageActionReg deleteMessage(@NotNull String messageId) {
        return deleteMessage(Long.parseUnsignedLong(messageId));
    }

    @NotNull
    MessageActionReg deleteMessage(long messageId);

    @NotNull
    MessageActionReg deleteMessages(int amount);
}
