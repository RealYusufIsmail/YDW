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
package io.github.realyusufismail.ydwreg.builder;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.builder.MessageBuilder;
import io.github.realyusufismail.ydw.builder.message.AllowedMention;
import io.github.realyusufismail.ydw.entities.Attachment;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MessageBuilderReg implements MessageBuilder {

    private boolean tts;
    private String content;
    private EmbedBuilder embedBuilder;
    private AllowedMention allowedMention;
    private MessageFlags flags;
    private List<Attachment> attachments;

    @Nullable
    @Override
    public MessageBuilder setTTS(boolean tts) {
        this.tts = tts;
        return this;
    }

    @Nullable
    @Override
    public MessageBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    @Nullable
    @Override
    public MessageBuilder setEmbeds(EmbedBuilder... embedBuilder) {
        for (EmbedBuilder eb : embedBuilder) {
            this.embedBuilder = eb;
        }
        return this;
    }

    @Nullable
    @Override
    public MessageBuilder allowedMention(AllowedMention allowedMention) {
        this.allowedMention = allowedMention;
        return this;
    }

    @Nullable
    @Override
    public MessageBuilder setFlags(MessageFlags flags) {
        this.flags = flags;
        return this;
    }

    @Nullable
    @Override
    public MessageBuilder setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    @Override
    public ObjectNode toJson() {
        var json = JsonNodeFactory.instance.objectNode();

        if (tts)
            json.put("tts", true);

        if (content != null)
            json.put("content", content);

        if (embedBuilder != null)
            json.set("embeds", JsonNodeFactory.instance.arrayNode().add(embedBuilder.toJson()));

        if (allowedMention != null)
            json.set("allowed_mentions", allowedMention.toJson());

        if (flags != null)
            json.put("flags", flags.getValue());

        if (attachments != null && !attachments.isEmpty()) {
            var attachmentsJson = JsonNodeFactory.instance.arrayNode();
            for (Attachment attachment : attachments) {
                attachmentsJson.add(attachment.toJson());
            }
            json.set("attachments", attachmentsJson);
        }


        return json;
    }
}
