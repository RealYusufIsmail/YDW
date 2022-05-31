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

package io.github.realyusufismail.ydw;

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

