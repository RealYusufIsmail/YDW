/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.github.realyusufismail.ydw.event.events;

import io.github.realyusufismail.event.updater.IEventUpdate;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;

public class GatewayPingEvent extends Event implements IEventUpdate<YDW, Long> {

    private final long oldPing;
    private final long newPing;

    public GatewayPingEvent(YDW ydw, long oldPing) {
        super(ydw);

        this.newPing = ydw.getGatewayPing();
        this.oldPing = oldPing;
    }

    public long getOldPing() {
        return oldPing;
    }

    public long getNewPing() {
        return newPing;
    }

    @Override
    public Long getOldValue() {
        return oldPing;
    }

    @Override
    public Long getNewValue() {
        return newPing;
    }

}
