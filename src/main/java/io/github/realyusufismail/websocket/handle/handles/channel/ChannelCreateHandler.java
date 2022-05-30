package io.github.realyusufismail.websocket.handle.handles.channel;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.handle.Handle;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.entities.guild.Channel;
import io.github.realyusufismail.yusufsdiscordbot.ydlreg.entities.guild.ChannelReg;

public class ChannelCreateHandler extends Handle {
    public ChannelCreateHandler(JsonNode json, YDL ydl) {
        super(json, ydl);
    }

    @Override
    public void start() {
        Channel channel = new ChannelReg(json.get("d"), json.get("d").get("id").asLong(), ydl);
        // TODO: Add channel to guild
    }
}
