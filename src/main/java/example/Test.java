package example;

import io.github.realyusufismail.websocket.Client;
import io.github.realyusufismail.websocket.event.events.ReadyEvent;

public class Test {
    Client client = new Client();

   public void test() {
       client.onEvent(ReadyEvent.class, event -> {
           
       });
   }
}
