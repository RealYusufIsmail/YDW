/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package io.github.realyusufismail.websocket.core;

import org.jetbrains.annotations.NotNull;

public enum CloseCode {
    UNKNOWN(4000, "We're not sure what went wrong. Try reconnecting?"),
    UNKNOWN_OPCODE(4001,
            "You sent an invalid Gateway opcode or an invalid payload for an opcode. Don't do that!"),
    DECODE_ERROR(4002, "You sent an invalid payload to us. Make sure you follow the protocol."),
    NOT_AUTHENTICATED(4003, "You sent an invalid token. Make sure you follow the protocol."),
    AUTHENTICATION_FAILED(4004, "You sent an invalid token. Make sure you follow the protocol.",
            false),
    ALREADY_AUTHENTICATED(4005, "You sent more than one token. Make sure you follow the protocol."),
    INVALID_SEQ(4007,
            "The sequence sent when resuming the session was invalid. Reconnect and start a new session."),
    RATE_LIMITED(4008,
            "Woah nelly! You're sending payloads to us too quickly. Slow it down! You will be disconnected on receiving this."),
    SESSION_TIMEOUT(4009, "Your session timed out. Reconnect and start a new one.", false),
    INVALID_SHARD(4010,
            "You sent us an invalid shard when identifying. Make sure you follow the protocol.",
            false),
    SHARDING_REQUIRED(4011,
            "The session would have handled too many guilds - you are required to shard your connection in order to connect.",
            false),
    INVALID_API_VERSION(4012, "You sent an invalid version for the gateway.", false),
    INVALID_INTENTS(4013, "You sent an invalid intents. Make sure you follow the protocol.", false),
    DISALLOWED_INTENTS(4014,
            "You sent a disallowed intent for a Gateway Intent. You may have tried to specify an intent that you have not enabled or are not approved for.",
            false);

    private final int code;
    private final String reason;
    private final boolean reconnect;

    CloseCode(int code, String reason, boolean reconnect) {
        this.code = code;
        this.reason = reason;
        this.reconnect = reconnect;
    }

    CloseCode(int code, String reason) {
        this(code, reason, true);
    }

    public static @NotNull CloseCode fromCode(int code) {
        for (CloseCode closeCode : CloseCode.values()) {
            if (closeCode.getCode() == code) {
                return closeCode;
            }
        }
        return UNKNOWN;
    }

    public static @NotNull CloseCode fromReason(String reason) {
        for (CloseCode closeCode : CloseCode.values()) {
            if (closeCode.getReason().equals(reason)) {
                return closeCode;
            }
        }
        return UNKNOWN;
    }

    // gets the reason for a specific code
    public static String getReason(int code) {
        for (CloseCode closeCode : CloseCode.values()) {
            if (closeCode.getCode() == code) {
                return closeCode.getReason();
            }
        }
        return "Unknown";
    }

    // gets the code for a specific reason
    public static int getCode(String reason) {
        for (CloseCode closeCode : CloseCode.values()) {
            if (closeCode.getReason().equals(reason)) {
                return closeCode.getCode();
            }
        }
        return 4000;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    public boolean isReconnect() {
        return reconnect;
    }
}
