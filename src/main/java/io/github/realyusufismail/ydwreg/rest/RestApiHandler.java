package io.github.realyusufismail.ydwreg.rest;

import io.github.realyusufismail.ydwreg.YDWReg;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

public class RestApiHandler {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final YDWReg ydw;
    private final OkHttpClient client;  // This is the client that will be used to make the requests to the API

    public RestApiHandler(@NotNull YDWReg ydw) {
        this.ydw = ydw;
        this.client = ydw.getHttpClient();
        ydw.setRest(this);
    }

    //private final GuildCaller guildRestApi = new GuildCaller(getYDW(), JSON, getHttpClient());
    //private final UserCaller userCaller = new UserCaller(getYDW(), getHttpClient());
    //private final StickerCaller stickerCaller = new StickerCaller(getYDW(), getHttpClient());
    //private final EmojiCaller emojiCaller = new EmojiCaller(getYDW(), getHttpClient());
    //private final ChannelCaller channelCaller = new ChannelCaller(getYDW(), getHttpClient());
    //private final YDWCaller ydwCaller = new YDWCaller(getYDW(), getHttpClient());
    //private final SlashCommandCaller slashCommandCaller =
            //new SlashCommandCaller(getToken(), getYDW(), JSON, getHttpClient());
    //private final MessageCaller messageCaller = new MessageCaller(getYDW(), JSON, getHttpClient());
}
