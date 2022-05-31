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

package io.github.realyusufismail.ydw.ydl.application.install;

import io.github.realyusufismail.ydw.ydl.oauth2.OAuth2Scopes;
import io.github.realyusufismail.ydw.ydl.perm.Permission;

import java.util.EnumSet;

public interface InstallParams {

    /**
     * Gets the scopes required when adding the bot to a server.
     *
     * @return The scopes required.
     */
    EnumSet<OAuth2Scopes> getScopes();

    /**
     * The permissions required for the bots' role.
     *
     * @return The permissions required.
     */
    Permission[] getPermissions();
}