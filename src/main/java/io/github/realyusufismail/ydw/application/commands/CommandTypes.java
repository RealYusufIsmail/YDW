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
package io.github.realyusufismail.ydw.application.commands;

public enum CommandTypes {
    /**
     * Slash commands; a text-based command that shows up when a user types /
     */
    SLASH(1),
    /**
     * A UI-based command that shows up when you right click or tap on a user
     */
    USER(2),
    /**
     * A UI-based command that shows up when you right click or tap on a message
     */
    MESSAGE(3),
    /**
     * Unknown command type
     */
    UNKNOWN(-1);

    private final int value;

    CommandTypes(int value) {
        this.value = value;
    }

    public static CommandTypes getCommandType(int value) {
        for (CommandTypes type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }
}
