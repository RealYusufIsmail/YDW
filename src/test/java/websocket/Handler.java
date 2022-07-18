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
package websocket;

import io.github.realyusufismail.event.adapter.EventAdapter;
import io.github.realyusufismail.ydw.event.events.GatewayPingEvent;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;
import io.github.realyusufismail.ydw.event.events.ReconnectEvent;
import io.github.realyusufismail.ydw.event.events.channel.update.ChannelNameUpdateEvent;

public class Handler extends EventAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Ready and the total guilds are: " + event.getNumberOfGuilds());
    }

    @Override
    public void onReconnect(ReconnectEvent event) {
        System.out.println("Reconnecting...");
    }

    @Override
    public void onChannelNameUpdate(ChannelNameUpdateEvent event) {
        System.out.println("Channel name updated: " + event.getChannel().getName());
    }
}
