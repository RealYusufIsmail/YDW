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
package io.github.realyusufismail.ydw.entities.guild;

import org.jetbrains.annotations.NotNull;

public enum SystemChannelFlags {
    /**
     * Suppress member join notifications.
     */
    SUPPRESS_JOIN_NOTIFICATIONS(1 << 0),
    /**
     * Suppress server boost notifications.
     */
    SUPPRESS_PREMIUM_SUBSCRIPTIONS(1 << 1),
    /**
     * Suppress server setup tips.
     */
    SUPPRESS_GUILD_REMINDER_NOTIFICATIONS(1 << 2),
    /**
     * Hide member join sticker reply buttons.
     */
    SUPPRESS_JOIN_NOTIFICATION_REPLIES(1 << 3),
    UNKNOWN(-1);

    private final int value;

    SystemChannelFlags(int value) {
        this.value = value;
    }

    @NotNull
    public static SystemChannelFlags fromValue(int value) {
        for (SystemChannelFlags flag : values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        return UNKNOWN;
    }

    public int getValue() {
        return value;
    }
}
