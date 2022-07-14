/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.rest.callers;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.application.commands.slash.SlashCommandReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.rest.queue.Queue;
import okhttp3.*;
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
    private ResponseBody body = null;

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
