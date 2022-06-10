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


import com.neovisionaries.ws.client.WebSocketException;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.websocket.event.Event;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydw.application.commands.slash.builder.SlashCommandBuilder;
import io.github.realyusufismail.ydw.entities.*;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.SlashCommandBuilderReg;
import io.github.realyusufismail.ydwreg.application.interaction.InteractionManager;
import io.github.realyusufismail.ydwreg.entities.guild.manager.GuildManager;
import io.github.realyusufismail.ydwreg.rest.RestApiHandler;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

public interface YDW {
    @NotNull
    List<Guild> getGuilds();

    Guild getGuild(long guildId);

    default Guild getGuild(@NotNull String guildId) {
        return getGuild(Long.parseLong(guildId));
    }

    List<UnavailableGuild> getUnavailableGuilds();

    List<AvailableGuild> getAvailableGuilds();

    boolean isSpecifiedGuildAvailable(long guildId);

    default boolean isSpecifiedGuildAvailable(@NotNull String guildId) {
        return isSpecifiedGuildAvailable(Long.parseLong(guildId));
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


    void login(String token, int gatewayIntents, String status, int largeThreshold,
            boolean compress, ActivityConfig activity) throws IOException, WebSocketException;


    void setToken(String token);

    void setGuildId(String guildId);

    long getPing();

    void setPing(long ping);

    long getSequenceNumber();

    long getGatewayPing();

    RestApiHandler getRest();

    WebSocketManager getWebSocket();

    int getMaxReconnectDelay();

    boolean needsToAutoReconnect();

    SelfUser getSelfUser();

    <EventClass extends Event> Flux<EventClass> onEvent(Class<EventClass> event);

    boolean isResumed();

    boolean hasReconnected();

    InteractionManager getInteractionManager();

    GuildManager getGuildManager();

    default SlashCommandBuilder newSlashCommand(String name, String description) {
        return new SlashCommandBuilderReg(this, name, description).call();
    }
}
