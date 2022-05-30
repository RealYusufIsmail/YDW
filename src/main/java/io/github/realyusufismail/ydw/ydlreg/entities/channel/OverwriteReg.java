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

package io.github.realyusufismail.ydw.ydlreg.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.guild.channel.Overwrite;
import org.jetbrains.annotations.NotNull;

public class OverwriteReg implements Overwrite {
    @NotNull
    private final JsonNode json;
    private final long id;
    private final YDL ydl;

    private Overwrite.OverwriteType type;
    private String allow;
    private String deny;

    public OverwriteReg(@NotNull JsonNode json, long id, YDL ydl) {
        this.json = json;
        this.id = id;
        this.ydl = ydl;

        this.setType(OverwriteType.getOverwriteType(json.get("type").asInt()));
        this.setAllow(json.get("allow").asText());
        this.setDeny(json.get("deny").asText());
    }

    @NotNull
    @Override
    public Long getIdLong() {
        return id;
    }

    @Override
    public OverwriteType getType() {
        return type;
    }

    @NotNull
    private OverwriteReg setType(OverwriteType type) {
        this.type = type;
        return this;
    }

    @Override
    public String getAllow() {
        return allow;
    }

    @NotNull
    private OverwriteReg setAllow(String allow) {
        this.allow = allow;
        return this;
    }

    @Override
    public String getDeny() {
        return deny;
    }

    @NotNull
    private OverwriteReg setDeny(String deny) {
        this.deny = deny;
        return this;
    }
}
