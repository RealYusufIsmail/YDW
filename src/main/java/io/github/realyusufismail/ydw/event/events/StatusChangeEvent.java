/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydw.event.events;

import io.github.realyusufismail.event.updater.IEventUpdate;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;

public class StatusChangeEvent extends Event implements IEventUpdate<YDW, YDW.Status> {

    private final YDW.Status newStatus;
    private final YDW.Status oldStatus;

    public StatusChangeEvent(YDW ydw, YDW.Status newStatus, YDW.Status oldStatus) {
        super(ydw);
        this.newStatus = newStatus;
        this.oldStatus = oldStatus;
    }

    public YDW.Status getNewStatus() {
        return newStatus;
    }

    public YDW.Status getOldStatus() {
        return oldStatus;
    }

    @Override
    public YDW.Status getOldValue() {
        return oldStatus;
    }

    @Override
    public YDW.Status getNewValue() {
        return newStatus;
    }
}
