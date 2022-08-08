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
package io.github.realyusufismail.ydwreg.entities.event;

import org.jetbrains.annotations.NotNull;

public enum EventType {
    STAGE_INSTANCE(1),
    VOICE(2),
    EXTERNAL(3),
    UNKNOWN(4);

    private final int id;

    EventType(final int id) {
        this.id = id;
    }

    public static @NotNull EventType getEventType(int eventType) {
        for (EventType type : values()) {
            if (type.getId() == eventType) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getId() {
        return this.id;
    }
}
