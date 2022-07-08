package io.github.realyusufismail.ydw.application.interaction.creator;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.application.interaction.InteractionCallbackType;
import io.github.realyusufismail.ydw.builder.MessageBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface InteractionResponseBuilder {

    @NotNull
    InteractionResponseBuilder setType(InteractionCallbackType type);

    @Nullable
    InteractionResponseBuilder setMessageBuilder(MessageBuilder messagesBuilder);

    @NotNull
    ObjectNode toJson();
}
