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
