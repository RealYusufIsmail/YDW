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

public enum OpCode {
    /**
     * An event was dispatched.
     */
    DISPATCH(0, false),
    /**
     * Fired periodically by the client to keep the connection alive.
     */
    HEARTBEAT(1, true, false),
    /**
     * Starts a new session during the initial handshake.
     */
    IDENTIFY(2),
    /**
     * Update the client's presence.
     */
    PRESENCE(3),
    /**
     * Used to join/leave or move between voice channels.
     */
    VOICE_STATE(4),
    /**
     * Resume a previous session that was disconnected.
     */
    RESUME(6),
    /**
     * You should attempt to reconnect and resume immediately.
     */
    RECONNECT(7, false),
    /**
     * Request information about offline guild members in a large guild.
     */
    REQUEST_GUILD_MEMBERS(8),
    /**
     * The session has been invalidated. You should reconnect and identify/resume accordingl
     */
    INVALID_SESSION(9, false),
    /**
     * Sent immediately after connecting, contains the heartbeat_interval to use.
     */
    HELLO(10, false),
    /**
     * Sent in response to receiving a heartbeat to acknowledge that it has been received.
     */
    HEARTBEAT_ACK(11, false),
    /**
     * For future use or unknown opcodes.
     */
    UNKNOWN(-1, false, false);

    private final int code;
    private final boolean send;
    private final boolean receive;

    OpCode(int code) {
        this(code, true, false);
    }

    OpCode(int code, boolean send) {
        this(code, send, false);
    }

    OpCode(int code, boolean send, boolean receive) {
        this.code = code;
        this.send = send;
        this.receive = receive;
    }

    public static @NotNull OpCode fromCode(int code) {
        for (OpCode opCode : values()) {
            if (opCode.getCode() == code) {
                return opCode;
            }
        }
        return UNKNOWN;
    }

    public int getCode() {
        return code;
    }
}
