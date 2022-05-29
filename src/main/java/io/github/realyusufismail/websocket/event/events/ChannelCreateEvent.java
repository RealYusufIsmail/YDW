package io.github.realyusufismail.websocket.event.events;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.event.Event;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.guild.Channel;
import yusufsdiscordbot.ydlreg.entities.guild.ChannelReg;

public class ChannelCreateEvent extends Event {
    public ChannelCreateEvent(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {
        Channel channel = new ChannelReg(json.get("d"), json.get("d").get("id").asLong(), ydl);
        // TODO: Add channel to guild
    }
}
