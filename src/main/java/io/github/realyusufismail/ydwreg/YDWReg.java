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
import com.neovisionaries.ws.client.WebSocketException;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.websocket.event.Event;
import io.github.realyusufismail.websocket.event.EventInterface;
import io.github.realyusufismail.ydw.GateWayIntent;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydw.entities.*;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydwreg.rest.RestApiHandler;
import io.github.realyusufismail.ydwreg.util.Verify;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class YDWReg implements YDW {

    // logger
    public static final Logger logger = LoggerFactory.getLogger(YDWReg.class);
    @NotNull
    private final RestApiHandler rest;
    @NotNull
    private final ObjectMapper mapper;
    private WebSocketManager ws;
    private long ping;
    private long sequenceNumber;
    private long gatewayPing;
    private SelfUser selfUser;
    @NotNull
    private final OkHttpClient client;

    private List<Guild> guilds;

    private List<UnavailableGuild> unavailableGuilds;

    private List<AvailableGuild> availableGuilds;

    private Boolean resumable;

    private Boolean reconnected;

    private Boolean resumed;

    public YDWReg(@Nullable OkHttpClient client) {
        rest = new RestApiHandler(this);
        mapper = new ObjectMapper();
        this.client = client == null ? new OkHttpClient() : client;
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
            boolean compress, ActivityConfig activity) throws IOException, WebSocketException {
        ws = new WebSocketManager(this, token, gatewayIntents, status, largeThreshold, compress,
                activity);
    }


    @Override
    public void setToken(String token) {
        rest.setToken(token);
    }

    @Override
    public void setGuildId(String guildId) {
        rest.setGuildId(guildId);
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
    public @NotNull SelfUser getSelfUser() {
        Optional<SelfUser> user = Optional.ofNullable(this.selfUser);
        if (user.isPresent())
            return user.get();
        else
            throw new IllegalStateException("Self user is not set");
    }

    public void setSelfUser(@NotNull SelfUser selfUser) {
        this.selfUser = selfUser;
    }

    @Override
    public <EventName extends Event> YDW onEvent(@NotNull EventName event,
            EventInterface<EventName> eventInterface) {
        if (!event.getClass().isAssignableFrom(Event.class))
            throw new IllegalArgumentException(
                    "Event class must be assignable from event interface");


        return this;
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

    public void setResumed(boolean b) {
        this.resumed = b;
    }

    public void setReconnected(boolean b) {
        this.reconnected = b;
    }


    public ObjectMapper getMapper() {
        return mapper;
    }

    public OkHttpClient getHttpClient() {
        return client;
    }

    public Logger getLogger() {
        return logger;
    }
}
