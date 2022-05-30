package io.github.realyusufismail.yusufsdiscordbot.ydl.team;

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

    public int getValue() {
        return value;
    }

    @NotNull
    public static MembershipState getMembershipState(int value) {
        return switch (value) {
            case 1 -> INVITED;
            case 2 -> ACCEPTED;
            default -> UNKNOWN;
        };
    }
}
