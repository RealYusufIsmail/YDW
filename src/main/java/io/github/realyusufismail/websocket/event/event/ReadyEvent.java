package io.github.realyusufismail.websocket.event.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.realyusufismail.websocket.WebSocketManager;
import io.github.realyusufismail.websocket.event.Event;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.UnavailableGuild;
import yusufsdiscordbot.ydlreg.entities.UnavailableGuildReg;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReadyEvent extends Event {

    public ReadyEvent(JsonNode json, YDL ydl) {
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
    }


}
