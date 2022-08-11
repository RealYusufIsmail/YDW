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
package io.github.realyusufismail.ydw;

import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.exception.InvalidStatusException;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

public class YDWConfig {
    private final String token;
    private final int gatewayIntents;
    private String status;
    private int largeThreshold = 250;
    private ActivityConfig activity = null;
    private OkHttpClient client;
    // used for resuming
    private ConcurrentMap<String, String> mdcContextMap;
    private String guildId;
    private int corePoolSize = 1;


    private YDWConfig(String token, int gatewayIntents) {
        this.token = token;
        this.gatewayIntents = 1 | gatewayIntents;
    }

    @NotNull
    public static YDWConfig setDefault(String token) {
        return new YDWConfig(token, GateWayIntent.DEFAULT_INTENTS);
    }

    /**
     * Used to register the bots token and also used to set the intents of the bot.
     *
     * @param token The bot token which is used to start the bot
     * @param intent The intents which the bot should have
     * @return The builder
     */
    @CheckReturnValue
    public static @NotNull YDWConfig setUpBot(String token, @NotNull GateWayIntent intent) {
        return new YDWConfig(token, intent.getValue());
    }

    /**
     * the status you wish to set
     *
     * @param status status
     * @return ydwConnector
     * @see Status the class for more info
     */
    @NotNull
    public YDWConfig setStatus(@NotNull Status status) {
        this.status = status.getStatus();
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
    public YDWConfig setLargeThreshold(int largeThreshold) {
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
    public YDWConfig setActivity(ActivityConfig activity) {
        this.activity = activity;
        return this;
    }

    /**
     * the client used to connect to the websocket
     *
     * @param client client
     * @return ydwConnector
     */
    @NotNull
    public YDWConfig setClient(OkHttpClient client) {
        this.client = client;
        return this;
    }

    /**
     * the guild id for global Commands
     *
     * @param guildId guildId
     */
    public YDWConfig setGuildId(String guildId) {
        this.guildId = guildId;
        return this;
    }

    /**
     * Used to set the core pool size of the thread pool.
     *
     * @param corePoolSize corePoolSize
     */
    public YDWConfig setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    /**
     * Used to set the mdc context map.
     *
     * @param mdcContextMap mdcContextMap
     */
    public YDWConfig setMdcContextMap(ConcurrentMap<String, String> mdcContextMap) {
        this.mdcContextMap = mdcContextMap;
        return this;
    }

    public YDW build() throws Exception {

        if (token == null || token.isEmpty()) {
            throw new InvalidStatusException("Token is null");
        }

        OkHttpClient client;
        client = Objects.requireNonNullElseGet(this.client, OkHttpClient::new);
        YDWReg ydw = new YDWReg(client, mdcContextMap);
        ydw.loginForRest(token, guildId);
        ydw.login(token, gatewayIntents, status, largeThreshold, activity, corePoolSize);
        return ydw;
    }
}
