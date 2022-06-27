package io.github.realyusufismail.ydw.event.events.channel;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.event.Event;

public class BasicChannelEvent extends Event {
    private final Channel channel;

    public BasicChannelEvent(YDW ydw, Channel channel) {
        super(ydw);
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
}
