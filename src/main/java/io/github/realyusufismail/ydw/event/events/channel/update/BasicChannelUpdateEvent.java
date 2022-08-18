/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydw.event.events.channel.update;

import io.github.realyusufismail.event.updater.IEventUpdate;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.guild.GuildChannel;
import io.github.realyusufismail.ydw.event.Event;

public class BasicChannelUpdateEvent<T> extends Event implements IEventUpdate<YDW, T> {
    private final Channel channel;
    private final T oldValue;
    private final T newValue;

    public BasicChannelUpdateEvent(YDW ydw, Channel channel, T oldValue, T newValue) {
        super(ydw);
        this.channel = channel;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Channel getChannel() {
        if (channel instanceof GuildChannel)
            return (GuildChannel) channel;
        else
            return channel;
    }

    @Override
    public T getOldValue() {
        return oldValue;
    }

    @Override
    public T getNewValue() {
        return newValue;
    }
}
