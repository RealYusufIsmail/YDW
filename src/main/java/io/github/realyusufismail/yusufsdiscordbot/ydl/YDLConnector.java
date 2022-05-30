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

package io.github.realyusufismail.yusufsdiscordbot.ydl;

import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.yusufsdiscordbot.ydl.activity.ActivityConfig;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.YDLReg;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.exception.*;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class YDLConnector {
    private final String token;
    private final List<Object> eventListener = new LinkedList<>();
    private String guildId;
    private int gatewayIntents;
    private String status = Status.ONLINE.getStatus();
    private ActivityConfig activity;
    private boolean compress = false;
    private int largeThreshold = 50;
    @Nullable
    private OkHttpClient client = null;

    private YDLConnector(String token, int intents) {
        this.token = token;
        this.gatewayIntents = 1 | intents;
    }

    private YDLConnector(long token, int intents) {
        this.token = Long.toString(token);
        this.gatewayIntents = 1 | intents;
    }

    /**
     * Used to register the bots token.
     *
     * @param token The bot token which is used to start the bot
     * @return The builder
     */
    @CheckReturnValue
    public static @NotNull YDLConnector setUpBot(String token) {
        return new YDLConnector(token, GateWayIntent.DEFAULT_INTENTS);
    }

    /**
     * Used to register the bots token.
     *
     * @param token The bot token which is used to start the bot
     * @return The builder
     */
    @CheckReturnValue
    public static @NotNull YDLConnector setUpBot(long token) {
        return new YDLConnector(token, GateWayIntent.DEFAULT_INTENTS);
    }

    /**
     * Used to register the bots token and also used to set the intents of the bot.
     *
     * @param token The bot token which is used to start the bot
     * @param intent The intents which the bot should have
     * @param intents The intents which the bot should have
     * @return The builder
     */
    @CheckReturnValue
    public static @NotNull YDLConnector setUpBot(String token, @NotNull GateWayIntent intent,
            GateWayIntent... intents) {
        return new YDLConnector(token, intent.getValue()).setGatewayIntents(intent, intents);
    }

    /**
     * Used to register the bots token and also used to set the intents of the bot.
     *
     * @param token The bot token which is used to start the bot
     * @param intent The intents which the bot should have
     * @param intents The intents which the bot should have
     * @return The builder
     */
    @CheckReturnValue
    public static @NotNull YDLConnector setUpBot(long token, @NotNull GateWayIntent intent,
            GateWayIntent... intents) {
        return new YDLConnector(token, intent.getValue()).setGatewayIntents(intent, intents);
    }

    @CheckReturnValue
    public @NotNull YDLConnector setGatewayIntents(@NotNull GateWayIntent intent,
            @NotNull GateWayIntent... intents) {
        this.gatewayIntents = intent.getValue();
        for (GateWayIntent i : intents) {
            this.gatewayIntents |= i.getValue();
        }
        return this;
    }

    @CheckReturnValue
    public @NotNull YDLConnector removeGatewayIntents(@NotNull GateWayIntent intent,
            @NotNull GateWayIntent... intents) {
        this.gatewayIntents = ~intent.getValue();
        for (GateWayIntent i : intents) {
            this.gatewayIntents &= ~i.getValue();
        }
        return this;
    }

    /**
     * the Gateway Intents you wish to receive
     *
     * @param gatewayIntents intents
     * @return YDLConnector
     * @see GateWayIntent the class for more info
     */
    @NotNull
    public YDLConnector setGatewayIntents(int gatewayIntents) {
        this.gatewayIntents = gatewayIntents;
        return this;
    }

    /**
     * the status you wish to set
     *
     * @param status status
     * @return YDLConnector
     * @see Status the class for more info
     */
    @NotNull
    public YDLConnector setStatus(@NotNull Status status) {
        this.status = status.getStatus();
        return this;
    }

    /**
     * whether this connection supports compression of packets default is false
     *
     * @param compress compress
     * @return YDLConnector
     */
    @NotNull
    public YDLConnector setCompress(boolean compress) {
        this.compress = compress;
        return this;
    }

    /**
     * value between 50 and 250, total number of members where the gateway will stop sending offline
     * members in the guild member list default is 50
     *
     * @param largeThreshold threshold
     * @return YDLConnector
     */
    @NotNull
    public YDLConnector setLargeThreshold(int largeThreshold) {
        this.largeThreshold = largeThreshold;
        return this;
    }

    /**
     * presence structure for initial presence information
     *
     * @param activity activity
     * @return YDLConnector
     */
    @NotNull
    public YDLConnector setActivity(ActivityConfig activity) {
        this.activity = activity;
        return this;
    }

    /**
     * Used to set the guild id for the bot
     */
    @NotNull
    public YDLConnector setGuildId(long guildId) {
        this.guildId = String.valueOf(guildId);
        return this;
    }

    /**
     * Used to set the guild id for the bot
     */
    @NotNull
    public YDLConnector setGuildId(String guildId) {
        this.guildId = guildId;
        return this;
    }

    /**
     * Used to set a custom http client
     * 
     * @param httpClient http client
     * @return YDLConnector
     */
    @NotNull
    private YDLConnector setHttpClient(OkHttpClient httpClient) {
        this.client = httpClient;
        return this;
    }

    public @NotNull YDL build() throws Exception {
        if (token == null || token.isEmpty() || token.isBlank()) {
            throw new InvalidTokenException("The token is invalid or empty or blank");
        }

        if (guildId == null || guildId.isEmpty() || guildId.isBlank()) {
            throw new InvalidGuildIdException("The guild id is invalid  or empty or blank");
        }

        if (gatewayIntents == 0 || gatewayIntents == -1) {
            throw new InvalidGateWayIntents("Gateway intents can not be empty or null");
        }

        if (status.isEmpty()) {
            throw new InvalidStatusException("Status can not be empty or null");
        }

        if (largeThreshold < 50 || largeThreshold > 250) {
            throw new InvalidThresholdException(
                    "Large threshold can not be less than 50 or greater than 250");
        }

        Optional<OkHttpClient> httpClient = Optional.ofNullable(this.client);

        if (httpClient.isEmpty()) {
            httpClient = Optional.of(new OkHttpClient());
        }

        YDLReg ydl = new YDLReg(httpClient.get());
        eventListener.forEach(ydl::setEventListeners);
        ydl.setToken(token);
        ydl.setGuildId(guildId);
        ydl.login(token, gatewayIntents, status, largeThreshold, compress, activity);
        return ydl;
    }
}
