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

package io.github.realyusufismail.websocket;

import org.jetbrains.annotations.NotNull;

public enum EventNames {
    READY("READY"),
    RESUMED("RESUMED"),
    RECONNECT("RECONNECT"),
    INVALID_SESSION("INVALID_SESSION"),
    INTERACTION_CREATE("INTERACTION_CREATE"),
    APPLICATION_COMMAND_PERMISSIONS_UPDATE("APPLICATION_COMMAND_PERMISSIONS_UPDATE"),
    CHANNEL_CREATE("CHANNEL_CREATE"),
    CHANNEL_UPDATE("CHANNEL_UPDATE"),
    CHANNEL_DELETE("CHANNEL_DELETE"),
    CHANNEL_PINS_UPDATE("CHANNEL_PINS_UPDATE"),
    THREAD_CREATE("THREAD_CREATE"),
    THREAD_UPDATE("THREAD_UPDATE"),
    THREAD_DELETE("THREAD_DELETE"),
    THREAD_LIST_SYNC("THREAD_LIST_SYNC"),
    THREAD_MEMBERS_UPDATE("THREAD_MEMBERS_UPDATE"),
    GUILD_CREATE("GUILD_CREATE"),
    GUILD_UPDATE("GUILD_UPDATE"),
    GUILD_DELETE("GUILD_DELETE"),
    GUILD_BAN_ADD("GUILD_BAN_ADD"),
    GUILD_BAN_REMOVE("GUILD_BAN_REMOVE"),
    GUILD_EMOJIS_UPDATE("GUILD_EMOJIS_UPDATE"),
    GUILD_INTEGRATIONS_UPDATE("GUILD_INTEGRATIONS_UPDATE"),
    GUILD_MEMBER_ADD("GUILD_MEMBER_ADD"),
    GUILD_MEMBER_REMOVE("GUILD_MEMBER_REMOVE"),
    GUILD_MEMBER_UPDATE("GUILD_MEMBER_UPDATE"),
    GUILD_ROLE_CREATE("GUILD_ROLE_CREATE"),
    GUILD_ROLE_UPDATE("GUILD_ROLE_UPDATE"),
    GUILD_ROLE_DELETE("GUILD_ROLE_DELETE"),
    INVITE_CREATE("INVITE_CREATE"),
    INVITE_DELETE("INVITE_DELETE"),
    MESSAGE_CREATE("MESSAGE_CREATE"),
    MESSAGE_UPDATE("MESSAGE_UPDATE"),
    MESSAGE_DELETE("MESSAGE_DELETE"),
    MESSAGE_DELETE_BULK("MESSAGE_DELETE_BULK"),
    MESSAGE_REACTION_ADD("MESSAGE_REACTION_ADD"),
    MESSAGE_REACTION_REMOVE("MESSAGE_REACTION_REMOVE"),
    MESSAGE_REACTION_REMOVE_ALL("MESSAGE_REACTION_REMOVE_ALL"),
    PRESENCE_UPDATE("PRESENCE_UPDATE"),
    TYPING_START("TYPING_START"),
    USER_UPDATE("USER_UPDATE"),
    VOICE_STATE_UPDATE("VOICE_STATE_UPDATE"),
    VOICE_SERVER_UPDATE("VOICE_SERVER_UPDATE"),
    WEBHOOKS_UPDATE("WEBHOOKS_UPDATE"),
    UNKNOWN("UNKNOWN");

    private final String name;

    EventNames(String name) {
        this.name = name;
    }

    @NotNull
    public static EventNames getEvent(String name) {
        for (EventNames event : EventNames.values()) {
            if (event.getName().equals(name)) {
                return event;
            }
        }
        return UNKNOWN;
    }

    public String getName() {
        return name;
    }
}
