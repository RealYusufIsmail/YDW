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
            
package io.github.realyusufismail.ydw;


import com.google.errorprone.annotations.CheckReturnValue;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydw.application.commands.slash.builder.SlashCommandBuilder;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.SelfUser;
import io.github.realyusufismail.ydw.entities.User;
import io.github.realyusufismail.ydw.entities.guild.channel.Category;
import io.github.realyusufismail.ydwreg.application.commands.option.interaction.InteractionManager;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.SlashCommandBuilderReg;
import io.github.realyusufismail.ydwreg.rest.RestApiHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface YDW {
    @NotNull
    List<Guild> getGuilds();

    Guild getGuild(long guildId);

    default Guild getGuild(@NotNull String guildId) {
        return getGuild(Long.parseLong(guildId));
    }

    @NotNull
    User getUser(long userId);

    @NotNull
    default User getUser(@NotNull String userId) {
        return getUser(Long.parseLong(userId));
    }

    Channel getChannel(long channelId);

    default Channel getChannel(@NotNull String channelId) {
        return getChannel(Long.parseLong(channelId));
    }

    Category getCategory(long categoryId);

    default Category getCategory(@NotNull String categoryId) {
        return getCategory(Long.parseLong(categoryId));
    }

    void login(String token, int gatewayIntents, String status, int largeThreshold,
            ActivityConfig activity) throws Exception;

    void loginForRest(String token, @Nullable String guildId);

    long getPing();

    void setPing(long ping);

    long getGatewayPing();

    RestApiHandler getRest();

    WebSocketManager getWebSocket();

    SelfUser getSelfUser() throws InterruptedException;

    InteractionManager getInteractionManager();

    @CheckReturnValue
    default SlashCommandBuilderReg newSlashCommand(String name, String description)
            throws InterruptedException {
        return new SlashCommandBuilderReg(this, name, description);
    }

    @CheckReturnValue
    default SlashCommandBuilderReg updateSlashCommand(String name, String description)
            throws InterruptedException {
        return new SlashCommandBuilderReg(this, name, description);
    }

    default void deleteGlobalSlashCommand(long commandId) throws InterruptedException {
        getRest().getSlashCommandCaller().deleteGlobalCommand(commandId);
    }

    default void deleteGuildSlashCommand(long commandId) throws InterruptedException {
        getRest().getSlashCommandCaller().deleteGuildCommand(commandId);
    }

    // TODO: completely and utterly broken
    void upsertCommands(List<SlashCommandBuilder> commands);

    String getToken();

    long getApplicationId();

    void addEventAdapter(Object... eventAdapters);

    void removeEventAdapter(Object... eventAdapters);

    YDW awaitReady();
}
