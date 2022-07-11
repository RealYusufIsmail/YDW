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

package io.github.realyusufismail.ydw.application.commands.option;

import org.jetbrains.annotations.Nullable;

public enum CommandType {
    CHAT_INPUT(1),
    USER(2),
    MESSAGE(3);

    private final int value;

    CommandType(final int value) {
        this.value = value;
    }

    public static @Nullable CommandType getCommandType(final int value) {
        for (final CommandType commandType : values()) {
            if (commandType.getValue() == value) {
                return commandType;
            }
        }
        return null;
    }

    public int getValue() {
        return this.value;
    }
}
