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

package io.github.realyusufismail.ydw.ydl;

public enum Status {
    /**
     * The bot is online and ready to receive commands.
     */
    ONLINE("online"),
    /**
     * The bot is offline and not ready to receive commands.
     */
    OFFLINE("offline"),
    /**
     * The bot is idle
     */
    IDLE("idle"),
    /**
     * The bot is busy
     */
    DND("dnd"),
    /**
     * The bot is invisible
     */
    INVISIBLE("invisible"),
    /**
     * This will be used if future statuses are added.
     */
    UNKNOWN("Unknown");

    private final String statusNames;

    Status(String status) {
        this.statusNames = status;
    }

    public String getStatus() {
        return statusNames;
    }
}

