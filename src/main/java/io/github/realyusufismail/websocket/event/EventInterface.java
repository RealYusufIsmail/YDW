package io.github.realyusufismail.websocket.event;

import reactor.core.publisher.Flux;

public interface EventInterface {

    <EventClass extends Event> Flux<EventClass> onEvent(Class<EventClass> eventClass);
}
