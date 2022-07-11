/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.github.realyusufismail.ydw.application.interaction;

import org.jetbrains.annotations.NotNull;

public enum InteractionType {

    PING(1),
    APPLICATION_COMMAND(2),
    MESSAGE_COMPONENT(3),
    APPLICATION_COMMAND_AUTOCOMPLETE(4),
    MODAL_SUBMIT(5),
    UNKNOWN(-1);

    private final int value;

    InteractionType(final int value) {
        this.value = value;
    }

    public static @NotNull InteractionType getValue(final int value) {
        for (final InteractionType interactionType : values()) {
            if (interactionType.getValue() == value) {
                return interactionType;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return this.value;
    }
}
