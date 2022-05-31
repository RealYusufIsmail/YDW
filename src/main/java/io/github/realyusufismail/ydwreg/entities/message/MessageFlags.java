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

package io.github.realyusufismail.ydwreg.entities.message;

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

    public static @NotNull MessageFlags[] fromValues(@NotNull int... values) {
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
