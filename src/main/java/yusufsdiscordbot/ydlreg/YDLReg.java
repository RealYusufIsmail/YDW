/*
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 *
 * YDL Copyright (C) 2022 - future YusufsDiscordbot This program comes with ABSOLUTELY NO WARRANTY
 *
 * This is free software, and you are welcome to redistribute it under certain conditions
 */

package yusufsdiscordbot.ydlreg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.websocket.core.GateWayIntent;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yusufsdiscordbot.ydl.Activity;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.Guild;
import yusufsdiscordbot.ydl.entities.SelfUser;
import yusufsdiscordbot.ydl.entities.User;
import yusufsdiscordbot.ydl.entities.guild.Channel;
import yusufsdiscordbot.ydlreg.rest.RestApiHandler;
import yusufsdiscordbot.ydlreg.util.Verify;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class YDLReg implements YDL {

    // logger
    public static final Logger logger = LoggerFactory.getLogger(YDLReg.class);
    private final RestApiHandler rest;
    private final ObjectMapper mapper;
    private WebSocketManager ws;
    private long ping;
    private long sequenceNumber;
    private long gatewayPing;
    private SelfUser selfUser;

    private final OkHttpClient client;

    public YDLReg(OkHttpClient client) {
        rest = new RestApiHandler(this);
        mapper = new ObjectMapper();
        this.client = client == null ? new OkHttpClient() : client;
    }

    @Override
    public Guild getGuild(long guildId) {
        return getRest().getYDLCaller().getGuild(guildId);
    }

    @Override
    public Set<String> getUnavailableGuilds() {
        return null;
    }

    @Override
    public boolean isSpecifiedGuildAvailable(long guildId) {
        return false;
    }

    @Override
    public @NotNull User getUser(long userId) {
        return getRest().getYDLCaller().getUser(userId);
    }

    @Override
    public Channel getChannel(long channelId) {
        return getRest().getYDLCaller().getChannel(channelId);
    }

    public boolean isGatewayIntents(@NotNull GateWayIntent intents) {
        int raw = intents.getValue();
        return (ws.getGatewayIntents() & raw) == raw;
    }


    @Override
    public void login(String token, int gatewayIntents, String status, int largeThreshold,
            boolean compress, Activity activity) throws IOException, WebSocketException {
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
    public void removeEventListeners(@NotNull Object... eventListener) {
        Verify.checkIfNull(eventListener, "Event listener cannot be null");
        for (Object listener : eventListener)
            //eventHandler.unregister(listener);
            logger.warn("Event listener not implemented");
    }

    @Override
    public List<Object> getEventListeners() {
        return null; //eventHandler.getEventListeners();
    }

    @Override
    public void setEventListeners(@NotNull Object... eventListener) {
        Verify.checkIfNull(eventListener, "The event listener cannot be null");
        for (Object listener : eventListener)
            //eventHandler.register(listener);
            logger.warn("Event listener not implemented");

    }

    @Override
    public long getGatewayPing() {
        return gatewayPing;
    }

    public void setGatewayPing(long gatewayPing) {
        var oldGatewayPing = this.gatewayPing;
        this.gatewayPing = gatewayPing;
        //apiHandler(new GatewayPingEvent(this, oldGatewayPing));
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
        Verify.checkIfNull(selfUser, "The session is not ready yet");
        return selfUser;
    }

    public void setSelfUser(@NotNull SelfUser selfUser) {
        this.selfUser = selfUser;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public OkHttpClient getHttpClient() {
        return client;
    }
}
