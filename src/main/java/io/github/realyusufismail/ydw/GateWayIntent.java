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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.EnumSet;

/**
 * GUILD_MEMBERS and GUILD_MEMBERS : are a Privileged intent. <br>
 * As discord states : <br>
 * <p>
 * "To specify these intents in your IDENTIFY payload, you must visit your application page in the
 * Developer Portal and enable the toggle for each Privileged Intent that you wish to use. If your
 * bot qualifies for verification, you must first verify your bot and request access to these
 * intents during the verification process. If your bot is already verified and you need to request
 * additional privileged intents, contact support.
 * </p>
 */
public enum GateWayIntent {
    GUILD_MEMBERS(1),
    GUILD_BANS(2),
    GUILD_WEBHOOKS(5),

    GUILD_INVITES(6),
    GUILD_VOICE_STATES(7),
    GUILD_PRESENCES(8),
    GUILD_MESSAGES(9),

    GUILD_MESSAGE_REACTIONS(10),
    GUILD_MESSAGE_TYPING(11),
    DIRECT_MESSAGES(12),
    DIRECT_MESSAGE_REACTIONS(13),
    DIRECT_MESSAGE_TYPING(14),

    MESSAGE_CONTENT(15),
    AUTO_MODERATION_CONFIGURATION(20),
    AUTO_MODERATION_EXECUTION(21);


    public static final int ALL_INTENTS = 1 | getValue(EnumSet.allOf(GateWayIntent.class));
    public static final int DEFAULT_INTENTS =
            ALL_INTENTS & ~getValue(GUILD_MEMBERS, GUILD_PRESENCES, GUILD_WEBHOOKS,
                    GUILD_MESSAGE_TYPING, DIRECT_MESSAGE_TYPING, MESSAGE_CONTENT);
    private final int value;

    GateWayIntent(int value) {
        this.value = 1 << value;
    }

    public static @Nullable GateWayIntent fromInt(int i) {
        for (GateWayIntent intent : values()) {
            if (intent.getValue() == i) {
                return intent;
            }
        }
        return null;
    }

    public static int getValue(@NotNull Collection<GateWayIntent> set) {
        int value = 0;
        for (GateWayIntent intent : set)
            value |= intent.value;
        return value;
    }

    public static int getValue(@NotNull GateWayIntent intent, GateWayIntent... intents) {
        return getValue(EnumSet.of(intent, intents));
    }

    public int getValue() {
        return value;
    }
}
