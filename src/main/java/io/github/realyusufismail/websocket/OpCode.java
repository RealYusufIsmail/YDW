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

package io.github.realyusufismail.websocket;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public enum OpCode {
    DISPATCH(0),
    HEARTBEAT(1),
    IDENTIFY(2),
    PRESENCE(3),
    VOICE_STATE(4),
    RESUME(6),
    RECONNECT(7),
    MEMBER_CHUNK_REQUEST(8),
    INVALIDATE_SESSION(9),
    HELLO(10),
    HEARTBEAT_ACK(11),
    GUILD_SYNC(12);

    private final int code;

    OpCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static @Nullable OpCode fromCode(int code) {
        for (OpCode opCode : values()) {
            if (opCode.getCode() == code) {
                return opCode;
            }
        }
        return null;
    }

    public static Optional<OpCode> fromOptionalCode(int code) {
        for (OpCode opCode : values()) {
            if (opCode.getCode() == code) {
                return Optional.of(opCode);
            }
        }
        return Optional.empty();
    }
}
