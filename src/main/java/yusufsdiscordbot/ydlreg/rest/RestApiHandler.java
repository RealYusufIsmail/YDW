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

package yusufsdiscordbot.ydlreg.rest;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.YDLInfo;
import yusufsdiscordbot.ydlreg.YDLReg;
import yusufsdiscordbot.ydlreg.rest.callers.*;
import yusufsdiscordbot.ydlreg.rest.old.ApplicationCommandsRestApi;
import yusufsdiscordbot.ydlreg.rest.old.MessageRestApi;

import java.io.IOException;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class RestApiHandler {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final Logger logger = LoggerFactory.getLogger(RestApiHandler.class);
    private final YDL ydl;
    private final String restLink = YDLInfo.DISCORD_REST_LINK;
    private final GuildCaller guildRestApi = new GuildCaller(getYDL(), JSON);
    private final UserCaller userCaller = new UserCaller(getYDL());
    private final StickerCaller stickerCaller = new StickerCaller((YDLReg) getYDL());
    private final EmojiCaller emojiCaller = new EmojiCaller(getYDL());
    private final ChannelCaller channelCaller = new ChannelCaller(getYDL());
    private final YDLCaller ydlCaller = new YDLCaller(getYDL());
    private final SlashCommandCaller slashCommandCaller = new SlashCommandCaller(getYDL());
    @NotNull
    OkHttpClient client = new OkHttpClient();
    private boolean isEphemeral = false;
    private boolean isTTS = false;
    private boolean isMentionable = false;
    private final MessageRestApi messageRestApi =
            new MessageRestApi(restLink, JSON, client, isMentionable, isTTS);
    private boolean isGuildOnlyCommand = false;
    private String guildId;
    private String token = "";
    private final ApplicationCommandsRestApi applicationCommandsRestApi =
            new ApplicationCommandsRestApi(restLink, JSON, client, token, guildId);

    public RestApiHandler(YDL ydl) {
        this.ydl = ydl;
    }

    // I want a queue system were once the queue is called it executes the method
    public <T> void queue(@NotNull Request request, @NotNull Consumer<? super T> success,
            @NotNull Consumer<? super Throwable> failure) {
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                failure.accept(e);
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response)
                    throws IOException {
                success.accept(null);
            }
        });
    }

    public void setGuildOnly(boolean isGuildOnly) {
        this.isGuildOnlyCommand = isGuildOnly;
    }

    public void setEphemeral(boolean ephemeral) {
        isEphemeral = ephemeral;
    }

    public void setTTS(boolean tts) {
        isTTS = tts;
    }

    public void setMentionable(boolean mentionable) {
        isMentionable = mentionable;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public @NotNull GuildCaller getGuildRestApi() {
        return guildRestApi;
    }

    public @NotNull UserCaller getUseCaller() {
        return userCaller;
    }

    public @NotNull ApplicationCommandsRestApi getApplicationCommandsRestApi() {
        return applicationCommandsRestApi;
    }

    public @NotNull MessageRestApi getMessageRestApi() {
        return messageRestApi;
    }

    public @NotNull StickerCaller getStickerCaller() {
        return stickerCaller;
    }

    public @NotNull YDLCaller getYDLCaller() {
        return ydlCaller;
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

    public YDL getYDL() {
        return ydl;
    }
}
