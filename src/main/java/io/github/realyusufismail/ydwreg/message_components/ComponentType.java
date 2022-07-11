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
package io.github.realyusufismail.ydwreg.message_components;

import org.jetbrains.annotations.NotNull;

public enum ComponentType {
    /**
     * A container for other components.
     */
    ACTION_ROW(1),
    /**
     * A button object.
     */
    BUTTON(2),
    /**
     * A select menu for picking from choices.
     */
    SELECT_MENU(3),
    /**
     * A text input object.
     */
    TEXT_INPUT(4),
    /**
     * For any future additions.
     */
    UNKNOWN(-1);

    private final int value;

    ComponentType(int value) {
        this.value = value;
    }

    @NotNull
    public static ComponentType getComponentType(int type) {
        for (ComponentType c : ComponentType.values()) {
            if (c.getValue() == type) {
                return c;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }
}
