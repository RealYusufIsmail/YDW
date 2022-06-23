package io.github.realyusufismail.ydw.application.commands;

public enum CommandTypes {
    /**
     * Slash commands; a text-based command that shows up when a user types /
     */
    SLASH(1),
    /**
     * A UI-based command that shows up when you right click or tap on a user
     */
    USER(2),
    /**
     * A UI-based command that shows up when you right click or tap on a message
     */
    MESSAGE(3),
    /**
     * Unknown command type
     */
    UNKNOWN(-1);

    private final int value;

    private CommandTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CommandTypes getCommandType(int value) {
        for (CommandTypes type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
