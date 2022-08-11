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
package io.github.realyusufismail.ydw.event.events;

import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.realyusufismail.ydw.YDW;
import io.github.realyusufismail.ydw.event.Event;

public class DisconnectEvent extends Event {
    private final WebSocketFrame serverCloseFrame;
    private final WebSocketFrame clientCloseFrame;
    private final boolean closedByServer;

    public DisconnectEvent(YDW ydw, WebSocketFrame serverCloseFrame,
            WebSocketFrame clientCloseFrame, boolean closedByServer) {
        super(ydw);
        this.serverCloseFrame = serverCloseFrame;
        this.clientCloseFrame = clientCloseFrame;
        this.closedByServer = closedByServer;
    }

    public WebSocketFrame getServerCloseFrame() {
        return serverCloseFrame;
    }

    public WebSocketFrame getClientCloseFrame() {
        return clientCloseFrame;
    }

    public boolean isClosedByServer() {
        return closedByServer;
    }
}
