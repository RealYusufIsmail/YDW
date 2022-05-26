package io.github.realyusufismail.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.*;

import java.io.IOException;

public class WebSocketManager extends WebSocketAdapter implements WebSocketListener {
    private String gatewayUrl = "wss://gateway.discord.gg/?v=9&encoding=json";
    // Create a WebSocketFactory instance.
    WebSocket ws = new WebSocketFactory().createSocket(gatewayUrl);
    ObjectMapper mapper = new ObjectMapper();
    

    public WebSocketManager() throws IOException, WebSocketException {
        ws.addListener(this);
        ws.connect();
    }

    @Override
    public void onTextMessage(WebSocket websocket, byte[] data) throws Exception {
        String dataAsString = new String(data);
        String payload = mapper.writeValueAsString(dataAsString);
    }
}
