package io.github.realyusufismail.ydw.event.events.channel;

import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.event.Event;

import java.time.ZonedDateTime;

public class ChannelPinsUpdateEvent extends Event {
    private final Guild guild;
    private final Channel channel;
    private final ZonedDateTime lastPinTime;

    public ChannelPinsUpdateEvent(YDW ydw, Guild guild, Channel channel,
            ZonedDateTime lastPinTime) {
        super(ydw);
        this.guild = guild;
        this.channel = channel;
        this.lastPinTime = lastPinTime;
    }

    public Guild getGuild() {
        return guild;
    }

    public Channel getChannel() {
        return channel;
    }

    public ZonedDateTime getLastPinTime() {
        return lastPinTime;
    }
}
