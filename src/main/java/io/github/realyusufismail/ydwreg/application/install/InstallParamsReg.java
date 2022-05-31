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

package io.github.realyusufismail.ydwreg.application.install;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.application.install.InstallParams;
import io.github.realyusufismail.ydw.oauth2.OAuth2Scopes;
import io.github.realyusufismail.ydw.perm.Permission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class InstallParamsReg implements InstallParams {

    private final EnumSet<OAuth2Scopes> scopes = EnumSet.noneOf(OAuth2Scopes.class);
    @Nullable
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

    @NotNull
    @Override
    public EnumSet<OAuth2Scopes> getScopes() {
        return scopes;
    }

    @Override
    public Permission[] getPermissions() {
        return permissions;
    }
}
