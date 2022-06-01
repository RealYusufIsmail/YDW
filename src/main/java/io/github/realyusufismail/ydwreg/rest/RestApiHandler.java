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

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.rest.callers.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class RestApiHandler {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final YDW ydw;

    @NotNull
    OkHttpClient client = new OkHttpClient();
    private boolean isEphemeral = false;
    private boolean isTTS = false;
    private boolean isMentionable = false;
    private boolean isGuildOnlyCommand = false;
    private String guildId;
    private String token = "";
    private final GuildCaller guildRestApi = new GuildCaller(getYDW(), JSON);
    private final UserCaller userCaller = new UserCaller(getYDW());
    private final StickerCaller stickerCaller = new StickerCaller((YDWReg) getYDW());
    private final EmojiCaller emojiCaller = new EmojiCaller(getYDW());
    private final ChannelCaller channelCaller = new ChannelCaller(getYDW());
    private final YDWCaller ydwCaller = new YDWCaller(getYDW());
    private final SlashCommandCaller slashCommandCaller =
            new SlashCommandCaller(getToken(), getYDW(), JSON);
    private final MessageCaller messageCaller = new MessageCaller(getYDW(), JSON);

    public RestApiHandler(YDW ydw) {
        this.ydw = ydw;
    }

    // I want a queue system were once the queue is called it executes the method


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

    public YDW getYDW() {
        return ydw;
    }

    public String getToken() {
        return token;
    }
}
