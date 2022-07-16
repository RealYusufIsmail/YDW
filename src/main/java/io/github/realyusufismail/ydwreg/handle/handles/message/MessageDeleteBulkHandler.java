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
package io.github.realyusufismail.ydwreg.handle.handles.message;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.entities.Channel;
import io.github.realyusufismail.ydw.entities.Guild;
import io.github.realyusufismail.ydw.entities.guild.Message;
import io.github.realyusufismail.ydw.event.events.message.MessageDeleteBulkEvent;
import io.github.realyusufismail.ydwreg.handle.Handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class MessageDeleteBulkHandler extends Handle {

    public MessageDeleteBulkHandler(JsonNode json, YDW ydw) {
        super(json, ydw);
    }

    @Override
    public void start() {
        List<Long> messageIds = new ArrayList<>();
        long channelId = json.get("channel_id").asLong();
        Optional<Guild> guild = Optional.ofNullable(ydw.getGuild(json.get("guild_id").asLong()));

        JsonNode messageIdsNode = json.get("ids");
        for (JsonNode messageIdNode : messageIdsNode) {
            messageIds.add(messageIdNode.asLong());
        }

        AtomicReference<Channel> channel = new AtomicReference<>();
        guild.ifPresent(g -> {
            channel.set((g.getChannel(channelId)));
        });


        List<Message> messages = new ArrayList<>();
        for (Long messageId : messageIds) {
            Message message;
            message = channel.get() != null ? channel.get().getMessage(messageId) : null;
            messages.add(message);
        }

        ydw.handelEvent(new MessageDeleteBulkEvent(ydw, messages, channel.get(), guild.get()));
    }
}
