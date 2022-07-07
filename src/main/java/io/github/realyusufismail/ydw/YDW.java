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

package io.github.realyusufismail.ydw;


import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
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

import javax.annotation.CheckReturnValue;
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

    String getToken();

    long getApplicationId();

    void addEventAdapter(Object... eventAdapters);

    void removeEventAdapter(Object... eventAdapters);

    YDW awaitReady();
}
