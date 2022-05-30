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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.message;

import org.jetbrains.annotations.NotNull;

public enum MessageFlags {
    /**
     * this message has been published to subscribed channels (via Channel Following)
     */
    CROSSPOSTED(1),
    /**
     * this message originated from a message in another channel (via Channel Following)
     */
    IS_CROSSPOST(1 << 1),
    /**
     * do not include any embeds when serializing this message
     */
    SUPPRESS_EMBEDS(1 << 2),
    /**
     * do not include any embeds when serializing this message
     */
    SOURCE_MESSAGE_DELETED(1 << 3),
    /**
     * this message came from the urgent message system
     */
    URGENT(1 << 4),
    /**
     * this message has an associated thread, with the same id as the message
     */
    HAS_THREAD(1 << 5),
    /**
     * this message is only visible to the user who invoked the Interaction
     */
    EPHEMERAL(1 << 6),
    /**
     * this message is an Interaction Response and the bot is "thinking"
     */
    LOADING(1 << 7),
    /**
     * this message failed to mention some roles and add their members to the thread
     */
    FAILED_TO_MENTION_SOME_ROLES_IN_THREAD(1 << 8),
    /**
     * this message is a response to another message
     */
    UNKNOWN(1 << 9);

    private final int value;

    MessageFlags(int value) {
        this.value = value;
    }

    public static @NotNull MessageFlags fromValue(int value) {
        for (MessageFlags flag : values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        return UNKNOWN;
    }

    public static @NotNull MessageFlags @NotNull [] fromValues(int @NotNull... values) {
        MessageFlags[] flags = new MessageFlags[values.length];
        for (int i = 0; i < values.length; i++) {
            flags[i] = fromValue(values[i]);
        }
        return flags;
    }

    public int getValue() {
        return value;
    }
}
