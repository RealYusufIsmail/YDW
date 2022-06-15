package io.github.realyusufismail.ydwreg.rest;

import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.exception.InvalidStatusException;
import io.github.realyusufismail.ydwreg.rest.callers.*;
import io.github.yusufsdiscordbot.config.Config;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.IOException;

public class RestApiHandler {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private YDWReg ydw;
    private OkHttpClient client; // This is the client that will be used to make the requests
                                 // to the API

    private final String guildId;

    private static RestApiStatus status = RestApiStatus.INITIALISING;

    private final String token;

    // logger
    private static final Logger logger = LoggerFactory.getLogger(RestApiHandler.class);

    public RestApiHandler(@NotNull YDWReg ydw, String token, OkHttpClient client) {
        this.ydw = ydw;
        this.client = client;
        this.token = token;
        this.guildId = ydw.getGuildId();

        /*
         * // while ydw and token are null, we wait for them to be set while (ydw == null || token
         * == null) { try { System.out.println("Waiting for ydw and token to be set");
         * Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); } }
         * 
         */
    }



    public OkHttpClient getHttpClient() {
        if (client == null) {
            client = new OkHttpClient();
        }
        return client;
    }

    public YDWReg getYDW() {
        if (ydw == null) {
            ydw = new YDWReg(getHttpClient());
        }
        return ydw;
    }

    public String getGuildId() {
        return guildId;
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
        return new ChannelCaller(token, ydw, client);
    }

    public @NotNull EmojiCaller getEmojiCaller() {
        return new EmojiCaller(token, ydw, client);
    }

    public @NotNull SlashCommandCaller getSlashCommandCaller() {
        return new SlashCommandCaller(token, guildId, ydw, JSON, client);
    }
}

