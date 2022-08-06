package io.github.realyusufismail.ydw.entities.automod;

public enum KeywordPresetType {
    /**
     * Words that may be considered forms of swearing or cursing
     */
    PROFANITY(1),
    /**
     * Words that refer to sexually explicit behavior or activity
     */
    SEXUAL_CONTENT(2),
    /**
     * Personal insults or words that may be considered hate speech
     */
    SLURS(3),
    /**
     * An unknown type.
     */
    UNKNOWN(-1);

    private final int value;


    KeywordPresetType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static KeywordPresetType fromValue(int value) {
        for (KeywordPresetType type : KeywordPresetType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
