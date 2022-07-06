package event;

import io.github.realyusufismail.event.recieve.IEventReceiver;
import io.github.realyusufismail.ydw.event.Event;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;

public class Test implements IEventReceiver {


    public void onEvent(Event event) {
        if (event instanceof ReadyEvent) {
            System.out
                .println("The number of guilds is " + ((ReadyEvent) event).getNumberOfGuilds());
        }
    }
}
