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

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.action.ReplyAction;
import io.github.realyusufismail.ydw.application.commands.option.CommandType;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.action.ReplyActionReg;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.Option;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.OptionExtender;
import io.github.realyusufismail.ydwreg.rest.YDWCallback;
import io.github.realyusufismail.ydwreg.rest.error.RestApiError;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import io.github.realyusufismail.ydwreg.rest.queue.Queue;
import io.github.realyusufismail.ydwreg.rest.request.YDWRequest;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;

public class SlashCommandCaller {
    private final YDWReg ydw;

    private final OkHttpClient client;

    private final MediaType JSON;
    private final String token;
    private final String guildId;
    private String interactionToken;

    private String name;
    private String description;
    private final Integer commandType = CommandType.CHAT_INPUT.getValue();
    private Collection<Option> options;
    private Collection<OptionExtender> extender;

    private Boolean ephemeral;
    private Boolean tts;
    private Boolean mentionable;

    public SlashCommandCaller(String token, String guildId, YDW ydw, MediaType json,
            OkHttpClient client) {
        this.token = token;
        this.guildId = guildId;
        this.ydw = (YDWReg) ydw;
        this.client = client;
        JSON = json;
    }

    public void callGlobalCommand() {
        if (name == null || description == null) {
            throw new IllegalStateException("Name, Description, and Options are required to call");
        }

        if (options != null) {
            slashCommandJson().set("options", Option.toJsonArray(options, extender));
        }

        RequestBody body = RequestBody.create(slashCommandJson().toString(), JSON);

        Request request = new YDWRequest()
            .request(token, EndPoint.GLOBAL_SLASH_COMMAND.getFullEndpoint(ydw.getApplicationId()))
            .post(body)
            .build();

        client.newCall(request).enqueue(new YDWCallback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ydw.getLogger().error("Failed to register global slash command", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (!response.isSuccessful()) {
                    RestApiError error = RestApiError.fromCode(response.code());
                    ydw.getLogger()
                        .error("Failed to register global slash command: " + error.getMessage());
                }
            }
        });
    }

    public void callGuildOnlyCommand() {
        if (name == null || description == null || guildId == null) {
            throw new IllegalStateException(
                    "Name, Description, Guild ID, and Options are required to call");
        }

        if (options != null) {
            slashCommandJson().set("options", Option.toJsonArray(options, extender));
        }

        RequestBody body = RequestBody.create(slashCommandJson().toString(), JSON);

        Request request =
                new YDWRequest()
                    .request(token,
                            EndPoint.GUILD_SLASH_COMMAND.getFullEndpoint(ydw.getApplicationId(),
                                    guildId))
                    .post(body)
                    .build();

        client.newCall(request).enqueue(new YDWCallback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ydw.getLogger().error("Failed to register guild only slash command", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (!response.isSuccessful()) {
                    RestApiError error = RestApiError.fromCode(response.code());
                    ydw.getLogger()
                        .error("Failed to register guild only slash command: "
                                + error.getMessage());
                }
            }
        });
    }

    private ObjectNode slashCommandJson() {
        return JsonNodeFactory.instance.objectNode()
            .put("name", name)
            .put("description", description)
            .put("type", commandType);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOptions(Collection<Option> options) {
        this.options = options;
    }

    public void setOptionExtenders(Collection<OptionExtender> optionExtenders) {
        this.extender = optionExtenders;
    }

    // Reply system

    // TODO: Implement reply system
    private ObjectNode replyJson(String content) {
        return JsonNodeFactory.instance.objectNode()
            .put("content", content)
            .put("tts", tts)
            .put("flags", ephemeral ? 64 : null);
    }

    public ReplyAction reply(String content) {
        if (token == null || interactionToken == null) {
            throw new IllegalStateException("Token and interaction Token are required to reply");
        }

        Request request = new YDWRequest()
            .request(token,
                    EndPoint.REPLY_TO_SLASH_COMMAND.getFullEndpoint(ydw.getApplicationId(),
                            interactionToken))
            .post(RequestBody.create(replyJson(content).toString(), JSON))
            .build();

        return new ReplyActionReg(request, ydw);
    }

    public void setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
    }

    public void setTTS(boolean tts) {
        this.tts = tts;
    }

    public SlashCommandCaller setInteractionToken(String interactionToken) {
        this.interactionToken = interactionToken;
        return this;
    }

    public <T> void queue(@NotNull Request request, @Nullable Consumer<? super T> success,
            @Nullable Consumer<? super Throwable> failure) {
        new Queue<T>(client, request, success, failure).queue();
    }
}
