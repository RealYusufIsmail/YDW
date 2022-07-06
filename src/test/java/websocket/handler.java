package websocket;

import io.github.realyusufismail.event.adapter.EventAdapter;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;

public class handler extends EventAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("ReadyEvent");

    }
}
