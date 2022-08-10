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
package io.github.realyusufismail.websocket;

import com.neovisionaries.ws.client.WebSocket;
import io.github.realyusufismail.ydw.event.events.ShutdownEvent;
import io.github.realyusufismail.ydwreg.YDWReg;
import org.joda.time.DateTime;
import org.slf4j.MDC;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

public class HandleReconnect extends WebSocketManager {
    private final WebSocket ws;
    private final int closeCode;

    public HandleReconnect(YDWReg ydw, WebSocket ws, int closeCode) {
        super(ydw);
        this.ws = ws;
        this.closeCode = closeCode;
    }

    public void handle() throws InterruptedException {
        if (sessionId == null) {
            logger.warn(
                    "A disconnection occurred due to receiving the close code '{}', will try to reconnect.",
                    closeCode);
            queueReconnect();
        } else {
            logger.debug(
                    "A disconnection occurred due to receiving the close code '{}', will try to reconnect session.",
                    closeCode);
            reconnect();
        }
    }

    public void queueReconnect() {
        try {
            reconnectQueued = true;
            setUpSystem = new ReconnectEvent();
            // TODO: add reconnect event to queue.
        } catch (RejectedExecutionException e) {
            logger.warn("Could not queue reconnect event, will try to reconnect immediately.", e);
            ydwReg.handelEvent(new ShutdownEvent(ydwReg, DateTime.now(), 1006));
        }
    }

    private void reconnect() throws InterruptedException {
        reconnect(false);
    }

    /**
     * Used to reconnect to the gateway.
     *
     * @param queueReconnect If it was pulled from the reconnect queue.
     * @throws InterruptedException If the thread is interrupted.
     */
    private void reconnect(boolean queueReconnect) throws InterruptedException {
        Set<MDC.MDCCloseable> contextEntries = null;
        Map<String, String> previousContext = null;

        {
            ConcurrentMap<String, String> contextMap = ydwReg.getMdcContextMap();
            if (queueReconnect && contextMap != null) {
                previousContext = MDC.getCopyOfContextMap();
                contextEntries = contextMap.entrySet()
                    .stream()
                    .map(e -> MDC.putCloseable(e.getKey(), e.getValue()))
                    .collect(Collectors.toSet());
            }
        }

        if (sessionId != null) {
            reconnectTimeout = 0;
        }

        logger.debug("Attempting to reconnect to the gateway in {} minutes.", reconnectTimeout);

        while (needsToReconnect) {
            int delay = reconnectTimeout;

            // In case there is a rapid burst of reconnects, we will wait for a little longer.
            reconnectTimeout = reconnectTimeout == 0 ? 2 : Math.min(reconnectTimeout << 1, 900);
            // Wait for the reconnect timeout.
            Thread.sleep(delay * 1000);
            logger.debug("Attempting to reconnect");
            try {
                connect();
            } catch (RejectedExecutionException e) {
                ydwReg.handelEvent(new ShutdownEvent(ydwReg, DateTime.now(), 1000));
            } catch (RuntimeException e) {
                logger.error("Error reconnecting, next attempt in {} minutes.", reconnectTimeout);
            }
        }

        if (contextEntries != null) {
            contextEntries.forEach(MDC.MDCCloseable::close);
        }

        if (previousContext != null) {
            previousContext.forEach(MDC::put);
        }
    }

    private class ReconnectEvent extends SetUpSystem {
        @Override
        boolean isReconnect() {
            return true;
        }

        @Override
        void handle(boolean lastInQueue) {
            needsToReconnect = true;
            if (lastInQueue) {
                return;
            }
            try {
                awaitConfirmation();
            } catch (InterruptedException e) {
                logger.error("Error waiting for reconnect confirmation", e);
                wsClose();
            }
        }
    }
}
