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

package io.github.realyusufismail.ydw.activity;

import org.jetbrains.annotations.NotNull;

public enum ActivityFlag {
    INSTANCE(1 << 0),
    JOIN(1 << 1),
    SPECTATE(1 << 2),
    JOIN_REQUEST(1 << 3),
    SYNC(1 << 4),
    PLAY(1 << 5),
    PARTY_PRIVACY_FRIENDS(1 << 6),
    PARTY_PRIVACY_VOICE_CHANNEL(1 << 7),
    EMBEDDED(1 << 8),
    UNKNOWN(-1);

    private final int value;

    ActivityFlag(int value) {
        this.value = value;
    }

    @NotNull
    public static ActivityFlag getFlag(int value) {
        for (ActivityFlag flag : values()) {
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
