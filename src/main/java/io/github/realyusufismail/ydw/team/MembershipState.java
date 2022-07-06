/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
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
