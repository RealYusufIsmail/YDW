package io.github.realyusufismail.websocket;

import com.neovisionaries.ws.client.WebSocketException;

import java.io.IOException;

public class WebSocketTest {
    public static void main(String[] args) throws WebSocketException, IOException {
        new WebSocketManager("OTMxOTE1NjYxNTMyMzYwNzA0.YeLYFw.IPXJqKyLKnE-SpcPqsr5Vn7d148",
                GateWayIntent.DEFAULT_INTENTS);
    }
}
