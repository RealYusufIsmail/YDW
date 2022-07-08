package io.github.realyusufismail.ydw.application.interaction;

import org.jetbrains.annotations.NotNull;

public enum InteractionCallbackType {
    PONG(1),
    CHANNEL_MESSAGE_WITH_SOURCE(4),
    DEFERRED_CHANNEL_MESSAGE_WITH_SOURCE(5),
    DEFERRED_UPDATE_MESSAGE(6),
    UPDATE_MESSAGE(7),
    APPLICATION_COMMAND_AUTOCOMPLETE_RESULT(8),
    MODAL(9),
    UNKNOWN(-1);

    private final int value;

    InteractionCallbackType(final int value) {
        this.value = value;
    }

    public static @NotNull InteractionCallbackType getValue(final int value) {
        for (final InteractionCallbackType interactionCallbackType : values()) {
            if (interactionCallbackType.getValue() == value) {
                return interactionCallbackType;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return this.value;
    }
}
