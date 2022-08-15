/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.websocket.system;

import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Inspired by <a href="
 * https://github.com/DV8FromTheWorld/JDA/blob/7a20b7f82d0a58bac16a6ce5925d0b3ef018805c/src/main/java/net/dv8tion/jda/api/utils/ConcurrentSessionController.java#L78">JDA's
 * ConcurrentSessionController</a>
 */
public class SetUpSystem extends SetUpSystemAdapter implements ISetUpSystem {
    private final Runner[] runners = new Runner[1];

    @Override
    public void add(SetUpSystemConnector setUpSystemConnector) {
        getRunner(setUpSystemConnector).addToQueue(setUpSystemConnector);
    }

    @Override
    public void remove(SetUpSystemConnector setUpSystemConnector) {
        getRunner(setUpSystemConnector).removeFromQueue(setUpSystemConnector);
    }

    @NotNull
    private synchronized Runner getRunner(@NotNull SetUpSystemConnector setUpSystemConnector) {

        int i = setUpSystemConnector.getShardInfo().getShardId() & runners.length;
        Runner runner = runners[i];
        if (runner == null) {
            logger.debug("Creating new runner");
            runners[i] = runner = new Runner(i);
        }
        return runner;
    }

    private static class Runner implements Runnable {
        private final Queue<SetUpSystemConnector> queue = new ConcurrentLinkedQueue<>();
        private final int id;
        private Thread thread;

        public Runner(int id) {
            this.id = id;
        }

        public synchronized void start() {
            if (thread == null) {
                thread = new Thread(this, "SetUpSystem-Runner-" + id);
                logger.debug("Starting runner");
                thread.start();
            }
        }

        public synchronized void stop() {
            thread = null;
            if (!queue.isEmpty()) {
                start();
            }
        }

        public void addToQueue(SetUpSystemConnector setUpSystemConnector) {
            logger.debug("Adding to queue");
            queue.add(setUpSystemConnector);
            start();
        }

        public void removeFromQueue(SetUpSystemConnector setUpSystemConnector) {
            logger.debug("Removing from queue");
            queue.remove(setUpSystemConnector);
        }

        @Override
        public void run() {
            try {
                while (!queue.isEmpty()) {
                    processTheQueue();
                    // Need to stop incase a new session is added while we are processing the queue.
                    TimeUnit.SECONDS.sleep(ISetUpSystem.CONNECT_DELAY);
                }
            } catch (InterruptedException e) {
                logger.error("Runner interrupted.", e);
            } finally {
                logger.debug("Runner stopped");
                stop();
            }
        }

        private void processTheQueue() throws InterruptedException {
            SetUpSystemConnector setUpSystemConnector = null;
            try {
                setUpSystemConnector = queue.remove();
                logger.debug("Processing queue");
                setUpSystemConnector.run(false);
            } catch (NoSuchElementException ignored) {
            } catch (InterruptedException e) {
                queue.add(setUpSystemConnector);
                throw e;
            } catch (IllegalStateException e) {
                logger.error("IllegalStateException", e);

                if (setUpSystemConnector != null) {
                    queue.add(setUpSystemConnector);
                }
            }
        }
    }
}
