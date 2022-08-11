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
package io.github.realyusufismail.websocket.system;

import com.neovisionaries.ws.client.OpeningHandshakeException;
import org.slf4j.Logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Inspired by <a href="
 * https://github.com/DV8FromTheWorld/JDA/blob/7a20b7f82d0a58bac16a6ce5925d0b3ef018805c/src/main/java/net/dv8tion/jda/api/utils/SessionControllerAdapter.java#L78">JDA's
 * ConcurrentSessionController</a>
 */
public class SetUpSystemAdapter implements ISetUpSystem {
    protected static final Logger logger =
            org.slf4j.LoggerFactory.getLogger(SetUpSystemAdapter.class);
    private final Object lock = new Object();
    private Queue<SetUpSystemConnector> connectQueue;
    private Thread workerHandle;
    private long lastConnect = 0;

    public SetUpSystemAdapter() {
        connectQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void add(SetUpSystemConnector setUpSystemConnector) {
        remove(setUpSystemConnector);
        connectQueue.add(setUpSystemConnector);
        runQueueHandler();
    }

    @Override
    public void remove(SetUpSystemConnector setUpSystemConnector) {
        connectQueue.remove(setUpSystemConnector);
    }

    protected void runQueueHandler() {
        synchronized (lock) {
            if (workerHandle == null) {
                workerHandle = new WorkerHandler();
                workerHandle.start();
            }
        }
    }

    private class WorkerHandler extends Thread {
        // The delay between each connect attempt.
        private final long delay;

        public WorkerHandler() {
            this(CONNECT_DELAY);
        }

        public WorkerHandler(int delay) {
            this(TimeUnit.SECONDS.toMillis(delay));
        }

        public WorkerHandler(long delay) {
            super("SetUpSystemAdapter-WorkerHandler");
            this.delay = delay;
            super.setUncaughtExceptionHandler(this::handleFailure);
        }

        private void handleFailure(Thread t, Throwable e) {
            logger.error("Exception in SetUpSystemAdapter-WorkerHandler", e);
        }

        @Override
        public void run() {
            try {
                if (delay > 0) {
                    final long interval = System.currentTimeMillis() - lastConnect;
                    if (interval < this.delay) {
                        Thread.sleep(this.delay - interval);
                    }
                }
            } catch (InterruptedException e) {
                logger.error("WorkerHandler interrupted.", e);
                return;
            }

            proccessTheQueue();

            synchronized (lock) {
                workerHandle = null;
                if (!connectQueue.isEmpty()) {
                    runQueueHandler();
                }
            }
        }

        private void proccessTheQueue() {
            // need to check if there is more than one in the queue
            // if there is, then we need to process them all
            boolean isMoreThanOne = connectQueue.size() > 1;

            while (!connectQueue.isEmpty()) {
                SetUpSystemConnector connector = connectQueue.poll();
                try {
                    connector.run(isMoreThanOne && connectQueue.isEmpty());
                    isMoreThanOne = true;
                    // get the time of the last connection
                    lastConnect = System.currentTimeMillis();

                    if (connectQueue.isEmpty()) {
                        break;
                    }

                    if (delay > 0) {
                        Thread.sleep(this.delay);
                    }
                } catch (IllegalStateException e) {
                    Throwable cause = e.getCause();

                    if (cause instanceof OpeningHandshakeException) {
                        logger.error("WebSocket connection failed. will retry adding to the queue.",
                                e);
                    } else {
                        logger.error("Unexpected error.", e);
                    }

                    add(connector);
                } catch (InterruptedException e) {
                    logger.error("WorkerHandler interrupted.", e);
                    add(connector);
                    return;
                }
            }
        }
    }
}
