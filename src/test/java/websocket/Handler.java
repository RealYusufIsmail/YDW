package websocket;

import io.github.realyusufismail.event.adapter.EventAdapter;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;

public class Handler extends EventAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Ready and the total guilds are: " + event.getNumberOfGuilds());
    }
}
