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
package io.github.realyusufismail.ydw.application.commands.reply;

import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.ydw.action.ReplyAction;
import io.github.realyusufismail.ydw.action.config.ReplyConfig;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import org.jetbrains.annotations.Nullable;

public interface IReply {

    @CheckReturnValue
    default ReplyAction reply(String message) {
        return reply(message, null);
    }

    @CheckReturnValue
    ReplyAction reply(String message, @Nullable ReplyConfig config);

    @CheckReturnValue
    default ReplyAction replyEmbed(EmbedBuilder embed) {
        return replyEmbed(embed, null);
    }

    @CheckReturnValue
    ReplyAction replyEmbed(EmbedBuilder embed, @Nullable ReplyConfig config);
}
