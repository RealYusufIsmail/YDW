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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.option.CommandType;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.Option;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.OptionExtender;
import io.github.realyusufismail.ydwreg.rest.queue.YDWCallback;
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
    private final Integer commandType = CommandType.CHAT_INPUT.getValue();
    private String interactionToken;
    private String name;
    private String description;
    private Collection<Option> options;
    private Collection<OptionExtender> extender;

    private boolean ephemeral;
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

    public void updateGlobalCommand(long commandId) {

        if (name == null || description == null || guildId == null) {
            throw new IllegalStateException(
                    "Name, Description, Guild ID, and Options are required to call");
        }

        Request request = new YDWRequest()
            .request(token,
                    EndPoint.UPDATE_GLOBAL_SLASH_COMMAND.getFullEndpoint(ydw.getApplicationId(),
                            commandId))
            .patch(RequestBody.create(slashCommandJson().toString(), JSON))
            .build();

        client.newCall(request).enqueue(new YDWCallback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ydw.getLogger().error("Failed to update global slash command", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (!response.isSuccessful()) {
                    RestApiError error = RestApiError.fromCode(response.code());
                    ydw.getLogger()
                        .error("Failed to update global slash command: " + error.getMessage());
                }
            }
        });
    }

    public void updateGuildCommand(long commandId) {
        if (name == null || description == null || guildId == null) {
            throw new IllegalStateException(
                    "Name, Description, Guild ID, and Options are required to call");
        }

        Request request = new YDWRequest()
            .request(token,
                    EndPoint.UPDATE_GUILD_SLASH_COMMAND.getFullEndpoint(ydw.getApplicationId(),
                            guildId, commandId))
            .patch(RequestBody.create(slashCommandJson().toString(), JSON))
            .build();

        client.newCall(request).enqueue(new YDWCallback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ydw.getLogger().error("Failed to update guild slash command", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (!response.isSuccessful()) {
                    RestApiError error = RestApiError.fromCode(response.code());
                    ydw.getLogger()
                        .error("Failed to update guild slash command: " + error.getMessage());
                }
            }
        });
    }

    public void deleteGlobalCommand(long commandId) {
        Request request = new YDWRequest()
            .request(token,
                    EndPoint.DELETE_GLOBAL_SLASH_COMMAND.getFullEndpoint(ydw.getApplicationId(),
                            commandId))
            .delete()
            .build();

        client.newCall(request).enqueue(new YDWCallback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ydw.getLogger().error("Failed to delete global slash command", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (!response.isSuccessful()) {
                    RestApiError error = RestApiError.fromCode(response.code());
                    ydw.getLogger()
                        .error("Failed to delete global slash command: " + error.getMessage());
                }
            }
        });
    }

    public void deleteGuildCommand(long commandId) {
        Request request = new YDWRequest()
            .request(token,
                    EndPoint.DELETE_GUILD_SLASH_COMMAND.getFullEndpoint(ydw.getApplicationId(),
                            guildId, commandId))
            .delete()
            .build();

        client.newCall(request).enqueue(new YDWCallback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ydw.getLogger().error("Failed to delete guild slash command", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (!response.isSuccessful()) {
                    RestApiError error = RestApiError.fromCode(response.code());
                    ydw.getLogger()
                        .error("Failed to delete guild slash command: " + error.getMessage());
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

    public void reply(String content, String interactionId, String interactionToken) {
        if (token == null) {
            throw new IllegalStateException("Token is required to reply");
        }

        if (interactionToken == null) {
            throw new IllegalStateException("Interaction Token is required to reply");
        }

        JsonNode json = JsonNodeFactory.instance.objectNode().put("content", content);

        RequestBody body = RequestBody.create(json.toString(), JSON);

        System.out.println(EndPoint.REPLY_TO_SLASH_COMMAND.getFullEndpoint(ydw.getApplicationId(),
                interactionToken));
        Request request = new YDWRequest()
            .request(token,
                    EndPoint.REPLY_TO_SLASH_COMMAND.getFullEndpoint(interactionId,
                            interactionToken))
            .post(body)
            .build();

        client.newCall(request).enqueue(new YDWCallback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ydw.getLogger().error("Failed to reply to slash command", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (!response.isSuccessful()) {
                    RestApiError error = RestApiError.fromCode(response.code());
                    ydw.getLogger()
                        .error("Failed to reply to slash command: " + error.getCode() + " "
                                + error.getMessage());
                }
            }
        });
    }

    private ObjectNode replyJson(String content) {
        return JsonNodeFactory.instance.objectNode().put("content", content);
    }

    public void setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
    }

    public void setTTS(boolean tts) {
        this.tts = tts;
    }

    public void queue(@NotNull Request request, @Nullable Consumer<? super Throwable> failure) {
        new Queue(client, request, failure).queue();
    }
}
