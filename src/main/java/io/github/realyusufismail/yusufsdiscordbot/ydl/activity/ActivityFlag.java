package io.github.realyusufismail.yusufsdiscordbot.ydl.activity;

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

    private ActivityFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
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
}
