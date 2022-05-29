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

package yusufsdiscordbot.ydl.perm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public enum Permission {
    /**
     * Allows creation of instant invites
     */
    CREATE_INSTANT_INVITE(1 << 0),
    /**
     * Allows kicking members
     */
    KICK_MEMBERS(1 << 1),
    /**
     * Allows banning members
     */
    BAN_MEMBERS(1 << 2),
    /**
     * Allows all permissions and bypasses channel permission overwrites
     */
    ADMINISTRATOR(1 << 3),
    /**
     * Allows management and editing of channels
     */
    MANAGE_CHANNELS(1 << 4),
    /**
     * Allows management and editing of the guild
     */
    MANAGE_GUILD(1 << 5),
    /**
     * Allows for the addition of reactions to messages
     */
    ADD_REACTIONS(1 << 6),
    /**
     * Allows for viewing of audit logs
     */
    VIEW_AUDIT_LOG(1 << 7),
    /**
     * Allows for using priority speaker in a voice channel
     */
    PRIORITY_SPEAKER(1 << 8),
    /**
     * Allows the user to go live
     */
    STREAM(1 << 9),
    /**
     * Allows guild members to view a channel, which includes reading messages in text channels and
     * joining voice channels
     */
    VIEW_CHANNEL(1 << 10),
    /**
     * Allows for sending messages in a channel
     */
    SEND_MESSAGES(1 << 11),
    /**
     * Allows for sending of text-to-speech messages
     */
    SEND_TTS_MESSAGES(1 << 12),
    /**
     * Allows for deletion of other users messages
     */
    MANAGE_MESSAGES(1 << 13),
    /**
     * Links sent by users with this permission will be auto-embedded
     */
    EMBED_LINKS(1 << 14),
    /**
     * Allows for uploading images and files
     */
    ATTACH_FILES(1 << 15),
    /**
     * Allows for reading of message history
     */
    READ_MESSAGE_HISTORY(1 << 16),
    /**
     * Allows for using the @everyone tag to notify all users in a channel, and the @here tag to
     * notify all online users in a channel
     */
    MENTION_EVERYONE(1 << 17),
    /**
     * Allows the usage of custom emojis from other servers
     */
    USE_EXTERNAL_EMOJIS(1 << 18),
    /**
     * Allows for joining of a voice channel
     */
    CONNECT(1 << 20),
    /**
     * Allows for speaking in a voice channel
     */
    SPEAK(1 << 21),
    /**
     * Allows for muting members in a voice channel
     */
    MUTE_MEMBERS(1 << 22),
    /**
     * Allows for deafening of members in a voice channel
     */
    DEAFEN_MEMBERS(1 << 23),
    /**
     * Allows for moving of members between voice channels
     */
    MOVE_MEMBERS(1 << 24),
    /**
     * Allows for using voice-activity-detection in a voice channel
     */
    USE_VAD(1 << 25),
    /**
     * Allows for modification of own nickname
     */
    CHANGE_NICKNAME(1 << 26),
    /**
     * Allows for modification of other users nicknames
     */
    MANAGE_NICKNAMES(1 << 27),
    /**
     * Allows management and editing of roles
     */
    MANAGE_ROLES(1 << 28),
    /**
     * Allows management and editing of webhooks
     */
    MANAGE_WEBHOOKS(1 << 29),
    /**
     * Allows management and editing of emojis
     */
    MANAGE_EMOJIS(1 << 30),
    /**
     * Allows members to use application commands, including slash commands and context menu
     * commands.
     */
    USE_APPLICATION_COMMANDS(1L << 31),
    /**
     * Allows for requesting to speak in stage channels. (This permission is under active
     * development and may be changed or removed.)
     */
    REQUEST_TO_SPEAK(1L << 32),
    /**
     * Allows for creating, editing, and deleting scheduled events.
     */
    MANAGE_EVENTS(1L << 33),
    /**
     * Allows for deleting and archiving threads, and viewing all private threads.
     */
    MANAGE_THREADS(1L << 34),
    /**
     * Allows for creating public and announcement threads.
     */
    CREATE_PUBLIC_THREADS(1L << 35),
    /**
     * Allows for creating private threads.
     */
    CREATE_PRIVATE_THREADS(1L << 36),
    /**
     * Allows the usage of custom stickers from other servers.
     */
    USE_EXTERNAL_STICKERS(1L << 37),
    /**
     * Allows for sending messages in threads.
     */
    SEND_MESSAGES_IN_THREADS(1L << 38),
    /**
     * Allows for using Activities (applications with the EMBEDDED flag) in a voice channel
     */
    USE_EMBEDDED_ACTIVITIES(1L << 39),
    /**
     * Allows for timing out users to prevent them from sending or reacting to message in chat and
     * threads, and from speaking in voice and stage channels
     */
    MODERATE_MEMBERS(1L << 40),
    /**
     * For future use or invalid permissions.
     */
    UNKNOWN(-1);


    private final long value;

    Permission(long value) {
        this.value = value;
    }

    public static @NotNull Permission getPermission(int value) {
        for (Permission p : values()) {
            if (p.getValue() == value) {
                return p;
            }
        }
        return UNKNOWN;
    }

    public static @NotNull Permission @NotNull [] getPermissions(int value) {
        List<Permission> permissions = new ArrayList<>();
        for (Permission p : values()) {
            if ((value & p.getValue()) == p.getValue()) {
                permissions.add(p);
            }
        }
        return permissions.toArray(new Permission[0]);
    }

    public static @NotNull Permission getPermission(long value) {
        for (Permission p : values()) {
            if (p.getValue() == value) {
                return p;
            }
        }
        return UNKNOWN;
    }

    public static @NotNull Permission[] getPermissions(long value) {
        List<Permission> permissions = new ArrayList<>();
        for (Permission p : values()) {
            if ((p.getValue() & value) == p.getValue()) {
                permissions.add(p);
            }
        }
        return permissions.toArray(new Permission[0]);
    }

    public static boolean contains(@Nullable Permission permission) {
        return permission != null && permission != UNKNOWN;
    }

    /**
     * @return gets all the permissions as a long.
     */
    public static long getAllPermissions() {
        long value = 0;
        for (Permission p : values()) {
            value |= p.getValue();
        }
        return value;
    }

    /**
     * @return gets all the permissions as a long, except for the unknown permission.
     */
    public static long getAllPermissionsExceptUnknown() {
        long value = 0;
        for (Permission p : values()) {
            if (p != UNKNOWN) {
                value |= p.getValue();
            }
        }
        return value;
    }

    /**
     * Gets the given value as a string.
     */
    public static @NotNull Permission valueOf(long value) {
        for (Permission type : Permission.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static @NotNull Permission valueOfAsString(String value) {
        for (Permission type : Permission.values()) {
            if (type.getValueAsString().equals(value)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public long getValue() {
        return value;
    }

    public @NotNull String getValueAsString() {
        return Long.toUnsignedString(value);
    }

}

