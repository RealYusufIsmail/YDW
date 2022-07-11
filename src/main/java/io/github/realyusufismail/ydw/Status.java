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

