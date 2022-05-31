package io.github.realyusufismail.ydw.websocket.handle.handles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.realyusufismail.ydw.websocket.WebSocketManager;
import io.github.realyusufismail.ydw.websocket.handle.Handle;
import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.entities.SelfUser;
import io.github.realyusufismail.ydw.ydl.entities.UnavailableGuild;
import io.github.realyusufismail.ydw.ydlreg.entities.SelfUserReg;
import io.github.realyusufismail.ydw.ydlreg.entities.UnavailableGuildReg;

import java.util.ArrayList;
import java.util.List;

public class ReadyHandler extends Handle {

    public ReadyHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
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
                        new UnavailableGuildReg(ydl, guild.get("id").asLong(), guild);
                unavailableGuilds.add(unavailableGuild);
            }
        }
        ydl.setUnavailableGuilds(unavailableGuilds);

        SelfUser selfUser =
                new SelfUserReg(json.get("user"), json.get("user").get("id").asLong(), ydl);
        ydl.setSelfUser(selfUser);
    }
}