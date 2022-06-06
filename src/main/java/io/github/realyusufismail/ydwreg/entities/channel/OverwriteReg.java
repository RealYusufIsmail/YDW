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

package io.github.realyusufismail.ydwreg.entities.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.channel.Overwrite;
import org.jetbrains.annotations.NotNull;

public class OverwriteReg implements Overwrite {
    @NotNull
    private final JsonNode json;
    private final long id;
    private final YDW ydw;

    private Overwrite.OverwriteType type;
    private String allow;
    private String deny;

    public OverwriteReg(@NotNull JsonNode json, long id, YDW ydw) {
        this.json = json;
        this.id = id;
        this.ydw = ydw;

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
