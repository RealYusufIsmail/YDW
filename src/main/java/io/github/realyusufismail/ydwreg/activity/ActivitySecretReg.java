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
