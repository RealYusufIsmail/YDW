package io.github.realyusufismail.ydw.event.events.channel;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;

public class ChannelDeleteEvent extends BasicChannelEvent {
    public ChannelDeleteEvent(YDW ydw, Channel channel) {
        super(ydw, channel);
    }
}
