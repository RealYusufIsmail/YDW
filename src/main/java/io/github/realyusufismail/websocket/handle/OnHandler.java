/*
 * GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/> Everyone is permitted to
 * copy and distribute verbatim copies of this license document, but changing it is not allowed.
 *
 * Yusuf Arfan Ismail Copyright (C) 2022 - future.
 *
 * The GNU General Public License is a free, copyleft license for software and other kinds of works.
 *
 * You may copy, distribute and modify the software as long as you track changes/dates in source
 * files. Any modifications to or software including (via compiler) GPL-licensed code must also be
 * made available under the GPL along with build & install instructions.
 *
 * You can find more details here https://github.com/RealYusufIsmail/YDW/LICENSE
 */

package io.github.realyusufismail.websocket.handle;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.websocket.EventNames;
import io.github.realyusufismail.websocket.handle.handles.*;
import io.github.realyusufismail.websocket.handle.handles.channel.ChannelCreateHandler;
import io.github.realyusufismail.websocket.handle.handles.channel.ChannelDeleteHandler;
import io.github.realyusufismail.websocket.handle.handles.channel.ChannelPinsUpdateHandler;
import io.github.realyusufismail.websocket.handle.handles.channel.ChannelUpdateHandler;
import io.github.realyusufismail.websocket.handle.handles.thread.ThreadCreateHandler;
import io.github.realyusufismail.websocket.handle.handles.thread.ThreadDeleteHandler;
import io.github.realyusufismail.websocket.handle.handles.thread.ThreadUpdateHandler;
import io.github.realyusufismail.ydwreg.YDWReg;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class OnHandler {
    private final YDWReg ydw;
    private final JsonNode json;
    private final String event;

    public OnHandler(YDWReg ydw, String event, JsonNode json) {
        this.ydw = ydw;
        this.json = json;
        this.event = event;
    }

    public void start() {
        Optional<EventNames> eventName = Optional.of(EventNames.getEvent(event));
        eventName.ifPresent(name -> fire(name, json));
    }

    public void fire(@NotNull EventNames event, JsonNode json) {
        switch (event) {
            case READY -> new ReadyHandler(json, ydw).start();
            case RESUMED -> new ResumedHandler(json, ydw).start();
            case RECONNECT -> new ReconnectHandler(json, ydw).start();
            case INVALID_SESSION -> new InvalidSessionHandler(json, ydw).start();
            case APPLICATION_COMMAND_PERMISSIONS_UPDATE -> new ApplicationCommandPermissionsUpdateHandler(
                    json, ydw).start();
            case CHANNEL_CREATE -> new ChannelCreateHandler(json, ydw).start();
            case CHANNEL_UPDATE -> new ChannelUpdateHandler(json, ydw).start();
            case CHANNEL_DELETE -> new ChannelDeleteHandler(json, ydw).start();
            case CHANNEL_PINS_UPDATE -> new ChannelPinsUpdateHandler(json, ydw).start();
            case THREAD_CREATE -> new ThreadCreateHandler(json, ydw).start();
            case THREAD_UPDATE -> new ThreadUpdateHandler(json, ydw).start();
            case THREAD_DELETE -> new ThreadDeleteHandler(json, ydw).start();

        }
    }
}
