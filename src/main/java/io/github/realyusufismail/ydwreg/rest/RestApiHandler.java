package io.github.realyusufismail.ydwreg.rest;

import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.rest.callers.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

public class RestApiHandler {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    // logger
    private static final Logger logger = LoggerFactory.getLogger(RestApiHandler.class);
    private static final RestApiStatus status = RestApiStatus.INITIALISING;
    // to the API
    private final String guildId;
    private final String token;
    private final YDWReg ydw;
    private final OkHttpClient client; // This is the client that will be used to make the requests

    public RestApiHandler(@NotNull YDWReg ydw, String token, OkHttpClient client,
            @Nullable String guildId) {
        this.ydw = ydw;
        this.client = client;
        this.token = token;
        this.guildId = guildId;

        /*
         * // while ydw and token are null, we wait for them to be set while (ydw == null || token
         * == null) { try { System.out.println("Waiting for ydw and token to be set");
         * Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); } }
         *
         */
    }


    public OkHttpClient getHttpClient() {
        return client;
    }

    public YDWReg getYDW() {
        return ydw;
    }

    public @NotNull GuildCaller getGuildCaller() {
        return new GuildCaller(token, ydw, JSON, client);
    }

    public @NotNull UserCaller getUseCaller() {
        return new UserCaller(token, ydw, client);
    }

    public @NotNull MessageCaller getMessageCaller() {
        return new MessageCaller(ydw, JSON, client);
    }

    public @NotNull StickerCaller getStickerCaller() {
        return new StickerCaller(token, ydw, client);
    }

    public @NotNull YDWCaller getYDWCaller() {
        return new YDWCaller(token, ydw, client);
    }

    public @NotNull ChannelCaller getChannelCaller() {
        return new ChannelCaller(token, ydw, client, JSON);
    }

    public @NotNull EmojiCaller getEmojiCaller() {
        return new EmojiCaller(token, ydw, client);
    }

    public @NotNull SlashCommandCaller getSlashCommandCaller() {
        return new SlashCommandCaller(token, guildId, ydw, JSON, client);
    }
}

