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

package io.github.realyusufismail.ydwreg.rest;

import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.rest.callers.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.net.http.HttpClient;

@SuppressWarnings("unused")
public class RestApiHandler {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private YDWReg ydw;

    private final OkHttpClient client;

    private String guildId;
    private String token = "";
    private final GuildCaller guildRestApi = new GuildCaller(getYDW(), JSON, getHttpClient());
    private final UserCaller userCaller = new UserCaller(getYDW(), getHttpClient());
    private final StickerCaller stickerCaller = new StickerCaller(getYDW(), getHttpClient());
    private final EmojiCaller emojiCaller = new EmojiCaller(getYDW(), getHttpClient());
    private final ChannelCaller channelCaller = new ChannelCaller(getYDW(), getHttpClient());
    private final YDWCaller ydwCaller = new YDWCaller(getYDW(), getHttpClient());
    private final SlashCommandCaller slashCommandCaller =
            new SlashCommandCaller(getToken(), getYDW(), JSON, getHttpClient());
    private final MessageCaller messageCaller = new MessageCaller(getYDW(), JSON, getHttpClient());

    public RestApiHandler(OkHttpClient client) {
        this.client = client;
    }

    // I want a queue system were once the queue is called it executes the method

    public void setYDW(YDWReg ydw) {
        this.ydw = ydw;
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

    public YDWReg getYDW() {
        return ydw;
    }

    public OkHttpClient getHttpClient() {
        return client;
    }

    public String getToken() {
        return token;
    }

}
