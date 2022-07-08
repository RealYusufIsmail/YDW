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
