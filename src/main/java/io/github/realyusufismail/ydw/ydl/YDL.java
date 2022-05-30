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

package io.github.realyusufismail.ydw.ydl;


import com.neovisionaries.ws.client.WebSocketException;
import io.github.realyusufismail.ydw.websocket.WebSocketManager;
import io.github.realyusufismail.ydw.ydl.activity.ActivityConfig;
import io.github.realyusufismail.ydw.ydl.entities.Guild;
import io.github.realyusufismail.ydw.ydl.entities.SelfUser;
import io.github.realyusufismail.ydw.ydl.entities.UnavailableGuild;
import io.github.realyusufismail.ydw.ydl.entities.User;
import io.github.realyusufismail.ydw.ydl.entities.guild.Channel;
import io.github.realyusufismail.ydw.ydlreg.rest.RestApiHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public interface YDL {
    @NotNull
    default List<Guild> getGuilds() {
        return null;
    }

    Guild getGuild(long guildId);

    default Guild getGuild(@NotNull String guildId) {
        return getGuild(Long.parseLong(guildId));
    }

    List<UnavailableGuild> getUnavailableGuilds();

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

    void removeEventListeners(@NotNull Object... eventListener);

    List<Object> getEventListeners();

    void setEventListeners(@NotNull Object... eventListener);

    long getGatewayPing();

    RestApiHandler getRest();

    WebSocketManager getWebSocket();

    int getMaxReconnectDelay();

    boolean needsToAutoReconnect();

    SelfUser getSelfUser();
}
