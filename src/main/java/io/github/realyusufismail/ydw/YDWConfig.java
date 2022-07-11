package io.github.realyusufismail.ydw;

import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.exception.InvalidStatusException;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class YDWConfig {
    private final String token;
    private final int gatewayIntents;

    private String status;
    private int largeThreshold = 250;
    private ActivityConfig activity = null;

    private OkHttpClient client;

    private String guildId;

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

    public YDW build() throws Exception {

        if (token == null || token.isEmpty()) {
            throw new InvalidStatusException("Token is null");
        }

        OkHttpClient client;
        client = Objects.requireNonNullElseGet(this.client, OkHttpClient::new);
        YDWReg ydw = new YDWReg(client);
        ydw.loginForRest(token, guildId);
        ydw.login(token, gatewayIntents, status, largeThreshold, activity);
        return ydw;
    }
}
