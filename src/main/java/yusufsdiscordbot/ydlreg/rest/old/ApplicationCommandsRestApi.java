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

package yusufsdiscordbot.ydlreg.rest.old;

import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yusufsdiscordbot.ydlreg.application.commands.data.CommandOptionData;
import yusufsdiscordbot.ydlreg.application.commands.data.SlashCommandData;
import yusufsdiscordbot.ydlreg.application.commands.data.UserCommandData;

import java.util.List;

public class ApplicationCommandsRestApi {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(ApplicationCommandsRestApi.class);
    private static final String COMMANDS = "/commands";
    private static final String GUILDS = "/guilds/";
    private final String restLink;
    private final MediaType JSON;
    private final OkHttpClient client;
    private final String token;
    private final String guildId;
    private final boolean isGuildOnly = false;
    private String botId;
    private String commandName;
    private String commandDescription = "";
    private List<CommandOptionData> commandOptionData;

    public ApplicationCommandsRestApi(String restLink, MediaType json, OkHttpClient client,
            String token, String guildId) {
        this.restLink = restLink;
        JSON = json;
        this.client = client;
        this.token = token;
        this.guildId = guildId;
    }

    public void createSlashCommand(String commandName, String commandDescription, String botId,
            boolean isGuildOnly) {
        String url = restLink + "/application/" + botId;

        if (isGuildOnly) {
            url += GUILDS + guildId + COMMANDS;
        } else {
            url += COMMANDS;
        }

        JsonNodeCreator JsonNodeCreator = new JsonNodeCreator().add("name", commandName)
            .add("type", 1)
            .add("description", commandDescription)
            .add("options", commandOptionData.stream()
                .map(data -> new JsonNodeCreator().add("name", data.getOptionName())
                    .add("description", data.getOptionDescription())
                    .add("type", 3)
                    .add("required", data.isRequired())
                    .add("choices", data.getChoices()))
                .collect(ArrayNodeCreator::new, ArrayNodeCreator::add, ArrayNodeCreator::addAll));

        RequestBody body = RequestBody.create(JsonNodeCreator.toJson(), JSON);
        Request request = new Request.Builder().url(url)
            .post(body)
            .addHeader("Authorization", "Bot " + token)
            .build();
        try {
            client.newCall(request).execute();
        } catch (Exception e) {
            logger.error("Unable to create slash command", e);
        }
    }

    public void createUserCommand(String commandName, String botId, boolean isGuildOnly) {
        String url = restLink + "/application/" + botId;

        if (isGuildOnly) {
            url += GUILDS + guildId + COMMANDS;
        } else {
            url += COMMANDS;
        }

        JsonNodeCreator JsonNodeCreator =
                new JsonNodeCreator().add("name", commandName).add("type", 2);

        RequestBody body = RequestBody.create(JsonNodeCreator.toJson(), JSON);
        Request request = new Request.Builder().url(url)
            .post(body)
            .addHeader("Authorization", "Bot " + token)
            .build();
        try {
            client.newCall(request).execute();
        } catch (Exception e) {
            logger.error("Unable to create user command", e);
        }
    }

    public void addSlashCommand(@NotNull SlashCommandData slashCommandData, String applicationId) {
        botId = applicationId;
        commandName = slashCommandData.getCommandName();
        commandDescription = slashCommandData.getCommandDescription();
        commandOptionData = slashCommandData.getOptions();

        createSlashCommand(commandName, commandDescription, botId, isGuildOnly);
    }

    public void addUserCommand(@NotNull UserCommandData userCommandData, String botId) {
        this.botId = botId;
        commandName = userCommandData.getCommandName();
        createUserCommand(commandName, botId, isGuildOnly);
    }
}
