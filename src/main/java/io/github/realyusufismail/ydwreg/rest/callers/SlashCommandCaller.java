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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.application.commands.ApplicationCommand;
import io.github.realyusufismail.ydw.application.commands.option.CommandType;
import io.github.realyusufismail.ydw.application.commands.reply.ReplyConfig;
import io.github.realyusufismail.ydw.application.interaction.InteractionCallbackType;
import io.github.realyusufismail.ydw.builder.MessageBuilder;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.application.commands.ApplicationCommandReg;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.Option;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.OptionExtender;
import io.github.realyusufismail.ydwreg.application.interaction.InteractionResponseBuilderReg;
import io.github.realyusufismail.ydwreg.builder.MessageBuilderReg;
import io.github.realyusufismail.ydwreg.entities.embed.builder.EmbedBuilder;
import io.github.realyusufismail.ydwreg.entities.message.MessageFlags;
import io.github.realyusufismail.ydwreg.rest.error.RestApiError;
import io.github.realyusufismail.ydwreg.rest.exception.RestApiException;
import io.github.realyusufismail.ydwreg.rest.name.EndPoint;
import io.github.realyusufismail.ydwreg.rest.queue.Queue;
import io.github.realyusufismail.ydwreg.rest.queue.YDWCallback;
import io.github.realyusufismail.ydwreg.rest.request.YDWRequest;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    public SlashCommandCaller(String token, String guildId, YDW ydw, MediaType json,
            OkHttpClient client) {
        this.token = token;
        this.guildId = guildId;
        this.ydw = (YDWReg) ydw;
        this.client = client;
        JSON = json;
    }

    public void createGlobalCommand() {
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

    public void createGuildOnlyCommand() {
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
                System.out.println("Failed to delete global slash command");
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
                System.out.println("Failed to delete guild slash command");
            }
        });
    }

    public void upsertGlobalCommand() {
        // need to check if command exits and if so update it otherwise create it and if it is not
        // there delete it
        if (name == null || description == null || guildId == null) {
            throw new IllegalStateException(
                    "Name, Description, Guild ID, and Options are required to call");
        }

        var commands = getGlobalSlashCommands();

        commands.stream()
            // check fif any commands need to be deleted
            .filter(command -> !command.getName().equals(name))
            .forEach(command -> deleteGlobalCommand(command.getIdLong()));

        commands.stream()
            .filter(c -> c.getName().equals(name))
            .forEach(c -> updateGlobalCommand(c.getIdLong()));

        if (commands.stream().noneMatch(c -> c.getName().equals(name))) {
            createGlobalCommand();
        }
    }

    public void upsertGuildCommand() {
        // need to check if command exits and if so update it otherwise create it and if it is not
        // there delete it
        if (name == null || description == null || guildId == null) {
            throw new IllegalStateException(
                    "Name, Description, Guild ID, and Options are required to call");
        }

        var commands = getGuildSlashCommands();


        commands.stream()
            .filter(c -> !c.getName().equals(name))
            .filter(c -> !c.getDescription().equals(description))
            .forEach(c -> deleteGuildCommand(c.getIdLong()));

        commands.stream()
            .filter(c -> c.getName().equals(name))
            .filter(c -> c.getDescription().equals(description))
            .forEach(c -> updateGuildCommand(c.getIdLong()));

        if (commands.stream().noneMatch(c -> c.getName().equals(name))) {
            createGuildOnlyCommand();
        }
    }

    public void deleteAllCommands() {
        var guildCommands = getGuildSlashCommands();
        var globalCommands = getGlobalSlashCommands();

        guildCommands.forEach(c -> deleteGuildCommand(c.getIdLong()));
        globalCommands.forEach(c -> deleteGlobalCommand(c.getIdLong()));
    }

    public List<ApplicationCommand> getGlobalSlashCommands() {
        Request request = new YDWRequest()
            .request(token, EndPoint.GLOBAL_SLASH_COMMAND.getFullEndpoint(ydw.getApplicationId()))
            .get()
            .build();

        try (var response = client.newCall(request).execute()) {
            return getSlashResponse(response);
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }

    public List<ApplicationCommand> getGuildSlashCommands() {
        Request request =
                new YDWRequest()
                    .request(token,
                            EndPoint.GUILD_SLASH_COMMAND.getFullEndpoint(ydw.getApplicationId(),
                                    guildId))
                    .get()
                    .build();

        try (var response = client.newCall(request).execute()) {
            return getSlashResponse(response);
        } catch (IOException e) {
            throw new RestApiException(e);
        }
    }

    public List<ApplicationCommand> getSlashResponse(Response response) throws IOException {
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + RestApiError.fromCode(response.code()) + " "
                    + RestApiError.fromCode(response.code()).getMessage());

        var body = response.body();
        JsonNode json = ydw.getMapper().readTree(body.string());
        List<ApplicationCommand> commands = new ArrayList<>();
        for (var command : json) {
            commands.add(new ApplicationCommandReg(command, command.get("id").asLong(), null, ydw));
        }
        return commands;
    }

    // Reply system
    public Request reply(String content, ReplyConfig config, String interactionId,
            String interactionToken) {
        if (token == null) {
            throw new IllegalStateException("Token is required to reply");
        }

        if (interactionToken == null) {
            throw new IllegalStateException("Interaction Token is required to reply");
        }

        RequestBody body = RequestBody.create(replyJson(content, null, config).toString(), JSON);

        return new YDWRequest()
            .request(token,
                    EndPoint.REPLY_TO_SLASH_COMMAND.getFullEndpoint(interactionId,
                            interactionToken))
            .post(body)
            .build();
    }

    public Request replyEmbed(EmbedBuilder build, ReplyConfig config, String id, String token) {
        if (token == null) {
            throw new IllegalStateException("Token is required to reply");
        }

        if (id == null) {
            throw new IllegalStateException("Interaction Token is required to reply");
        }

        RequestBody body = RequestBody.create(replyJson(null, build, config).toString(), JSON);

        return new YDWRequest()
            .request(token, EndPoint.REPLY_TO_SLASH_COMMAND.getFullEndpoint(id, token))
            .post(body)
            .build();
    }

    private ObjectNode slashCommandJson() {
        return JsonNodeFactory.instance.objectNode()
            .put("name", name)
            .put("description", description)
            .put("type", commandType);
    }


    private ObjectNode replyJson(@Nullable String content, @Nullable EmbedBuilder embed,
            ReplyConfig config) {
        var interaction = new InteractionResponseBuilderReg();

        interaction.setType(InteractionCallbackType.CHANNEL_MESSAGE_WITH_SOURCE);

        MessageBuilder messageBuilder = new MessageBuilderReg();

        if (content != null) {
            messageBuilder.setContent(content);
        }

        if (embed != null) {
            messageBuilder.setEmbeds(embed);
        }

        if (config.isEphemeral()) {
            messageBuilder.setFlags(MessageFlags.EPHEMERAL);
        }

        if (config.isTTS()) {
            messageBuilder.setTTS(true);
        }

        interaction.setMessageBuilder(messageBuilder);

        return interaction.toJson();
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

    public void queue(@NotNull Request request, @Nullable Consumer<? super Throwable> failure,
            Consumer<? super Response> success) {
        new Queue(client, request, failure, success).queue();
    }
}
