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
            
package io.github.realyusufismail.ydw.team;

import org.jetbrains.annotations.NotNull;

public enum MembershipState {
    /**
     * Weather the user has been invited to the team.
     */
    INVITED(1),
    /**
     * Weather the user has accepted the invitation.
     */
    ACCEPTED(2),
    /**
     * For future use or unknown state.
     */
    UNKNOWN(-1);

    private final int value;

    MembershipState(int value) {
        this.value = value;
    }

    @NotNull
    public static MembershipState getMembershipState(int value) {
        return switch (value) {
            case 1 -> INVITED;
            case 2 -> ACCEPTED;
            default -> UNKNOWN;
        };
    }

    public int getValue() {
        return value;
    }
}
