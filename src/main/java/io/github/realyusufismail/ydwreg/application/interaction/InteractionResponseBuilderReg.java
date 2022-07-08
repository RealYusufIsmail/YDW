package io.github.realyusufismail.ydwreg.application.interaction;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.application.interaction.InteractionCallbackType;
import io.github.realyusufismail.ydw.application.interaction.creator.InteractionResponseBuilder;
import io.github.realyusufismail.ydw.builder.MessageBuilder;
import org.jetbrains.annotations.NotNull;

public class InteractionResponseBuilderReg implements InteractionResponseBuilder {
    private InteractionCallbackType type;
    private MessageBuilder builder;

    @NotNull
    @Override
    public InteractionResponseBuilder setType(InteractionCallbackType type) {
        this.type = type;
        return this;
    }

    @Override
    public InteractionResponseBuilder setMessageBuilder(MessageBuilder messagesBuilder) {
        this.builder = messagesBuilder;
        return this;
    }

    @NotNull
    @Override
    public ObjectNode toJson() {
        return JsonNodeFactory.instance.objectNode()
            .put("type", type.getValue())
            .set("data", builder.toJson());
    }
}
