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
package io.github.realyusufismail.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.neovisionaries.ws.client.*;
import io.github.realyusufismail.websocket.core.CloseCode;
import io.github.realyusufismail.websocket.core.EventNames;
import io.github.realyusufismail.websocket.core.OpCode;
import io.github.realyusufismail.websocket.system.ISetUpSystem;
import io.github.realyusufismail.ydw.GateWayIntent;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.YDWInfo;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydw.entities.AvailableGuild;
import io.github.realyusufismail.ydw.entities.UnavailableGuild;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;
import io.github.realyusufismail.ydw.event.events.ReconnectEvent;
import io.github.realyusufismail.ydw.event.events.ResumedEvent;
import io.github.realyusufismail.ydw.event.events.ShutdownEvent;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.entities.AvailableGuildReg;
import io.github.realyusufismail.ydwreg.entities.SelfUserReg;
import io.github.realyusufismail.ydwreg.entities.UnavailableGuildReg;
import io.github.realyusufismail.ydwreg.handle.OnHandler;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Inspired from JDA's <a href=
 * "https://github.com/DV8FromTheWorld/JDA/blob/master/src/main/java/net/dv8tion/jda/internal/requests/WebSocketClient.java">WebSocketClient</a>
 */
public class WebSocketManager extends WebSocketAdapter implements WebSocketListener {

    // Create a WebSocketFactory instance.
    static WebSocket ws;
    // YDW
    protected final YDWReg ydwReg;
    public static final Logger logger = LoggerFactory.getLogger(WebSocketManager.class);
    protected static final long CONNECT_DELAY =
            TimeUnit.SECONDS.toMillis(ISetUpSystem.CONNECT_DELAY); // same as 1000 * IDENTIFY_DELAY
    // the core pool
    private int corePoolSize;
    // the scheduled thread pool
    protected final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(corePoolSize);
    // The bots token.
    protected final String token;
    // The gateway intents
    protected final int intent;
    // The sequence number, used for resuming sessions and heartbeats.
    // The status of the bot e.g. online, idle, dnd, invisible etc.
    protected final String status;
    protected final Integer largeThreshold;
    // The activity of the bot e.g. playing, streaming, listening, watching etc.
    protected final ActivityConfig activity;
    // The sequence number, used for resuming sessions and heartbeats.
    protected Integer seq = null;
    // The session id. This is basically a key that stores the past activity of the bot.
    protected volatile String sessionId = null;
    // gateway url for resuming
    protected String resumeGateWayUrl = null;
    // Used to indicate that the bot has connected to the gateway.
    protected boolean connected = false;
    // The thread used for the heartbeat. Needed in cases such as disconnect.
    protected volatile Future<?> heartbeatThread;
    // code 1000 meaning resuming can not be done
    protected boolean canNotResume = false;
    // the time that the hearbeat started
    protected long heartbeatStartTime;
    // The amounts of time that the heartbeat has been missed.
    protected int heartbeatsMissed = 0;
    // weather we need to reconnect or not.
    protected boolean needsToReconnect;
    // weather the authentication info has been sent or not.
    protected boolean sentAuthInfo = false;
    protected static final String INVALID_SESSION = "INVALID_SESSION";
    // As discord states if the TCP connection is closed, or use a different close code, the bot
    // session will remain active and timeout after a few minutes. This can be useful for a
    // reconnect, which will resume the previous session.
    protected int reconnectTimeout = 2;
    // Used for reconnecting and starting(not ready) the bot.
    protected volatile ISetUpSystem.SetUpSystemConnector setUpSystemConnector;
    // indicates that ws has shutdown
    protected boolean shutdown = false;
    protected boolean processingReady = true;
    protected boolean firstInit = true;
    // weather the bot has started or not
    protected boolean initiating;
    // Used to track the rate limit
    protected volatile long ratelimitResetTime;
    // Used to track the amount of messages sent
    protected final AtomicInteger messagesSent = new AtomicInteger(0);
    // Weather the last message was sent or not
    protected volatile boolean printedRateLimitMessage = false;
    protected boolean handleIdentifyRateLimit = false;
    // The time it took connect(identify) to complete
    protected long connectTime = 0;

    protected WebSocketManager(YDW ydw) {
        this.ydwReg = (YDWReg) ydw;
        this.token = null;
        this.intent = 0;
        this.status = null;
        this.largeThreshold = null;
        this.activity = null;
        this.setUpSystemConnector = null;
    }

    public WebSocketManager(YDW ydw, String token, Integer intent, String status,
            int largeThreshold, ActivityConfig activity) {
        this.ydwReg = (YDWReg) ydw;
        this.token = token;
        this.intent = intent;
        this.status = status;
        this.largeThreshold = largeThreshold;
        this.activity = activity;
        this.setUpSystemConnector = new ConnectEvent();
        try {
            ydwReg.getSetupSystem().add(setUpSystemConnector);
        } catch (RuntimeException | Error e) {
            logger.error("Error adding the setUpSystem to the queue, shutting down the bot.", e);
            ydwReg.setStatus(YDW.Status.SHUTDOWN);
            ydwReg.handelEvent(new ShutdownEvent(ydwReg, DateTime.now(), 1006));
            if (e instanceof RuntimeException)
                throw (RuntimeException) e;
            else
                throw (Error) e;
        }
    }


    public WebSocketManager(YDW ydw, String token, @NotNull GateWayIntent intent, String status,
            int largeThreshold, ActivityConfig activity) {
        this(ydw, token, intent.getValue(), status, largeThreshold, activity);
    }

    public synchronized void connect() {

        if (ydwReg.getStatus() != YDW.Status.ATTEMPTING_TO_RECONNECT)
            ydwReg.setStatus(YDW.Status.CONNECTING_TO_WEBSOCKET);

        if (shutdown)
            throw new RuntimeException("The bot is shutdown.");

        initiating = true;
        String url = (resumeGateWayUrl != null ? resumeGateWayUrl : YDWInfo.DISCORD_GATEWAY_LINK)
                + YDWInfo.REST_OF_DISCORD_GATEWAY_LINK;
        // wss://gateway.discord.gg/?v=10&encoding=json
        try {
            WebSocketFactory wsFactory = new WebSocketFactory(new WebSocketFactory());
            setServerName(wsFactory, url);
            if (wsFactory.getSocketTimeout() > 0)
                wsFactory.setSocketTimeout(Math.max(1000, wsFactory.getSocketTimeout()));
            else
                wsFactory.setSocketTimeout(10000);
            ws = wsFactory.createSocket(url);
            ws.addHeader("Accept-Encoding", "gzip").addListener(this).connect();

        } catch (IOException | WebSocketException e) {
            resumeGateWayUrl = null;
            throw new IllegalStateException(e);
        }
    }

    private void setServerName(WebSocketFactory wsFactory, String url) {
        String host = getHost(url);

        if (host != null) {
            wsFactory.setServerName(host);
        }
    }

    private String getHost(String url) {
        return URI.create(url).getHost();
    }

    @Override
    public void onTextMessage(WebSocket websocket, String message) throws Exception {
        onHandelMessage(message);
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
        // in a rare case, HELLO might not be sent so waiting 10 seconds to see if it is sent
        prepareClose();
        ydwReg.setStatus(YDW.Status.IDENTIFYING_SESSION);
        if (sessionId == null) {
            System.out.println("Connected to the gateway.");
        } else {
            System.out.println("Resuming session.");
        }

        connected = true;
        messagesSent.set(0);
        ratelimitResetTime = System.currentTimeMillis() + 60000;
        if (sessionId == null) {
            identify();
        } else {
            resume();
        }
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame,
            WebSocketFrame clientCloseFrame, boolean closedByServer) {
        sentAuthInfo = false;
        connected = false;
        if (Thread.currentThread().isInterrupted()) {
            Thread thread = new Thread(() -> new HandleDisconnect(this.ydwReg, websocket,
                    serverCloseFrame, clientCloseFrame, closedByServer).handle());
            thread.setName("reconnect-thread");
            thread.start();
        } else {
            new HandleDisconnect(this.ydwReg, websocket, serverCloseFrame, clientCloseFrame,
                    closedByServer).handle();
        }
    }

    @Override
    public void onError(WebSocket websocket, @NotNull WebSocketException cause) {
        if (cause.getCause() instanceof SocketTimeoutException) {
            logger.error("Socket timeout due to {}", cause.getCause().getMessage());
        } else if (cause.getCause() instanceof IOException) {
            logger.error("IO error {}", cause.getCause().getMessage());
        } else if (cause.getCause() instanceof ClosedChannelException) {
            logger.error("Closed channel error {}", cause.getCause().getMessage());
        } else {
            logger.error("Unknown error", cause);
        }
    }

    public void onHandelMessage(String message) {
        try {
            JsonNode payload = ydwReg.getMapper().readTree(message);
            onHandel(payload);
        } catch (Exception e) {
            logger.error("Error while handling message", e);
        }
    }

    public void onHandel(JsonNode payload) {
        onEvent(payload);
    }

    public void onEvent(@NotNull JsonNode payload) {
        Optional<Integer> s = Optional.of(payload.get("s").asInt());
        s.ifPresent(integer -> this.seq = integer);


        Optional<JsonNode> d = Optional.of(payload.get("d"));
        Optional<Integer> op = Optional.of(payload.get("op").asInt());
        op.ifPresent(integer -> onOpcode(op.get(), d.get()));

        Optional<String> t = Optional.of(payload.get("t").asText());

        t.ifPresent(text -> {
            EventNames event = EventNames.getEvent(text);
            JsonNode eventD = payload.get("d");
            try {
                switch (event) {
                    case READY -> {
                        reconnectTimeout = 2;
                        processingReady = true;
                        ydwReg.setStatus(YDW.Status.LOADING_SUBSYSTEMS);
                        List<UnavailableGuild> unavailableGuilds = new ArrayList<>();
                        ArrayNode guilds = (ArrayNode) eventD.get("guilds");
                        for (JsonNode guild : guilds) {
                            if (guild.get("unavailable").asBoolean()) {
                                UnavailableGuild unavailableGuild = new UnavailableGuildReg(ydwReg,
                                        guild.get("id").asLong(), guild);
                                unavailableGuilds.add(unavailableGuild);
                            }
                        }

                        List<AvailableGuild> availableGuilds = new ArrayList<>();
                        for (JsonNode guild : guilds) {
                            if (!guild.get("unavailable").asBoolean()) {
                                AvailableGuild availableGuild = new AvailableGuildReg(ydwReg,
                                        guild.get("id").asLong(), guild);
                                availableGuilds.add(availableGuild);
                            }
                        }

                        ydwReg.setUnavailableGuilds(unavailableGuilds);
                        ydwReg.setAvailableGuilds(availableGuilds);

                        ydwReg.setApplicationId(eventD.get("application").get("id").asLong());


                        ydwReg.setSelfUser(new SelfUserReg(eventD.get("user"),
                                eventD.get("user").get("id").asLong(), ydwReg));

                        sessionId = eventD.get("session_id").asText();

                        resumeGateWayUrl = eventD.hasNonNull("resume_gateway_url")
                                ? eventD.get("resume_gateway_url").asText()
                                : null;

                        ydwReg.setReady(true);

                        ydwReg.handelEvent(new ReadyEvent(ydwReg, unavailableGuilds.size(),
                                availableGuilds.size()));
                    }
                    case RESUMED -> {
                        reconnectTimeout = 2;
                        sentAuthInfo = true;

                        if (!processingReady) {
                            initiating = false;
                            ready();
                        } else {
                            logger.debug("Resumed session.");
                            ydwReg.setStatus(YDW.Status.LOADING_SUBSYSTEMS);
                        }

                        ydwReg.handelEvent(new ResumedEvent(ydwReg, true));
                    }
                    default -> new OnHandler(ydwReg, text, payload).start();
                }
            } catch (Exception e) {
                logger.error("Error while handling event", e);
            }
        });
    }

    public void ready() {
        if (initiating) {
            initiating = false;
            processingReady = false;

            if (firstInit) {
                firstInit = false;
                if (ydwReg.getGuilds().size() >= 2000) {
                    YDWReg.logger.warn("You are trying to connect to over 2000 guilds. This is not "
                            + "recommended. You can still connect, but you will not be able to "
                            + "use any features that require all guilds to be loaded.");
                }
                YDWReg.logger.info("Connected to {} guilds.", ydwReg.getGuilds().size());
                ydwReg.handelEvent(new ReadyEvent(ydwReg, ydwReg.getUnavailableGuilds().size(),
                        ydwReg.getAvailableGuilds().size()));
            } else {
                YDWReg.logger.info("Reconnected to {} guilds.", ydwReg.getGuilds().size());
                ydwReg.handelEvent(new ReconnectEvent(ydwReg, true));
            }
        } else {
            YDWReg.logger.info("Resumed session.");
            ydwReg.handelEvent(new ResumedEvent(ydwReg, true));
        }
        ydwReg.setStatus(YDW.Status.CONNECTED);
    }

    public void onOpcode(Integer opcode, JsonNode d) {
        OpCode op = OpCode.fromCode(opcode);
        switch (op) {
            case HEARTBEAT -> sendHeartbeat();
            case HELLO -> {
                logger.debug("Received HELLO");
                int heartbeatInterval = d.get("heartbeat_interval").asInt();
                // Send heartbeat
                sendHeartbeat(heartbeatInterval);
            }
            case HEARTBEAT_ACK -> {
                logger.debug("Received HEARTBEAT_ACK");
                heartbeatsMissed = 0;
                ydwReg.setGatewayPing(System.currentTimeMillis() - heartbeatStartTime);
            }
            case INVALID_SESSION -> {
                logger.debug("Received INVALID_SESSION");
                boolean shouldResume = d.asBoolean();
                handleIdentifyRateLimit = handleIdentifyRateLimit
                        && System.currentTimeMillis() - connectTime < CONNECT_DELAY;
                logger.debug("Should resume: {}", shouldResume);
                sentAuthInfo = false;

                int reconnectCode = shouldResume ? CloseCode.RECONNECT.getCode()
                        : CloseCode.ZOMBIE_CONNECTION.getCode();

                if (shouldResume)
                    logger.debug("Session invalidated, resuming session");
                else
                    invalidate();

                wsClose(reconnectCode, INVALID_SESSION);
            }
            case RECONNECT -> {
                logger.debug("Received RECONNECT");
                wsClose(CloseCode.RECONNECT.getCode(), "Op: RECONNECT");
            }
            default -> logger.debug("Unhandled opcode: {}", op);
        }
    }

    private void wsClose(int code, String reason) {
        prepareClose();
        if (ws != null) {
            ws.sendClose(code, reason);
        }
    }

    protected void wsClose() {
        prepareClose();
        if (ws != null) {
            ws.sendClose(1000);
        }
    }

    private void prepareClose() {
        try {
            if (ws != null) {
                Socket rawSocket = ws.getSocket();
                if (rawSocket != null) // attempt to set a 10 second timeout for the close frame
                    rawSocket.setSoTimeout(10000); // this has no affect if the socket is already
                // stuck in a read call
            }
        } catch (SocketException ignored) {
        }
    }

    /**
     * After receiving Opcode 10 Hello, the client may begin sending Opcode 1 Heartbeat payloads
     * after heartbeat_interval * jitter milliseconds (where jitter is a random value between 0 and
     * 1), and every heartbeat_interval milliseconds thereafter. You may send heartbeats before this
     * interval elapses, but you should avoid doing so unless necessary. here is already tolerance
     * in the heartbeat_interval that will cover network latency, so you do not need to account for
     * it in your own implementation - waiting the precise interval will suffice.
     *
     * @param heartbeatInterval the interval in milliseconds between heartbeats
     */
    public void sendHeartbeat(int heartbeatInterval) {
        try {
            Socket rawSocket = this.ws.getSocket();
            if (rawSocket != null)
                rawSocket.setSoTimeout(heartbeatInterval + 10000); // setup a timeout when we miss
                                                                   // heartbeats
        } catch (SocketException ex) {
            logger.warn("Failed to setup timeout for socket", ex);
        }

        heartbeatThread = scheduler.scheduleAtFixedRate(() -> {
            if (connected) // IS FALSE
                sendHeartbeat();
            logger.info("Sending heartbeat");
        }, 0, heartbeatInterval, TimeUnit.MILLISECONDS);
    }

    /**
     * <p>
     * The gateway may request a heartbeat from the client in some situations by sending an Opcode 1
     * Heartbeat. When this occurs, the client should immediately send an Opcode 1 Heartbeat without
     * waiting the remainder of the current interval.
     * </p>
     * <p>
     * If a client does not receive a heartbeat ack between its attempts at sending heartbeats, this
     * may be due to a failed or "zombied" connection. The client should then immediately terminate
     * the connection with a non-1000 close code, reconnect, and attempt to Resume.
     * </p>
     */
    public void sendHeartbeat() {

        Integer d;

        if (seq != null) {
            d = seq;
        } else {
            d = null;
        }

        JsonNode heartbeat = JsonNodeFactory.instance.objectNode()
            .put("op", OpCode.HEARTBEAT.getCode())
            .put("d", d);

        if (heartbeatsMissed >= 2) {
            heartbeatsMissed = 0;
            logger.warn("Heartbeat missed, will attempt to reconnect");
            prepareClose();
            ws.disconnect(CloseCode.RECONNECT.getCode(), "ZOMBIE CONNECTION");
        } else {
            heartbeatsMissed += 1;
            send(heartbeat, true);
            heartbeatStartTime = System.currentTimeMillis();
        }
    }


    public void identify() {
        ObjectNode d = JsonNodeFactory.instance.objectNode()
            .put("token", token)
            .put("intents", intent)
            .set("properties",
                    JsonNodeFactory.instance.objectNode()
                        .put("os", System.getProperty("os.name"))
                        .put("browser", "YDL")
                        .put("device", "YDL"));

        ObjectNode presence = JsonNodeFactory.instance.objectNode();

        if (activity != null) {
            ArrayNode activities = JsonNodeFactory.instance.arrayNode();
            activities.add(JsonNodeFactory.instance.objectNode()
                .put("name", activity.getName())
                .put("type", activity.getActivity()));
            presence.set("activities", activities);
        }

        if (status != null) {
            presence.put("status", status);
        }

        if (largeThreshold != null) {
            d.put("large_threshold", largeThreshold);
        }

        JsonNode json = JsonNodeFactory.instance.objectNode().put("op", 2).set("d", d);
        sentAuthInfo = true;
        send(json, true);
        connectTime = System.currentTimeMillis();
        logger.info("Connected");
    }

    public void resume() {
        JsonNode payload = JsonNodeFactory.instance.objectNode()
            .put("token", token)
            .put("session_id", sessionId)
            .put("seq", seq);

        JsonNode identify = JsonNodeFactory.instance.objectNode()
            .put("op", OpCode.RESUME.getCode())
            .set("d", payload);

        send(identify, true);
        logger.info("Reconnected");
    }

    private boolean send(JsonNode message, boolean skipQueue) {
        if (!connected)
            return false;

        long now = System.currentTimeMillis();

        if (this.ratelimitResetTime <= now) {
            this.messagesSent.set(0);
            this.ratelimitResetTime = now + 6000;
            this.printedRateLimitMessage = false;
        }

        // technically we could go to 120, but we aren't going to chance it
        if (this.messagesSent.get() <= 115 || (skipQueue && this.messagesSent.get() <= 119)) {
            logger.trace("<- {}", message);
            ws.sendText(message.toString());
            this.messagesSent.getAndIncrement();
            return true;
        } else {
            if (!printedRateLimitMessage) {
                logger.warn(
                        "Ratelimit hit, this can be caused by a large number of messages being sent at once.");
                printedRateLimitMessage = true;
            }
        }
        return false;
    }

    protected void invalidate() {
        resumeGateWayUrl = null;
        sessionId = null;
        sentAuthInfo = false;
    }

    public WebSocketManager setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public boolean isReady() {
        return !initiating;
    }

    protected long calculateIdentifyBackoff() {
        long currentTime = System.currentTimeMillis();
        // calculate remaining backoff time since identify
        return currentTime - (connectTime + CONNECT_DELAY);
    }

    protected class ConnectEvent implements ISetUpSystem.SetUpSystemConnector {
        @Override
        public boolean isReconnect() {
            return false;
        }

        @Override
        public YDW.ShardInfo getShardInfo() {
            return ydwReg.getShardInfo();
        }

        @Override
        public void run(boolean lastInQueue) {
            connect();

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
