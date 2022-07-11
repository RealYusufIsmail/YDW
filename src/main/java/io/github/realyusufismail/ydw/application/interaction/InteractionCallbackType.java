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
