package io.github.realyusufismail.ydw.entities.automod;

public enum EventType {
    /**
     * when a member sends or edits a message in the guild
     */
    MESSAGE_SEND(1),
    UNKNOWN(-1);

    private final int value;

    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EventType fromValue(int value) {
        for (EventType type : EventType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
