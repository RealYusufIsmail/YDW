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

package io.github.realyusufismail.ydw.application.install;

import io.github.realyusufismail.ydw.oauth2.OAuth2Scopes;
import io.github.realyusufismail.ydw.perm.Permission;

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