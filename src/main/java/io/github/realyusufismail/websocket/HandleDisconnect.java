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
package io.github.realyusufismail.websocket;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.realyusufismail.websocket.core.CloseCode;
import io.github.realyusufismail.ydw.event.events.DisconnectEvent;
import io.github.realyusufismail.ydw.event.events.ShutdownEvent;
import io.github.realyusufismail.ydwreg.YDWReg;
import org.joda.time.DateTime;

/**
 * <h1>Disconnections</h1>
 * <p>
 * If the gateway ever issues a disconnect to your client, it will provide a close event code that
 * you can use to properly handle the disconnection. A full list of these close codes can be found
 * in the Response Codes documentation.
 * <p>
 * When you close the connection to the gateway with the close code 1000 or 1001, your session will
 * be invalidated and your bot will appear offline. If you simply close the TCP connection, or use a
 * different close code, the bot session will remain active and timeout after a few minutes. This
 * can be useful for a reconnect, which will resume the previous session.
 */
public class HandleDisconnect extends WebSocketManager {
    private final WebSocket ws;
    private final WebSocketFrame serverCloseFrame;
    private final WebSocketFrame clientCloseFrame;
    private final boolean closedByServer;

    public HandleDisconnect(YDWReg ydw, WebSocket ws, WebSocketFrame serverCloseFrame,
            WebSocketFrame clientCloseFrame, boolean closedByServer) {
        super(ydw);
        this.ws = ws;
        this.serverCloseFrame = serverCloseFrame;
        this.clientCloseFrame = clientCloseFrame;
        this.closedByServer = closedByServer;
    }

    public void handle() {
        CloseCode closeCode = null;
        // Just used as a dummy variable.
        int rawCloseCode = 1000;
        // used to check for 1000 close code.
        canNotResume = false;

        if (heartbeatThread != null) {
            heartbeatThread.cancel(false);
            heartbeatThread = null;
        }

        if (closedByServer && serverCloseFrame != null) {
            rawCloseCode = serverCloseFrame.getCloseCode();
            String rawCloseReason = serverCloseFrame.getCloseReason();
            closeCode = CloseCode.fromCode(rawCloseCode);

            // fater way to check for all close code and throw exception
            int finalRawCloseCode = rawCloseCode;
            closeCode.forEach(code -> {
                if (code.getCode() == finalRawCloseCode) {
                    WebSocketManager.logger.error("Disconnected from gateway: {}",
                            code.getReason());
                }
            });
        } else if (clientCloseFrame != null) {
            rawCloseCode = clientCloseFrame.getCloseCode();
            if (rawCloseCode == 1000 && INVALID_SESSION.equals(clientCloseFrame.getCloseReason())) {
                canNotResume = true;
            }
        }

        boolean reconnectCloseCode = closeCode == null || closeCode.isReconnect();
        if (!needsToReconnect || !reconnectCloseCode || scheduler.isShutdown()) {
            ydwReg.handelEvent(new ShutdownEvent(ydwReg, DateTime.now(), rawCloseCode));
        } else {

            // code 1000 means resuming is impossible
            if (canNotResume) {
                invalidate();
                ydwReg.handelEvent(new DisconnectEvent(ydwReg, serverCloseFrame, clientCloseFrame,
                        closedByServer));
            }

            // will try to reconnect
            try {
                new HandleReconnect(super.ydwReg, this.ws, rawCloseCode).handle();
            } catch (Exception e) {
                WebSocketManager.logger.error("Error while reconnecting", e);
                invalidate();
                new HandleReconnect(super.ydwReg, this.ws, rawCloseCode).queueReconnect();
            }
        }
    }
}
