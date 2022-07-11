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

package io.github.realyusufismail.ydwreg.entities.user;

import org.jetbrains.annotations.NotNull;

public enum UserFlags {
    NONE(0),
    STAFF(1 << 0),
    PARTNER(1 << 1),
    HYPESQUAD(1 << 2),
    BUG_HUNTER_LEVEL_1(1 << 3),
    HYPESQUAD_ONLINE_HOUSE_1(1 << 6),
    HYPESQUAD_ONLINE_HOUSE_2(1 << 7),
    HYPESQUAD_ONLINE_HOUSE_3(1 << 8),
    PREMIUM_EARLY_SUPPORTER(1 << 9),
    TEAM_PSEUDO_USER(1 << 10),
    BUG_HUNTER_LEVEL_2(1 << 14),
    VERIFIED_BOT(1 << 16),
    VERIFIED_DEVELOPER(1 << 17),
    CERTIFIED_MODERATOR(1 << 18),
    BOT_HTTP_INTERACTIONS(1 << 19),
    UNKNOWN(-1);

    private final int flags;

    UserFlags(final int flags) {
        this.flags = flags;
    }

    public static @NotNull UserFlags getFlag(int flags) {
        for (UserFlags flag : values()) {
            if (flag.getFlag() == flags) {
                return flag;
            }
        }
        return UNKNOWN;
    }

    public int getFlag() {
        return this.flags;
    }
}
