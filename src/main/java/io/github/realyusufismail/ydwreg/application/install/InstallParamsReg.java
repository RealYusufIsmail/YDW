/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

        if (perm.hasNonNull("scopes")) {
            for (JsonNode scope : perm.get("scopes")) {
                scopes.add(OAuth2Scopes.valueOf(scope.asText()));
            }
        }

        permissions = perm.hasNonNull("permissions")
                ? Permission.getPermissions(perm.get("permissions").asInt())
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
