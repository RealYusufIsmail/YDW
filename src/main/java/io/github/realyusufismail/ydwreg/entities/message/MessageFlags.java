/*
 *
 *  * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
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
