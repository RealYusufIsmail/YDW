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
import io.github.realyusufismail.event.recieve.EventReceiver;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.ydw.GateWayIntent;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.SelfUser;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydw.event.Event;
import io.github.realyusufismail.ydwreg.application.commands.option.interaction.InteractionManager;
import io.github.realyusufismail.ydwreg.rest.RestApiHandler;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

public class YDWReg implements YDW {

    // logger
    public static final Logger logger = LoggerFactory.getLogger(YDWReg.class);
    @NotNull
    private final ObjectMapper mapper;
    @NotNull
    private final OkHttpClient okHttpClient;
    private final ExecutorService executorService;
    private final EventReceiver eventReceiver;
    private RestApiHandler rest;
    private WebSocketManager ws;
    private long ping;
    private long gatewayPing;
    private SelfUser selfUser;
    private List<Guild> guilds;
    private Boolean resumable;
    private boolean ready;
    private String guildId;
    private String token;
    private Long applicationId;

    public YDWReg(@NotNull OkHttpClient okHttpClient, ExecutorService executorService) {
        this.executorService = executorService;
        mapper = new ObjectMapper();
        this.okHttpClient = okHttpClient;
        eventReceiver = new EventReceiver();
    }

    public void handelEvent(Event event) {
        eventReceiver.eventReceivers.forEach(eventReceiver -> eventReceiver.onEvent(event));

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

    public void setRest(RestApiHandler rest) {
        this.rest = rest;
    }

    @Override
    public WebSocketManager getWebSocket() {
        return ws;
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
    public InteractionManager getInteractionManager() {
        return new InteractionManager(this);
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
        return ready ? this : awaitReady(this);
    }

    private YDW awaitReady(YDWReg ydwReg) {
        if (ydwReg.isReady()) {
            return ydwReg;
        } else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return awaitReady(ydwReg);
        }
    }

    private boolean isReady() {
        return ready;
    }

    public YDW setReady(boolean ready) {
        this.ready = ready;
        return this;
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
            throw new IllegalStateException("Application id is not set");
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
