package io.github.realyusufismail.websocket.event.events;

import io.github.realyusufismail.websocket.event.Event;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.entities.UnavailableGuild;

public class ReadyEvent extends Event {
    public ReadyEvent(YDL ydl) {
        super(ydl);
    }
}
