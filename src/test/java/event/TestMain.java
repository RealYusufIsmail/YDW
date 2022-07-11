package event;

import io.github.realyusufismail.event.recieve.EventReceiver;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;

public class TestMain {
    public static void main(String[] args) {
        EventReceiver eventReceiver = new EventReceiver();
        Test test = new Test();
        eventReceiver.addEventReceiver(test);

        test.onEvent(new ReadyEvent(null, 2, 2));
    }
}
