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

    public RestApiHandler(@NotNull YDWReg ydw, @NotNull String token) {
        this.ydw = ydw;
        this.client = ydw.getHttpClient();
        this.guildId = ydw.getGuildId();
        this.token = token;

        // while ydw and token are null, we wait for them to be set
        while (ydw == null || token == null) {
            try {
                System.out.println("Waiting for ydw and token to be set");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final GuildCaller guildRestApi =
            new GuildCaller(getToken(), getYDW(), JSON, getHttpClient());

    private final UserCaller userCaller = new UserCaller(getToken(), getYDW(), getHttpClient());

    private final StickerCaller stickerCaller =
            new StickerCaller(getToken(), getYDW(), getHttpClient());

    private final EmojiCaller emojiCaller = new EmojiCaller(getToken(), getYDW(), getHttpClient());

    private final ChannelCaller channelCaller =
            new ChannelCaller(getToken(), getYDW(), getHttpClient());

    private final YDWCaller ydwCaller = new YDWCaller(getToken(), getYDW(), getHttpClient());

    private final SlashCommandCaller slashCommandCaller =
            new SlashCommandCaller(getToken(), getGuildId(), getYDW(), JSON, getHttpClient());

    private final MessageCaller messageCaller = new MessageCaller(getYDW(), JSON, getHttpClient());


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

    public @NotNull GuildCaller getGuildRestApi() {
        return guildRestApi;
    }

    public @NotNull UserCaller getUseCaller() {
        return userCaller;
    }

    public @NotNull MessageCaller getMessageCaller() {
        return messageCaller;
    }

    public @NotNull StickerCaller getStickerCaller() {
        return stickerCaller;
    }

    public @NotNull YDWCaller getYDWCaller() {
        return ydwCaller;
    }

    public @NotNull ChannelCaller getChannelCaller() {
        return channelCaller;
    }

    public @NotNull EmojiCaller getEmojiCaller() {
        return emojiCaller;
    }

    public @NotNull SlashCommandCaller getSlashCommandCaller() {
        return slashCommandCaller;
    }

    public RestApiHandler awaitStatus(RestApiStatus status) {
        if (status == this.status)
            return this;

        while (this.status != status) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    public RestApiHandler awaitReady() {
        return awaitStatus(RestApiStatus.CONNECTED);
    }

    public RestApiStatus getStatus() {
        return status;
    }

    public static void setStatus(RestApiStatus status) {
        RestApiHandler.status = status;
    }

    public @Nullable String getToken() {
        return token;
    }
}

