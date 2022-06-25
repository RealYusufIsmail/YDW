/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
 */

package io.github.realyusufismail.ydwreg;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.realyusufismail.event.Client;
import io.github.realyusufismail.handler.EventHandler;
import io.github.realyusufismail.handler.MainHandlerEvent;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.ydw.event.BasicEvent;
import io.github.realyusufismail.ydw.event.events.ApiStatusChangeEvent;
import io.github.realyusufismail.ydw.GateWayIntent;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydw.entities.*;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydwreg.application.commands.option.interaction.InteractionManager;
import io.github.realyusufismail.ydwreg.entities.guild.manager.GuildManager;
import io.github.realyusufismail.ydwreg.rest.RestApiHandler;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class YDWReg implements YDW {

    // logger
    public static final Logger logger = LoggerFactory.getLogger(YDWReg.class);
    private RestApiHandler rest;
    @NotNull
    private final ObjectMapper mapper;
    private WebSocketManager ws;
    private long ping;
    private long sequenceNumber;
    private long gatewayPing;
    private SelfUser selfUser;
    @NotNull
    private final OkHttpClient okHttpClient;

    private List<Guild> guilds;

    private List<UnavailableGuild> unavailableGuilds;

    private List<AvailableGuild> availableGuilds;

    private Boolean resumable;

    private Boolean reconnected;

    private Boolean resumed;

    private boolean ready;

    private ApiStatus status = ApiStatus.STARTING;
    private String guildId;
    private String token;

    private Long applicationId;

    private final ExecutorService executorService;

    private final EventHandler eventHandler;

    private final Client client;

    public YDWReg(@NotNull OkHttpClient okHttpClient, ExecutorService executorService) {
        this.executorService = executorService;
        mapper = new ObjectMapper();
        this.okHttpClient = okHttpClient;
        this.client = new Client();
        eventHandler = new EventHandler(new MainHandlerEvent(), executorService);
    }

    public void handelEvent(BasicEvent event) {
        eventHandler.send(event);
    }

    @Override
    public YDW awaitStatus(ApiStatus status) throws InterruptedException {
        if (!status.isInitialized()) {
            throw new IllegalArgumentException("Status is not part of the initialising state");
        }

        if (this.status == status)
            return this;
        List<ApiStatus> statuses = List.of(status);
        while (!getStatus().isInitialized() || getStatus().ordinal() < status.ordinal()) {
            if (getStatus() == ApiStatus.SHUT_DOWN)
                throw new IllegalStateException("Bot is shut down");
            else if (statuses.contains(getStatus()))
                return this;
            Thread.sleep(100);
        }
        return this;
    }

    public ApiStatus getStatus() {
        return status;
    }

    @NotNull
    @Override
    public List<Guild> getGuilds() {
        return guilds;
    }

    public void setGuilds(List<Guild> guilds) {
        this.guilds = guilds;
    }

    @Override
    public Guild getGuild(long guildId) {
        return getRest().getYDWCaller().getGuild(guildId);
    }

    @Override
    public List<UnavailableGuild> getUnavailableGuilds() {
        return unavailableGuilds;
    }

    public void setUnavailableGuilds(List<UnavailableGuild> unavailableGuilds) {
        this.unavailableGuilds = unavailableGuilds;
    }

    @Override
    public List<AvailableGuild> getAvailableGuilds() {
        return availableGuilds;
    }

    public void setAvailableGuilds(List<AvailableGuild> availableGuilds) {
        this.availableGuilds = availableGuilds;
    }

    @Override
    public boolean isSpecifiedGuildAvailable(long guildId) {
        return false;
    }

    @Override
    public @NotNull User getUser(long userId) {
        return getRest().getYDWCaller().getUser(userId);
    }

    @Override
    public Channel getChannel(long channelId) {
        return getRest().getYDWCaller().getChannel(channelId);
    }

    public boolean isGatewayIntents(@NotNull GateWayIntent intents) {
        int raw = intents.getValue();
        return (ws.getGatewayIntents() & raw) == raw;
    }


    @Override
    public void login(String token, int gatewayIntents, String status, int largeThreshold,
            ActivityConfig activity) throws Exception {
        logger.info("Received login request");
        this.token = token;
        ws = new WebSocketManager(this, token, gatewayIntents, status, largeThreshold, activity);
    }

    @Override
    public void loginForRest(String token, @Nullable String guildId) {
        rest = new RestApiHandler(this, token, okHttpClient, guildId);
    }

    @Override
    public String getToken() {
        return token;
    }

    public String getGuildId() {
        return guildId;
    }

    @Override
    public long getPing() {
        return ping;
    }

    @Override
    public void setPing(long ping) {
        this.ping = ping;
    }

    @Override
    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    @Override
    public long getGatewayPing() {
        return gatewayPing;
    }

    public void setGatewayPing(long gatewayPing) {
        var oldGatewayPing = this.gatewayPing;
        this.gatewayPing = gatewayPing;
        // apiHandler(new GatewayPingEvent(this, oldGatewayPing));
    }

    @NotNull
    @Override
    public RestApiHandler getRest() {
        return rest;
    }

    @Override
    public WebSocketManager getWebSocket() {
        return ws;
    }

    @Override
    public int getMaxReconnectDelay() {
        return 0;
    }

    @Override
    public boolean needsToAutoReconnect() {
        return false;
    }

    public boolean isSelfUserThere() {
        return selfUser != null;
    }

    @Override
    public @NotNull SelfUser getSelfUser() throws InterruptedException {
        Optional<SelfUser> user = Optional.ofNullable(this.selfUser);
        return user.orElseThrow(() -> new IllegalStateException("Self user is not set"));
    }

    public void setSelfUser(@NotNull SelfUser selfUser) {
        this.selfUser = selfUser;
    }

    public Boolean isResumable() {
        return resumable;
    }

    public void setResumable(Boolean resumable) {
        this.resumable = resumable;
    }

    @Override
    public boolean isResumed() {
        return resumed;
    }

    @Override
    public boolean hasReconnected() {
        return reconnected;
    }

    @Override
    public InteractionManager getInteractionManager() {
        return new InteractionManager(this);
    }

    @Override
    public GuildManager getGuildManager() {
        return new GuildManager(this);
    }

    public void setResumed(boolean b) {
        this.resumed = b;
    }

    public void setReconnected(boolean b) {
        this.reconnected = b;
    }

    public void setApiStatus(YDW.ApiStatus apiStatus) {
        synchronized (this.status) {
            ApiStatus oldStatus = this.status;
            this.status = apiStatus;

            handelEvent(new ApiStatusChangeEvent(this, oldStatus, apiStatus));
        }
    }

    public void setRest(RestApiHandler rest) {
        this.rest = rest;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public OkHttpClient getHttpClient() {
        return okHttpClient;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public long getApplicationId() {
        if (applicationId == null)
            throw new IllegalStateException("Application id is not set");
        return applicationId;
    }

    @Override
    public <Event extends BasicEvent> void onEvent(Class<Event> eventClass,
            Consumer<Event> consumer) {
        eventHandler.register(eventClass);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
