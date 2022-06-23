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

package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.websocket.event.BasicEvent;
import io.github.realyusufismail.websocket.event.EventExtender;
import io.github.realyusufismail.ydw.YDW;

public class ReadyEvent extends EventExtender {

    private final long numberOfUnavailableGuilds;

    private final long numberOfAvailableGuilds;

    private final long numberOfGuilds;

    public ReadyEvent(YDW ydw) {
        super(ydw);

        this.numberOfUnavailableGuilds = ydw.getUnavailableGuilds().size();
        this.numberOfAvailableGuilds = ydw.getAvailableGuilds().size();
        this.numberOfGuilds = numberOfAvailableGuilds + numberOfUnavailableGuilds;
    }

    public long getNumberOfUnavailableGuilds() {
        return numberOfUnavailableGuilds;
    }

    public long getNumberOfAvailableGuilds() {
        return numberOfAvailableGuilds;
    }

    public long getNumberOfGuilds() {
        return numberOfGuilds;
    }
}
