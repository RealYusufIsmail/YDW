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

package io.github.realyusufismail.yusufsdiscordbot.ydlreg.application.install;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import io.github.realyusufismail.yusufsdiscordbot.ydl.application.install.InstallParams;
import io.github.realyusufismail.yusufsdiscordbot.ydl.oauth2.OAuth2Scopes;
import io.github.realyusufismail.yusufsdiscordbot.ydl.perm.Permission;

import java.util.EnumSet;

public class InstallParamsReg implements InstallParams {

    private final EnumSet<OAuth2Scopes> scopes = EnumSet.noneOf(OAuth2Scopes.class);
    private final Permission[] permissions;

    public InstallParamsReg(@NotNull JsonNode perm) {

        if (perm.has("scopes")) {
            for (JsonNode scope : perm.get("scopes")) {
                scopes.add(OAuth2Scopes.valueOf(scope.asText()));
            }
        }

        permissions =
                perm.has("permissions") ? Permission.getPermissions(perm.get("permissions").asInt())
                        : null;
    }

    @Override
    public EnumSet<OAuth2Scopes> getScopes() {
        return scopes;
    }

    @Override
    public Permission[] getPermissions() {
        return permissions;
    }
}
