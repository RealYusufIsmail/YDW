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

package io.github.realyusufismail.websocket;

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
 * <p>
 * Events under the GUILD_PRESENCES and GUILD_MEMBERS intents are turned off by default on all
 * gateway versions. If you are using Gateway v6, you will receive those events if you are
 * authorized to receive them and have enabled the intents in the Developer Portal. You do not need
 * to use Intents on Gateway v6 to receive these events; you just need to enable the flags."
 * </p>
 */
public enum GateWayIntent {
    GUILD_MEMBERS(1, true),
    GUILD_BANS(2),
    GUILD_EMOJIS(3),
    GUILD_INTEGRATIONS(4),
    GUILD_WEBHOOKS(5),
    GUILD_INVITES(6),
    GUILD_VOICE_STATES(7),
    GUILD_PRESENCES(8, true),
    GUILD_MESSAGES(9),
    GUILD_MESSAGE_REACTIONS(10),
    GUILD_MESSAGE_TYPING(11),
    DIRECT_MESSAGES(12),
    DIRECT_MESSAGE_REACTIONS(13),
    DIRECT_MESSAGE_TYPING(14),
    GUILD_SCHEDULED_EVENTS(15);

    private final int value;
    private final boolean privileged;

    GateWayIntent(int value) {
        this(value, false);
    }

    GateWayIntent(int value, boolean privileged) {
        this.value = value;
        this.privileged = privileged;
    }

    public int getValue() {
        return value;
    }

    public static @Nullable GateWayIntent fromInt(int i) {
        for (GateWayIntent intent : values()) {
            if (intent.getValue() == i) {
                return intent;
            }
        }
        return null;
    }

    public static final int ALL_INTENTS = 1 | getValue(EnumSet.allOf(GateWayIntent.class));

    public static final int DEFAULT_INTENTS = ALL_INTENTS & ~getValue(GUILD_MEMBERS,
            GUILD_PRESENCES, GUILD_WEBHOOKS, GUILD_MESSAGE_TYPING, DIRECT_MESSAGE_TYPING);

    public static int getValue(@NotNull Collection<GateWayIntent> set) {
        int value = 0;
        for (GateWayIntent intent : set)
            value |= intent.value;
        return value;
    }

    public static int getValue(GateWayIntent intent, GateWayIntent... intents) {
        return getValue(EnumSet.of(intent, intents));
    }
}
