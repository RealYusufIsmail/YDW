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

package io.github.realyusufismail.ydw;

import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.exception.*;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class YDWConnector {
    private final String token;
    private String guildId;
    private int gatewayIntents;
    private String status = Status.ONLINE.getStatus();
    private ActivityConfig activity;
    private boolean compress = false;
    private int largeThreshold = 50;
    @Nullable
    private OkHttpClient client = null;

    private YDWConnector(String token, int intents) {
        this.token = token;
        this.gatewayIntents = 1 | intents;
    }

    private YDWConnector(long token, int intents) {
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
    public static @NotNull YDWConnector setUpBot(String token) {
        return new YDWConnector(token, GateWayIntent.DEFAULT_INTENTS);
    }

    /**
     * Used to register the bots token.
     *
     * @param token The bot token which is used to start the bot
     * @return The builder
     */
    @CheckReturnValue
    public static @NotNull YDWConnector setUpBot(long token) {
        return new YDWConnector(token, GateWayIntent.DEFAULT_INTENTS);
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
    public static @NotNull YDWConnector setUpBot(String token, @NotNull GateWayIntent intent,
            GateWayIntent... intents) {
        return new YDWConnector(token, intent.getValue()).setGatewayIntents(intent, intents);
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
    public static @NotNull YDWConnector setUpBot(long token, @NotNull GateWayIntent intent,
            GateWayIntent... intents) {
        return new YDWConnector(token, intent.getValue()).setGatewayIntents(intent, intents);
    }

    @CheckReturnValue
    public @NotNull YDWConnector setGatewayIntents(@NotNull GateWayIntent intent,
            @NotNull GateWayIntent... intents) {
        this.gatewayIntents = intent.getValue();
        for (GateWayIntent i : intents) {
            this.gatewayIntents |= i.getValue();
        }
        return this;
    }

    @CheckReturnValue
    public @NotNull YDWConnector removeGatewayIntents(@NotNull GateWayIntent intent,
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
     * @return ydwConnector
     * @see GateWayIntent the class for more info
     */
    @NotNull
    public YDWConnector setGatewayIntents(int gatewayIntents) {
        this.gatewayIntents = gatewayIntents;
        return this;
    }

    /**
     * the status you wish to set
     *
     * @param status status
     * @return ydwConnector
     * @see Status the class for more info
     */
    @NotNull
    public YDWConnector setStatus(@NotNull Status status) {
        this.status = status.getStatus();
        return this;
    }

    /**
     * whether this connection supports compression of packets default is false
     *
     * @param compress compress
     * @return ydwConnector
     */
    @NotNull
    public YDWConnector setCompress(boolean compress) {
        this.compress = compress;
        return this;
    }

    /**
     * value between 50 and 250, total number of members where the gateway will stop sending offline
     * members in the guild member list default is 50
     *
     * @param largeThreshold threshold
     * @return ydwConnector
     */
    @NotNull
    public YDWConnector setLargeThreshold(int largeThreshold) {
        this.largeThreshold = largeThreshold;
        return this;
    }

    /**
     * presence structure for initial presence information
     *
     * @param activity activity
     * @return ydwConnector
     */
    @NotNull
    public YDWConnector setActivity(ActivityConfig activity) {
        this.activity = activity;
        return this;
    }

    /**
     * Used to set the guild id for the bot. This will be used for guild only commands.
     */
    @NotNull
    public YDWConnector setGuildId(long guildId) {
        this.guildId = String.valueOf(guildId);
        return this;
    }

    /**
     * Used to set the guild id for the bot. This will be used for guild only commands.
     */
    @NotNull
    public YDWConnector setGuildId(String guildId) {
        this.guildId = guildId;
        return this;
    }

    /**
     * Used to set a custom http client
     * 
     * @param httpClient http client
     * @return ydwConnector
     */
    @NotNull
    private YDWConnector setHttpClient(OkHttpClient httpClient) {
        this.client = httpClient;
        return this;
    }

    public @NotNull YDW build() throws Exception {
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

        YDWReg ydw = new YDWReg(httpClient.get());
        ydw.setToken(token);
        ydw.setGuildId(guildId);
        ydw.login(token, gatewayIntents, status, largeThreshold, compress, activity);
        return ydw;
    }
}
