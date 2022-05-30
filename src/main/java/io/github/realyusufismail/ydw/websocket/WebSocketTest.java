package io.github.realyusufismail.ydw.websocket;

import io.github.realyusufismail.ydw.ydl.YDL;
import io.github.realyusufismail.ydw.ydl.YDLConnector;

public class WebSocketTest {
    public static void main(String[] args) throws Exception {
        YDL ydl =
                YDLConnector.setUpBot("OTMxOTE1NjYxNTMyMzYwNzA0.YeLYFw.IPXJqKyLKnE-SpcPqsr5Vn7d148")
                    .build();
    }
}
