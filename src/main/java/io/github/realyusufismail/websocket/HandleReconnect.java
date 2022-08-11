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
import io.github.realyusufismail.websocket.system.ISetUpSystem;
import io.github.realyusufismail.ydw.YDW;
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
            if (handleIdentifyRateLimit) {
                long backoff = calculateIdentifyBackoff();
                if (backoff > 0) {
                    logger.info("Sleeping for {} seconds before reconnecting", backoff);
                    Thread.sleep(backoff);
                } else {
                    logger.info("Encounters rate limit");
                }
            }
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
            ydwReg.setStatus(YDW.Status.RECONNECT_QUEUED);
            setUpSystemConnector = new ReconnectEvent();
            ydwReg.getSetupSystem().add(setUpSystemConnector);
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
            ydwReg.setStatus(YDW.Status.WAITING_TO_RECONNECT);
            // In case there is a rapid burst of reconnects, we will wait for a little longer.
            reconnectTimeout = reconnectTimeout == 0 ? 2 : Math.min(reconnectTimeout << 1, 900);
            // Wait for the reconnect timeout.
            Thread.sleep(delay * 1000);
            ydwReg.setStatus(YDW.Status.ATTEMPTING_TO_RECONNECT);
            logger.debug("Attempting to reconnect");
            try {
                connect();
            } catch (RejectedExecutionException e) {
                ydwReg.setStatus(YDW.Status.SHUTDOWN);
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

    private class ReconnectEvent implements ISetUpSystem.SetUpSystemConnector {
        @Override
        public boolean isReconnect() {
            return true;
        }

        @Override
        public YDW.ShardInfo getShardInfo() {
            return ydwReg.getShardInfo();
        }

        @Override
        public void run(boolean lastInQueue) {
            needsToReconnect = true;
            if (lastInQueue) {
                return;
            }
            try {
                ydwReg.awaitStatus(YDW.Status.LOADING_SUBSYSTEMS, YDW.Status.RECONNECT_QUEUED);
            } catch (InterruptedException e) {
                logger.error("Error waiting for reconnect confirmation", e);
                wsClose();
            }
        }
    }
}
