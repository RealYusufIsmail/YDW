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

package io.github.realyusufismail.websocket.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.websocket.handle.Handle;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.SelfUser;
import io.github.realyusufismail.ydw.entities.UnavailableGuild;
import io.github.realyusufismail.ydwreg.entities.SelfUserReg;
import io.github.realyusufismail.ydwreg.entities.UnavailableGuildReg;

import java.util.ArrayList;
import java.util.List;

public class ReadyHandler extends Handle {

    public ReadyHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        String sessionId = json.get("session_id").asText();
        WebSocketManager.setSessionId(sessionId);

        List<UnavailableGuild> unavailableGuilds = new ArrayList<>();
        ArrayNode guilds = (ArrayNode) json.get("guilds");
        for (JsonNode guild : guilds) {
            if (guild.get("unavailable").asBoolean()) {
                UnavailableGuild unavailableGuild =
                        new UnavailableGuildReg(ydw, guild.get("id").asLong(), guild);
                unavailableGuilds.add(unavailableGuild);
            }
        }
        ydw.setUnavailableGuilds(unavailableGuilds);

        SelfUser selfUser =
                new SelfUserReg(json.get("user"), json.get("user").get("id").asLong(), ydw);
        ydw.setSelfUser(selfUser);
    }
}
