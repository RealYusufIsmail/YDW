/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.AvailableGuild;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.SelfUser;
import io.github.realyusufismail.ydw.entities.UnavailableGuild;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;
import io.github.realyusufismail.ydwreg.entities.AvailableGuildReg;
import io.github.realyusufismail.ydwreg.entities.SelfUserReg;
import io.github.realyusufismail.ydwreg.entities.UnavailableGuildReg;
import io.github.realyusufismail.ydwreg.handle.Handle;

import java.util.ArrayList;
import java.util.List;

public class ReadyHandler extends Handle {

    public ReadyHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        List<UnavailableGuild> unavailableGuilds = new ArrayList<>();
        ArrayNode guilds = (ArrayNode) json.get("guilds");
        for (JsonNode guild : guilds) {
            if (guild.get("unavailable").asBoolean()) {
                UnavailableGuild unavailableGuild =
                        new UnavailableGuildReg(ydw, guild.get("id").asLong(), guild);
                unavailableGuilds.add(unavailableGuild);
            }
        }

        List<AvailableGuild> availableGuilds = new ArrayList<>();
        for (JsonNode guild : guilds) {
            if (!guild.get("unavailable").asBoolean()) {
                AvailableGuild availableGuild =
                        new AvailableGuildReg(ydw, guild.get("id").asLong(), guild);
                availableGuilds.add(availableGuild);
            }
        }

        List<Guild> guild = new ArrayList<>();
        for (UnavailableGuild unavailableGuild1 : unavailableGuilds) {
            var guild1 = ydw.getGuild(unavailableGuild1.getId());
            guild.add(guild1);
        }

        for (AvailableGuild availableGuild1 : availableGuilds) {
            var guild1 = ydw.getGuild(availableGuild1.getId());
            guild.add(guild1);
        }

        // updateGuildChannels(guild);

        ydw.getWebSocket()
            .setSessionId(json.hasNonNull("session_id") ? json.get("session_id").asText() : null);
        ydw.setApplicationId(json.get("application").get("id").asLong());

        SelfUser selfUser =
                new SelfUserReg(json.get("user"), json.get("user").get("id").asLong(), ydw);
        ydw.setSelfUser(selfUser);
        ydw.setGuilds(guild);
        ydw.setReady(true);
        ydw.getWebSocket().setReconnectTimeoutS(2);
        ydw.handelEvent(new ReadyEvent(ydw, unavailableGuilds.size(), availableGuilds.size()));
    }
}
