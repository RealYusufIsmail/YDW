package io.github.realyusufismail.ydw;

import com.google.errorprone.annotations.CheckReturnValue;
import com.neovisionaries.ws.client.WebSocketException;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.exception.InvalidStatusException;
import io.github.realyusufismail.ydwreg.rest.RestApiHandler;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class YDwConfig {
    private final String token;
    private final int gatewayIntents;

    private String status;
    private int largeThreshold = 250;
    private ActivityConfig activity = null;

    private OkHttpClient client;


    private YDwConfig(String token, int gatewayIntents) {
        this.token = token;
        this.gatewayIntents = 1 | gatewayIntents;
    }

    @NotNull
    public static YDwConfig setDefault(String token) {
        return new YDwConfig(token, GateWayIntent.DEFAULT_INTENTS);
    }

    /**
     * Used to register the bots token and also used to set the intents of the bot.
     *
     * @param token The bot token which is used to start the bot
     * @param intent The intents which the bot should have
     * @return The builder
     */
    @CheckReturnValue
    public static @NotNull YDwConfig setUpBot(String token, @NotNull GateWayIntent intent) {
        return new YDwConfig(token, intent.getValue());
    }

    /**
     * the status you wish to set
     *
     * @param status status
     * @return ydwConnector
     * @see Status the class for more info
     */
    @NotNull
    public YDwConfig setStatus(@NotNull Status status) {
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
    public YDwConfig setLargeThreshold(int largeThreshold) {
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
    public YDwConfig setActivity(ActivityConfig activity) {
        this.activity = activity;
        return this;
    }

    /**
     * the client used to connect to the websocket
     * 
     * @param client client
     *
     * @return ydwConnector
     */
    @NotNull
    public YDwConfig setClient(OkHttpClient client) {
        this.client = client;
        return this;
    }


    public YDW build() throws WebSocketException, IOException, InvalidStatusException {

        if (token == null || token.isEmpty()) {
            throw new InvalidStatusException("Token is null");
        }

        OkHttpClient client;

        if (this.client == null) {
            client = new OkHttpClient();
        } else {
            client = this.client;
        }

        YDWReg ydw = new YDWReg(client);
        new RestApiHandler(ydw);
        new WebSocketManager(ydw, token, gatewayIntents, status, largeThreshold, activity);
        return ydw;
    }
}
