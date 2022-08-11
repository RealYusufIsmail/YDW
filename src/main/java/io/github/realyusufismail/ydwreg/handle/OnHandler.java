/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.handle;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.websocket.core.EventNames;
import io.github.realyusufismail.ydwreg.YDWReg;
import io.github.realyusufismail.ydwreg.handle.handles.*;
import io.github.realyusufismail.ydwreg.handle.handles.channel.ChannelCreateHandler;
import io.github.realyusufismail.ydwreg.handle.handles.channel.ChannelDeleteHandler;
import io.github.realyusufismail.ydwreg.handle.handles.channel.ChannelPinsUpdateHandler;
import io.github.realyusufismail.ydwreg.handle.handles.channel.ChannelUpdateHandler;
import io.github.realyusufismail.ydwreg.handle.handles.guild.*;
import io.github.realyusufismail.ydwreg.handle.handles.integration.IntegrationCreateHandler;
import io.github.realyusufismail.ydwreg.handle.handles.integration.IntegrationDeleteHandler;
import io.github.realyusufismail.ydwreg.handle.handles.integration.IntegrationUpdateHandler;
import io.github.realyusufismail.ydwreg.handle.handles.interaction.InteractionCreateHandler;
import io.github.realyusufismail.ydwreg.handle.handles.invite.InviteCreateHandler;
import io.github.realyusufismail.ydwreg.handle.handles.invite.InviteDeleteHandler;
import io.github.realyusufismail.ydwreg.handle.handles.message.*;
import io.github.realyusufismail.ydwreg.handle.handles.thread.*;
import io.github.realyusufismail.ydwreg.handle.handles.voice.VoiceServerUpdateHandler;
import io.github.realyusufismail.ydwreg.handle.handles.voice.VoiceStateUpdateHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class OnHandler {
    private final YDWReg ydw;
    private final JsonNode json;
    private final String event;

    public OnHandler(YDWReg ydw, String event, JsonNode json) {
        this.ydw = ydw;
        this.json = json;
        this.event = event;
    }

    public void start() {
        Optional<EventNames> eventName = Optional.of(EventNames.getEvent(event));
        eventName.ifPresent(name -> fire(name, json));
    }

    public void fire(@NotNull EventNames event, JsonNode json) {
        switch (event) {
            case HELLO -> new HelloHandler(json, ydw).start();
            case INVALID_SESSION -> new InvalidSessionHandler(json, ydw).start();
            case APPLICATION_COMMAND_PERMISSIONS_UPDATE -> new ApplicationCommandPermissionsUpdateHandler(
                    json, ydw).start();
            case CHANNEL_CREATE -> new ChannelCreateHandler(json, ydw).start();
            case CHANNEL_UPDATE -> new ChannelUpdateHandler(json, ydw).start();
            case CHANNEL_DELETE -> new ChannelDeleteHandler(json, ydw).start();
            case CHANNEL_PINS_UPDATE -> new ChannelPinsUpdateHandler(json, ydw).start();
            case THREAD_CREATE -> new ThreadCreateHandler(json, ydw).start();
            case THREAD_UPDATE -> new ThreadUpdateHandler(json, ydw).start();
            case THREAD_DELETE -> new ThreadDeleteHandler(json, ydw).start();
            case THREAD_LIST_SYNC -> new ThreadListSyncHandler(json, ydw).start();
            case THREAD_MEMBERS_UPDATE -> new ThreadMembersUpdateHandler(json, ydw).start();
            case GUILD_CREATE -> new GuildCreateHandler(json, ydw).start();
            case GUILD_UPDATE -> new GuildUpdateHandler(json, ydw).start();
            case GUILD_DELETE -> new GuildDeleteHandler(json, ydw).start();
            case GUILD_BAN_ADD -> new GuildBanAddHandler(json, ydw).start();
            case GUILD_BAN_REMOVE -> new GuildBanRemoveHandler(json, ydw).start();
            case GUILD_EMOJIS_UPDATE -> new GuildEmojisUpdateHandler(json, ydw).start();
            case GUILD_INTEGRATIONS_UPDATE -> new GuildIntegrationsUpdateHandler(json, ydw).start();
            case GUILD_MEMBER_ADD -> new GuildMemberAddHandler(json, ydw).start();
            case GUILD_MEMBER_REMOVE -> new GuildMemberRemoveHandler(json, ydw).start();
            case GUILD_MEMBER_UPDATE -> new GuildMemberUpdateHandler(json, ydw).start();
            case GUILD_ROLE_CREATE -> new GuildRoleCreateHandler(json, ydw).start();
            case GUILD_ROLE_UPDATE -> new GuildRoleUpdateHandler(json, ydw).start();
            case GUILD_ROLE_DELETE -> new GuildRoleDeleteHandler(json, ydw).start();
            case GUILD_SCHEDULED_EVENT_CREATE -> new GuildScheduledEventCreateHandler(json, ydw)
                .start();
            case GUILD_SCHEDULED_EVENT_UPDATE -> new GuildScheduledEventUpdateHandler(json, ydw)
                .start();
            case GUILD_SCHEDULED_EVENT_DELETE -> new GuildScheduledEventDeleteHandler(json, ydw)
                .start();
            case GUILD_SCHEDULED_EVENT_USER_ADD -> new GuildScheduledEventUserAddHandler(json, ydw)
                .start();
            case GUILD_SCHEDULED_EVENT_USER_REMOVE -> new GuildScheduledEventUserRemoveHandler(json,
                    ydw).start();
            case INTEGRATION_CREATE -> new IntegrationCreateHandler(json, ydw).start();
            case INTEGRATION_UPDATE -> new IntegrationUpdateHandler(json, ydw).start();
            case INTEGRATION_DELETE -> new IntegrationDeleteHandler(json, ydw).start();
            case INTERACTION_CREATE -> new InteractionCreateHandler(json, ydw).start();
            case INVITE_CREATE -> new InviteCreateHandler(json, ydw).start();
            case INVITE_DELETE -> new InviteDeleteHandler(json, ydw).start();
            case MESSAGE_CREATE -> new MessageCreateHandler(json, ydw).start();
            case MESSAGE_UPDATE -> new MessageUpdateHandler(json, ydw).start();
            case MESSAGE_DELETE -> new MessageDeleteHandler(json, ydw).start();
            case MESSAGE_DELETE_BULK -> new MessageDeleteBulkHandler(json, ydw).start();
            case MESSAGE_REACTION_ADD -> new MessageReactionAddHandler(json, ydw).start();
            case MESSAGE_REACTION_REMOVE -> new MessageReactionRemoveHandler(json, ydw).start();
            case MESSAGE_REACTION_REMOVE_ALL -> new MessageReactionRemoveAllHandler(json, ydw)
                .start();
            case PRESENCE_UPDATE -> new PresenceUpdateHandler(json, ydw).start();
            case TYPING_START -> new TypingStartHandler(json, ydw).start();
            case USER_UPDATE -> new UserUpdateHandler(json, ydw).start();
            case VOICE_STATE_UPDATE -> new VoiceStateUpdateHandler(json, ydw).start();
            case VOICE_SERVER_UPDATE -> new VoiceServerUpdateHandler(json, ydw).start();
            case WEBHOOKS_UPDATE -> new WebhooksUpdateHandler(json, ydw).start();
            default -> WebSocketManager.logger.debug("Event not found: " + event);
        }
    }
}
