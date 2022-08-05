package io.github.realyusufismail.ydw.entities.automod;

public enum TriggerType {
    /**
     * check if content contains words from a user defined list of keywords
     */
    KEYWORD(1, 3),
    /**
     * check if content contains any harmful links
     */
    HARMFUL_LINK(2, 1),
    /**
     * check if content represents generic spam
     */
    SPAM(3, 1),
    /**
     * check if content contains words from internal pre-defined wordsets
     */
    KEYWORD_PRESET(4, 1),
    /**
     * An unknown trigger type
     */
    UNKNOWN(-1, -1);

    private final int value;
    private final int maxPerGuild;

    TriggerType(int value, int maxPerGuild) {
        this.value = value;
        this.maxPerGuild = maxPerGuild;
    }

    public int getValue() {
        return value;
    }

    public int getMaxPerGuild() {
        return maxPerGuild;
    }

    public static TriggerType fromValue(int value) {
        for (TriggerType type : TriggerType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static TriggerType fromValue(int value, int maxPerGuild) {
        for (TriggerType type : TriggerType.values()) {
            if (type.getValue() == value && type.getMaxPerGuild() == maxPerGuild) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
