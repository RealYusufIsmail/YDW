package io.github.realyusufismail.websocket;

import com.neovisionaries.ws.client.WebSocketException;
import io.github.realyusufismail.websocket.core.GateWayIntent;
import yusufsdiscordbot.ydl.YDL;
import yusufsdiscordbot.ydl.YDLConnector;

import java.io.IOException;

public class WebSocketTest {
    public static void main(String[] args) throws Exception {
        YDL ydl =
                YDLConnector.setUpBot("OTMxOTE1NjYxNTMyMzYwNzA0.YeLYFw.IPXJqKyLKnE-SpcPqsr5Vn7d148")
                    .build();
    }
}
