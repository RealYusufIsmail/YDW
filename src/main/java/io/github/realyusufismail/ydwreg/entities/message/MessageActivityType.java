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

package io.github.realyusufismail.ydwreg.entities.message;

import org.jetbrains.annotations.NotNull;

public enum MessageActivityType {
    JOIN(1),
    SPECTATE(2),
    LISTEN(3),
    JOIN_REQUEST(4),
    UNKNOWN(-1);

    private final int value;

    MessageActivityType(int value) {
        this.value = value;
    }

    @NotNull
    public static MessageActivityType fromInt(int i) {
        for (MessageActivityType type : values()) {
            if (type.getValue() == i) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }
}
