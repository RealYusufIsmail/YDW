package websocket;

import io.github.realyusufismail.handler.EventHandlerAdapter;
import io.github.realyusufismail.websocket.event.events.ReadyEvent;

public class TestHandler extends EventHandlerAdapter {

    @Override
    public void onReadyEvent(ReadyEvent event) {
        System.out.println("Ready!");
    }
}
