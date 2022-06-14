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
import io.github.realyusufismail.websocket.event.Event;
import io.github.realyusufismail.websocket.event.EventInterface;
import io.github.realyusufismail.ydw.activity.ActivityConfig;
import io.github.realyusufismail.ydw.application.commands.slash.builder.SlashCommandBuilder;
import io.github.realyusufismail.ydw.entities.*;
import io.github.realyusufismail.ydw.entities.guild.Channel;
import io.github.realyusufismail.ydwreg.application.commands.slash.builder.SlashCommandBuilderReg;
import io.github.realyusufismail.ydwreg.application.interaction.InteractionManager;
import io.github.realyusufismail.ydwreg.entities.guild.manager.GuildManager;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;

import java.util.List;

public interface YDW {

    /**
     * Used to indicate the connection status.
     */
    enum ApiStatus {
        /**
         * Indicates that the api is starting.
         */
        STARTING(true),
        /**
         * Indicates that the api has started.
         */
        STARTED(true),
        /**
         * Indicates the bot is logging in.
         */
        LOGGING_IN(true),
        /**
         * Indicates that the bot is trying to connect to the websocket.
         */
        CONNECTING_TO_WEBSOCKET(true),
        /**
         * Indicates the bot has logged in but is not trying to identify.
         */
        LOGGED_IN_AND_IDENTIFYING(true),
        /**
         * The required info has been sent but YDW is not awaiting confirmation.
         */
        AWAITING_CONFIRMATION(true),
        /**
         * This indicates that YDW is preparing the backend.
         */
        PREPARING_BACKEND(true),
        /**
         * Everything has gone well and YDW has received the right information from Discord.
         */
        READY(true),
        /**
         * Indicates that the websocket has disconnected, will try to resume.
         */
        WEBSOCKET_DISCONNECTED,
        /**
         * Indicates that a reconnection attempt has been queued.
         */
        RECONNECTING,
        /**
         * Indicates that the bot is reconnecting.
         */
        RECONNECTING_TO_WEBSOCKET,
        /**
         * Indicates that the bot is shutting down.
         */
        SHUTTING_DOWN,
        /**
         * Indicates that the bot has shut down.
         */
        SHUT_DOWN,
        /**
         * Indicates that that login attempt has failed.
         */
        LOGIN_FAILED;

        private final boolean isInitialized;

        ApiStatus(boolean isInitialized) {
            this.isInitialized = isInitialized;
        }

        ApiStatus() {
            this(false);
        }

        public boolean isInitialized() {
            return isInitialized;
        }
    }

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
            ActivityConfig activity) throws Exception;


    void setToken(String token);

    void setGuildId(String guildId);

    long getPing();

    void setPing(long ping);

    long getSequenceNumber();

    long getGatewayPing();

    RestApiHandler1 getRest();

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

    EventInterface getEventInterface();
}
