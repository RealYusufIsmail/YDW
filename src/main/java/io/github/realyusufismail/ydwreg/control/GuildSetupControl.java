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
package io.github.realyusufismail.ydwreg.control;

import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.ydwreg.YDWReg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

/**
 * Inspired by <a
 * href="https://github.com/DV8FromTheWorld/JDA/blob/7a20b7f82d0a58bac16a6ce5925d0b3ef018805c/src/main/java/net/dv8tion/jda/internal/handle/GuildSetupController.java#L115"JDA's
 * >GuildSetupController</a>
 */
public class GuildSetupControl {
    private static final Logger logger = LoggerFactory.getLogger(GuildSetupControl.class);
    private final YDWReg ydw;
    private StatusListener listener = (id, oldStatus, newStatus) -> logger
        .trace("[{}] Updated status {}->{}", id, oldStatus, newStatus);
    private int incompleteCount = 0;
    private Future<?> timeoutHandle;

    public GuildSetupControl(YDWReg ydw) {
        this.ydw = ydw;
    }

    public YDWReg getYDW() {
        return ydw;
    }

    private void checkReady() {
        WebSocketManager webSocketManager = getYDW().getWebSocket();
        if (incompleteCount < 1 && !webSocketManager.isReady()) {
            if (timeoutHandle != null) {
                timeoutHandle.cancel(false);
            }
            timeoutHandle = null;
            webSocketManager.ready();
        }
    }

    public enum Status {
        INIT,
        CHUNKING,
        BUILDING,
        READY,
        UNAVAILABLE,
        REMOVED
    }

    @FunctionalInterface
    public interface StatusListener {
        void onStatusChange(long guildId, Status oldStatus, Status newStatus);
    }
}
