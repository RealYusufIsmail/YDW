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

package io.github.realyusufismail.ydwreg.rest.callers;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.application.commands.slash.SlashCommandReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.rest.queue.Queue;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class MessageCaller {

    private final YDWReg ydw;

    @NotNull
    private final OkHttpClient client;
    private final MediaType JSON;

    private Boolean tts;
    private Boolean mentionable;

    public MessageCaller(YDW ydw, MediaType json, OkHttpClient client) {
        this.ydw = (YDWReg) ydw;
        JSON = json;
        this.client = client;
    }

    public @Nullable Request reply(String message, SlashCommandReg slashCommandReg) {
        // TODO: Implement
        return null;
    }

    public @Nullable Request replyEmbed(EmbedBuilder embed, SlashCommandReg slashCommandReg) {
        // TODO: Implement
        return null;
    }

    public void setTTS(boolean tts) {
        this.tts = tts;
    }

    public void setMentionable(boolean mentionable) {
        this.mentionable = mentionable;
    }

    public void queue(@NotNull Request request, @Nullable Consumer<? super Throwable> failure,
            Consumer<? super Response> success) {
        new Queue(client, request, failure, success).queue();
    }
}
