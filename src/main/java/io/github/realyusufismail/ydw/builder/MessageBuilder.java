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
            
package io.github.realyusufismail.ydw.builder;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.builder.message.AllowedMention;
import io.github.realyusufismail.ydw.entities.Attachment;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface MessageBuilder {

    @Nullable
    MessageBuilder setTTS(boolean tts);

    @Nullable
    MessageBuilder setContent(String content);

    @Nullable
    MessageBuilder setEmbeds(EmbedBuilder... embedBuilder);

    @Nullable
    MessageBuilder allowedMention(AllowedMention allowedMention);

    @Nullable
    MessageBuilder setFlags(MessageFlags flags);

    @Nullable
    MessageBuilder setAttachments(List<Attachment> attachments);

    ObjectNode toJson();
}
