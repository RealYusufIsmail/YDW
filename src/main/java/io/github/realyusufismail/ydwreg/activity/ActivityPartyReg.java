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
import io.github.realyusufismail.ydw.activity.ActivityParty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ActivityPartyReg implements ActivityParty {

    private final String id;
    private final Map<Integer, Integer> size = new HashMap<>();

    public ActivityPartyReg(@NotNull JsonNode party) {
        this.id = party.hasNonNull("id") ? party.get("id").asText() : null;

        if (party.hasNonNull("size")) {
            party.get("size").forEach(sizeNode -> {
                size.put(sizeNode.get("size").asInt(), sizeNode.get("count").asInt());
            });
        }
    }

    @NotNull
    @Override
    public Map<Integer, Integer> getSize() {
        return size;
    }

    @Nullable
    @Override
    public Long getIdLong() {
        return id != null ? Long.parseLong(id) : null;
    }
}
