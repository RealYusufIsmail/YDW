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

package io.github.realyusufismail.ydwreg.activity;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.activity.ActivitySecret;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ActivitySecretReg implements ActivitySecret {

    private final String join;
    private final String spectate;
    private final String match;

    public ActivitySecretReg(@NotNull JsonNode secret) {
        this.join = secret.hasNonNull("join") ? secret.get("join").asText() : null;
        this.spectate = secret.hasNonNull("spectate") ? secret.get("spectate").asText() : null;
        this.match = secret.hasNonNull("match") ? secret.get("match").asText() : null;
    }

    @NotNull
    @Override
    public Optional<String> getJoin() {
        return Optional.ofNullable(join);
    }

    @NotNull
    @Override
    public Optional<String> getSpectate() {
        return Optional.ofNullable(spectate);
    }

    @NotNull
    @Override
    public Optional<String> getMatch() {
        return Optional.ofNullable(match);
    }
}
