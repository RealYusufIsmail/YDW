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

import io.github.realyusufismail.websocket.core.CloseCode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;
import org.joda.time.DateTime;

public class ShutdownEvent extends Event {
    private final DateTime shutdownTime;
    private final CloseCode closeCode;

    public ShutdownEvent(YDW ydw, DateTime shutdownTime, CloseCode closeCode) {
        super(ydw);
        this.shutdownTime = shutdownTime;
        this.closeCode = closeCode;
    }

    public DateTime getShutdownTime() {
        return shutdownTime;
    }

    public CloseCode getCloseCode() {
        return closeCode;
    }
}
