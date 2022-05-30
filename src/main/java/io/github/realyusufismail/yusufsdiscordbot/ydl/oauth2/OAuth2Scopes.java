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

package io.github.realyusufismail.yusufsdiscordbot.ydl.oauth2;

public enum OAuth2Scopes {
    /**
     * allows your app to fetch data from a user's "Now Playing/Recently Played" list - requires
     * Discord approval.
     */
    ACTIVITIES_READ("activities.read"),
    /**
     * allows your app to update a user's activity - requires Discord approval (NOT REQUIRED FOR
     * GAMESDK ACTIVITY MANAGER).
     */
    ACTIVITIES_WRITE("activities.write"),
    /**
     * allows your app to read build data for a user's applications.
     */
    APPLICATIONS_BUILDS_READ("applications.builds.read"),
    /**
     * allows your app to upload/update builds for a user's applications - requires Discord
     * approval.
     */
    APPLICATIONS_BUILDS_UPLOAD("applications.builds.upload"),
    /**
     * allows your app to use commands in a guild.
     */
    APPLICATIONS_COMMANDS("applications.commands"),
    /**
     * allows your app to update its commands using a Bearer token - client credentials grant only.
     */
    APPLICATIONS_COMMANDS_UPDATE("applications.commands.update"),
    /**
     * allows your app to update permissions for its commands in a guild a user has permissions to.
     */
    APPLICATIONS_COMMANDS_PERMISSIONS_UPDATE("applications.commands.permissions.update"),
    /**
     * allows your app to read entitlements for a user's applications.
     */
    APPLICATIONS_ENTITLEMENTS("applications.entitlements"),
    /**
     * allows your app to read and update store data (SKUs, store listings, achievements, etc.) for
     * a user's applications.
     */
    APPLICATIONS_STORE_UPDATE("applications.store.update"),
    /**
     * for oauth2 bots, this puts the bot in the user's selected guild by default.
     */
    BOT("bot"),
    /**
     * allows /users/@me/connections to return linked third-party accounts.
     */
    CONNECTIONS("connections"),
    /**
     * allows your app to see information about the user's DMs and group DMs - requires Discord
     * approval.
     */
    DM_CHANNEL_READ("dm_channels.read"),
    /**
     * enables /users/@me to return an email.
     */
    EMAIL("email"),
    /**
     * allows your app to join users to a group dm.
     */
    GDM_JOIN("gdm.join"),
    /**
     * allows /users/@me/guilds to return basic information about all of a user's guilds.
     */
    GUILDS("guilds"),
    /**
     * allows /guilds/{guild.id}/members/{user.id} to be used for joining users to a guild.
     */
    GUILDS_JOIN("guilds.join"),
    /**
     * allows /users/@me/guilds/{guild.id}/member to return a user's member information in a guild.
     */
    GUILDS_MEMBERS_READ("guilds.members.read"),
    /**
     * allows /users/@me without email.
     */
    IDENTIFY("identify"),
    /**
     * for local rpc server api access, this allows you to read messages from all client channels
     * (otherwise restricted to channels/guilds your app creates).
     */
    MESSAGE_READ("message.read"),
    /**
     * allows your app to know a user's friends and implicit relationships - requires Discord
     * approval.
     */
    RELATIONSHIPS_READ("relationships.read"),
    /**
     * for local rpc server access, this allows you to control a user's local Discord client -
     * requires Discord approval.
     */
    RPC("rpc"),
    /**
     * for local rpc server access, this allows you to update a user's activity - requires Discord
     * approval.
     */
    RPC_ACTIVITIES_WRITE("rpc.activities.write"),
    /**
     * for local rpc server access, this allows you to receive notifications pushed out to the user
     * - requires Discord approval.
     */
    RPC_NOTIFICATIONS_READ("rpc.notifications.read"),
    /**
     * for local rpc server access, this allows you to read a user's voice settings and listen for
     * voice events - requires Discord approval.
     */
    RPC_VOICE_READ("rpc.voice.read"),
    /**
     * for local rpc server access, this allows you to update a user's voice settings - requires
     * Discord approval.
     */
    RPC_VOICE_WRITE("rpc.voice.write"),
    /**
     * allows your app to connect to voice on user's behalf and see all the voice members - requires
     * Discord approval.
     */
    VOICE("voice"),
    /**
     * this generates a webhook that is returned in the oauth token response for authorization code
     * grants.
     */
    WEBHOOK_INCOMING("webhook.incoming"),
    /**
     * For future use.
     */
    UNKNOWN("unknown");

    private final String name;

    OAuth2Scopes(String name) {
        this.name = name;
    }

    public static OAuth2Scopes fromString(String name) {
        for (OAuth2Scopes s : OAuth2Scopes.values()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return UNKNOWN;
    }

    public String getName() {
        return name;
    }
}
