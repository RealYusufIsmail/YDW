package io.github.realyusufismail.event.adapter;

import io.github.realyusufismail.event.recieve.IEventReceiver;
import io.github.realyusufismail.event.recieve.util.ClassWalker;
import io.github.realyusufismail.event.updater.IEventUpdate;
import io.github.realyusufismail.ydw.event.BasicEvent;
import io.github.realyusufismail.ydw.event.Event;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;
import io.github.realyusufismail.ydw.event.events.ReconnectEvent;
import io.github.realyusufismail.ydw.event.events.ResumedEvent;
import io.github.realyusufismail.ydw.event.events.channel.ChannelCreateEvent;
import io.github.realyusufismail.ydw.event.events.interaction.SlashCommandInteractionEvent;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class EventAdapter implements IEventReceiver {

    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();
    private static final ConcurrentMap<Class<?>, MethodHandle> methods = new ConcurrentHashMap<>();
    private static final Set<Class<?>> unresolved;

    static {
        unresolved = ConcurrentHashMap.newKeySet();
        Collections.addAll(unresolved, Object.class, // Objects aren't events
                Event.class, // onEvent is final and would never be found
                IEventUpdate.class, // onBasicUpdate has already been called
                BasicEvent.class // onBasicEvent has already been called
        );
    }

    private static MethodHandle findMethod(Class<?> clazz) {
        String name = clazz.getSimpleName();
        MethodType type = MethodType.methodType(Void.TYPE, clazz);
        try {
            name = "on" + name.substring(0, name.length() - "Event".length());
            return lookup.findVirtual(EventAdapter.class, name, type);
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
        } // this means this is probably a custom event!
        return null;
    }

    public void onBasicEvent(BasicEvent event) {}

    public void onBasicUpdate(IEventUpdate<?, ?> eventUpdate) {}

    public void onReady(ReadyEvent event) {}

    public void onResumed(ResumedEvent event) {}

    public void onReconnect(ReconnectEvent event) {}

    // interaction
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {}

    // channel
    public void onChannelCreate(ChannelCreateEvent event) {}

    public void onChannelDelete(ChannelCreateEvent event) {}

    /**
     * This method is called when an event is received.
     *
     * @param event The event that was received.
     */
    @Override
    public final void onEvent(Event event) {
        onBasicEvent(event);

        if (event instanceof IEventUpdate)
            onBasicUpdate((IEventUpdate<?, ?>) event);

        for (Class<?> clazz : ClassWalker.range(event.getClass(), BasicEvent.class)) {
            if (unresolved.contains(clazz))
                continue;
            MethodHandle mh = methods.computeIfAbsent(clazz, EventAdapter::findMethod);
            if (mh == null) {
                unresolved.add(clazz);
                continue;
            }

            try {
                mh.invoke(this, event);
            } catch (Throwable throwable) {
                if (throwable instanceof RuntimeException)
                    throw (RuntimeException) throwable;
                if (throwable instanceof Error)
                    throw (Error) throwable;
                throw new IllegalStateException(throwable);
            }
        }
    }
}
