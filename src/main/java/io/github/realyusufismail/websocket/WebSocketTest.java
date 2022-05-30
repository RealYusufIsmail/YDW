package io.github.realyusufismail.websocket;

import io.github.realyusufismail.yusufsdiscordbot.ydl.YDL;
import io.github.realyusufismail.yusufsdiscordbot.ydl.YDLConnector;

public class WebSocketTest {
    public static void main(String[] args) throws Exception {
        YDL ydl =
                YDLConnector.setUpBot("OTMxOTE1NjYxNTMyMzYwNzA0.YeLYFw.IPXJqKyLKnE-SpcPqsr5Vn7d148")
                    .build();
    }
}
