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

package io.github.realyusufismail.event.recieve;

import io.github.realyusufismail.ydw.event.Event;

import java.util.ArrayList;
import java.util.List;

public class EventReceiver implements IEventReceiverConfig {
    // Null as there is no default value for this parameter
    public Event event = null;

    public List<IEventReceiver> eventReceivers;

    public EventReceiver() {
        eventReceivers = new ArrayList<>();
    }

    public void receive(Event event) {
        this.event = event;

        for (IEventReceiver eventReceiver : eventReceivers) {
            eventReceiver.onEvent(event);
        }
    }

    @Override
    public void addEventReceiver(Object eventReceiver) {
        if (eventReceiver instanceof IEventReceiver) {
            eventReceivers.add((IEventReceiver) eventReceiver);
        } else {
            throw new IllegalArgumentException("EventReceiver must be instance of IEventReceiver");
        }
    }

    @Override
    public void removeEventReceiver(Object eventReceiver) {
        if (eventReceiver instanceof IEventReceiver) {
            eventReceivers.remove((IEventReceiver) eventReceiver);
        } else {
            throw new IllegalArgumentException("EventReceiver must be instance of IEventReceiver");
        }
    }
}
