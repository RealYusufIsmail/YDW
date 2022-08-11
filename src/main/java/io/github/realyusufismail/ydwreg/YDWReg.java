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
package io.github.realyusufismail.ydwreg;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.realyusufismail.event.recieve.EventReceiver;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.websocket.system.ISetUpSystem;
import io.github.realyusufismail.websocket.system.SetUpSystem;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydw.application.commands.slash.builder.SlashCommandBuilder;
import io.github.realyusufismail.ydw.entities.*;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.event.Event;
import io.github.realyusufismail.ydw.event.events.GatewayPingEvent;
import io.github.realyusufismail.ydw.event.events.StatusChangeEvent;
import io.github.realyusufismail.ydwreg.application.commands.option.interaction.InteractionManager;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.SlashCommandBuilderReg;
import io.github.realyusufismail.ydwreg.control.GuildSetupControl;
import io.github.realyusufismail.ydwreg.exception.NotReadyException;
import io.github.realyusufismail.ydwreg.rest.RestApiHandler;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class YDWReg implements YDW {
    // logger
    public static final Logger logger = LoggerFactory.getLogger(YDWReg.class);
    @NotNull
    private final ObjectMapper mapper;
    @NotNull
    private final OkHttpClient okHttpClient;
    private final EventReceiver eventReceiver;
    private RestApiHandler rest;
    private WebSocketManager ws;
    private long ping;
    private long gatewayPing = -1;
    private SelfUser selfUser;
    private List<Guild> guilds;
    private boolean ready;
    private String token;
    private Long applicationId;
    private final ConcurrentMap<String, String> mdcContextMap;
    private ShardInfo shardInfo;
    private List<UnavailableGuild> unavailableGuilds;
    private List<AvailableGuild> availableGuilds;
    private YDW.Status status = Status.INITIALIZING;
    private final GuildSetupControl guildSetupControl;

    public YDWReg(@NotNull OkHttpClient okHttpClient, ConcurrentMap<String, String> mdcContextMap) {
        mapper = new ObjectMapper();
        this.okHttpClient = okHttpClient;
        eventReceiver = new EventReceiver();
        this.mdcContextMap = mdcContextMap == null ? new ConcurrentHashMap<>() : mdcContextMap;
        guildSetupControl = new GuildSetupControl(this);
    }

    public void handelEvent(Event event) {
        eventReceiver.eventReceivers.forEach(eventReceiver -> eventReceiver.onEvent(event));
    }

    @NotNull
    @Override
    public List<Guild> getGuilds() {
        return rest.getYDW().getGuilds();
    }

    @Override
    public Guild getGuild(long guildId) {
        return getRest().getYDWCaller().getGuild(guildId);
    }

    @Override
    public List<AvailableGuild> getAvailableGuilds() {
        return availableGuilds;
    }

    @Override
    public List<UnavailableGuild> getUnavailableGuilds() {
        return unavailableGuilds;
    }

    @Override
    public GuildSetupControl guildSetupControl() {
        return guildSetupControl;
    }

    @Override
    public @NotNull User getUser(long userId) {
        return getRest().getYDWCaller().getUser(userId);
    }

    @Override
    public Channel getChannel(long channelId) {
        return getRest().getYDWCaller().getChannel(channelId);
    }

    @Override
    public Category getCategory(long categoryId) {
        return getRest().getYDWCaller().getCategory(categoryId);
    }

    @Override
    public void login(String token, int gatewayIntents, String status, int largeThreshold,
            ActivityConfig activity, int corePoolSize) {
        logger.info("Received login request");
        this.token = token;
        ws = new WebSocketManager(this, token, gatewayIntents, status, largeThreshold, activity)
            .setCorePoolSize(corePoolSize);
    }

    @Override
    public void loginForRest(String token, @Nullable String guildId) {
        rest = new RestApiHandler(this, token, okHttpClient, guildId);
    }

    @Override
    public String getToken() {
        return token;
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
    public long getGatewayPing() {
        return gatewayPing;
    }

    public void setGatewayPing(long gatewayPing) {
        var oldGatewayPing = this.gatewayPing;
        this.gatewayPing = gatewayPing;
        handelEvent(new GatewayPingEvent(this, oldGatewayPing));
    }

    @NotNull
    @Override
    public RestApiHandler getRest() {
        return rest;
    }

    public void setRest(RestApiHandler rest) {
        this.rest = rest;
    }

    @Override
    public WebSocketManager getWebSocket() {
        return ws;
    }

    @Override
    public @NotNull SelfUser getSelfUser() {
        Optional<SelfUser> user = Optional.ofNullable(this.selfUser);
        return user.orElseThrow(() -> new NotReadyException("Self user"));
    }

    public void setSelfUser(@NotNull SelfUser selfUser) {
        this.selfUser = selfUser;
    }

    @Override
    public InteractionManager getInteractionManager() {
        return new InteractionManager(this);
    }

    @Override
    public void upsertCommands(List<SlashCommandBuilder> commands) {
        if (commands.isEmpty()) {
            // need to delete all commands
            getRest().getSlashCommandCaller().deleteAllCommands();
        } else {
            for (SlashCommandBuilder command : commands) {
                var reg = (SlashCommandBuilderReg) command;

                var caller = reg.caller();

                var guildOnly = reg.guildOnly();

                if (guildOnly) {
                    caller.upsertGuildCommand();
                } else {
                    caller.upsertGlobalCommand();
                }
            }
        }
    }

    @Override
    public void addEventAdapter(Object... eventAdapters) {
        for (Object eventAdapter : eventAdapters) {
            this.eventReceiver.addEventReceiver(eventAdapter);
        }
    }

    @Override
    public void removeEventAdapter(Object... eventAdapters) {
        for (Object eventAdapter : eventAdapters) {
            this.eventReceiver.removeEventReceiver(eventAdapter);
        }
    }

    @Override
    public YDW awaitReady() {
        while (!isReady()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // check if ready
                if (isReady()) {
                    logger.info("YDW is ready");
                } else {
                    logger.info("YDW is not ready, trying again");
                    awaitReady();
                }
            }
        }

        return this;
    }

    public void setStatus(YDW.Status status) {
        synchronized (this.status) {
            Status oldStatus = this.status;
            this.status = status;

            handelEvent(new StatusChangeEvent(this, status, oldStatus));
        }
    }

    @Override
    public YDW.Status getStatus() {
        return status;
    }

    public YDW awaitStatus(YDW.Status status, YDW.Status... others) throws InterruptedException {

        if (!status.isInit()) {
            throw new IllegalArgumentException("Status must be INITIALIZING or READY");
        }

        if (getStatus() == YDW.Status.CONNECTED)
            return this;

        List<YDW.Status> othersList = Arrays.asList(others);
        while (!getStatus().isInit() || getStatus().ordinal() < status.ordinal()) {
            if (getStatus() == YDW.Status.SHUTDOWN)
                throw new IllegalStateException("WebSocketManager is shutdown");
            else if (othersList.contains(getStatus()))
                return this;
            Thread.sleep(50);
        }
        return this;
    }

    @Override
    public ShardInfo getShardInfo() {
        return shardInfo == null ? ShardInfo.ONE_SHARD : shardInfo;
    }

    private synchronized boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
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

    public long getApplicationId() {
        if (applicationId == null)
            throw new NotReadyException("Application id");
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public ConcurrentMap<String, String> getMdcContextMap() {
        return mdcContextMap;
    }

    public ISetUpSystem getSetupSystem() {
        return new SetUpSystem();
    }

    public void setUnavailableGuilds(List<UnavailableGuild> unavailableGuilds) {
        this.unavailableGuilds = unavailableGuilds;
    }

    public void setAvailableGuilds(List<AvailableGuild> availableGuilds) {
        this.availableGuilds = availableGuilds;
    }
}
