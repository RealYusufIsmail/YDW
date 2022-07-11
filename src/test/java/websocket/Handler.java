package websocket;

import io.github.realyusufismail.event.adapter.EventAdapter;
import io.github.realyusufismail.ydw.event.events.GatewayPingEvent;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;
import io.github.realyusufismail.ydw.event.events.ReconnectEvent;

public class Handler extends EventAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Ready and the total guilds are: " + event.getNumberOfGuilds());
    }

    @Override
    public void onGatewayPing(GatewayPingEvent event) {
        System.out.println("Gateway ping is: " + event.getNewPing());
    }

    @Override
    public void onReconnect(ReconnectEvent event) {
        System.out.println("Reconnecting...");
    }
}
