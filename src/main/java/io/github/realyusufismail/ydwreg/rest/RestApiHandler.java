package io.github.realyusufismail.ydwreg.rest;

import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.rest.callers.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class RestApiHandler {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private YDWReg ydw;
    private OkHttpClient client; // This is the client that will be used to make the requests
                                 // to the API

    private final String guildId;

    private final String token;

    private RestApiStatus status = RestApiStatus.INITIALISING;

    public RestApiHandler(@NotNull YDWReg ydw) {
        this.ydw = ydw;
        this.client = ydw.getHttpClient();
        ydw.setRest(this);
        this.token = ydw.getToken();
        this.guildId = ydw.getGuildId();

        // after 1000ms, set the status to ready
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            status = RestApiStatus.CONNECTED;
        }).start();
    }

    private final GuildCaller guildRestApi = new GuildCaller(getYDW(), JSON, getHttpClient());

    private final UserCaller userCaller = new UserCaller(getYDW(), getHttpClient());

    private final StickerCaller stickerCaller = new StickerCaller(getYDW(), getHttpClient());

    private final EmojiCaller emojiCaller = new EmojiCaller(getYDW(), getHttpClient());

    private final ChannelCaller channelCaller = new ChannelCaller(getYDW(), getHttpClient());

    private final YDWCaller ydwCaller = new YDWCaller(getYDW(), getHttpClient());

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

    public String getToken() {
        return token;
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

    public void setStatus(RestApiStatus status) {
        this.status = status;
    }
}

