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

package io.github.realyusufismail.ydw.ydlreg.entities.guild;

import org.jetbrains.annotations.Nullable;

public enum GuildFeatures {
    /**
     * guild has access to set an animated guild banner image
     */
    ANIMATED_BANNER("ANIMATED_BANNER"),
    /**
     * guild has access to set a guild splash image
     */
    ANIMATED_ICON("ANIMATED_ICON"),
    /**
     * guild has access to set a guild banner image
     */
    BANNER("BANNER"),
    /**
     * guild has access to use commerce features (i.e. create store channels)
     */
    COMMERCE("COMMERCE"),
    /**
     * guild can enable welcome screen, Membership Screening, stage channels and discovery, and
     * receives community updates
     */
    COMMUNITY("COMMUNITY"),
    /**
     * guild is able to be discovered in the directory
     */
    DISCOVERABLE("DISCOVERABLE"),
    /**
     * guild is able to be featured in the directory
     */
    FEATURABLE("FEATURABLE"),
    /**
     * guild has access to set an invite splash background
     */
    INVITE_SPLASH("INVITE_SPLASH"),
    /**
     * guild has enabled Membership Screening
     */
    MEMBER_VERIFICATION_GATE_ENABLED("MEMBER_VERIFICATION_GATE_ENABLED"),
    /**
     * guild has enabled monetization
     */
    MONETIZATION_ENABLED("MONETIZATION_ENABLED"),
    /**
     * guild has increased custom sticker slots
     */
    MORE_STICKERS("MORE_STICKERS"),
    /**
     * guild has access to create news channels
     */
    NEWS("NEWS"),
    /**
     * guild is partnered
     */
    PARTNERED("PARTNERED"),
    /**
     * guild can be previewed before joining via Membership Screening or the directory
     */
    PREVIEW_ENABLED("PREVIEW_ENABLED"),
    /**
     * guild has access to create private threads
     */
    PRIVATE_THREADS("PRIVATE_THREADS"),
    /**
     * guild is able to set role icons
     */
    ROLE_ICONS("ROLE_ICONS"),
    /**
     * guild has access to the seven day archive time for threads
     */
    SEVEN_DAY_THREAD_ARCHIVE("SEVEN_DAY_THREAD_ARCHIVE"),
    /**
     * guild has access to the three day archive time for threads
     */
    THREE_DAY_THREAD_ARCHIVE("THREE_DAY_THREAD_ARCHIVE"),
    /**
     * guild has enabled ticketed events
     */
    TICKETED_EVENTS_ENABLED("TICKETED_EVENTS_ENABLED"),
    /**
     * guild has access to set a vanity URL
     */
    VANITY_URL("VANITY_URL"),
    /**
     * guild is verified.
     */
    VERIFIED("VERIFIED"),
    /**
     * guild has access to set 384kbps bitrate in voice (previously VIP voice servers)
     */
    VIP_REGIONS("VIP_REGIONS"),
    /**
     * guild has enabled the welcome screen
     */
    WELCOME_SCREEN_ENABLED("WELCOME_SCREEN_ENABLED");

    private final String feature;

    GuildFeatures(String feature) {
        this.feature = feature;
    }

    public static @Nullable GuildFeatures getFeature(String feature) {
        for (GuildFeatures f : GuildFeatures.values()) {
            if (f.getFeature().equals(feature)) {
                return f;
            }
        }
        return null;
    }

    public String getFeature() {
        return feature;
    }
}
