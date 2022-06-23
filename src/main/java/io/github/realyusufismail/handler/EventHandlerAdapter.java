package io.github.realyusufismail.handler;

import io.github.realyusufismail.handler.util.ClassWalker;
import io.github.realyusufismail.ydw.event.BasicEvent;
import io.github.realyusufismail.ydw.event.EventExtender;
import io.github.realyusufismail.ydw.event.events.ReadyEvent;
import io.github.realyusufismail.ydw.event.events.ReconnectEvent;
import io.github.realyusufismail.ydw.event.events.ResumedEvent;
import io.github.realyusufismail.ydw.event.events.interaction.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class EventHandlerAdapter implements EventHandler {

    public void onBasicEvent(BasicEvent event) {}

    public void onReadyEvent(ReadyEvent event) {}

    public void onReconnectEvent(ReconnectEvent event) {}

    public void onResumeEvent(ResumedEvent event) {}

    public void onSlashCommandInteractionEvent(SlashCommandInteractionEvent event) {}


    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();
    private static final ConcurrentMap<Class<?>, MethodHandle> methods = new ConcurrentHashMap<>();
    private static final Set<Class<?>> unresolved;
    static {
        unresolved = ConcurrentHashMap.newKeySet();
        Collections.addAll(unresolved, Object.class, // Objects aren't events
                EventExtender.class, // onEvent is final and would never be found
                UpdateEvent.class, // onBasicEvent has already been called
                BasicEvent.class // onBasicEvent has already been called
        );
    }

    @Override
    public void onEvent(BasicEvent event) {
        onBasicEvent(event);

        if (event instanceof UpdateEvent)
            onBasicEvent((UpdateEvent<?, ?>) event);

        for (Class<?> clazz : ClassWalker.range(event.getClass(), BasicEvent.class)) {
            if (unresolved.contains(clazz))
                continue;

            MethodHandle mh = methods.computeIfAbsent(clazz, EventHandlerAdapter::findMethod);
            if (mh == null) {
                unresolved.add(clazz);
                continue;
            }

            try {
                mh.invoke(this, event);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private static MethodHandle findMethod(@NotNull Class<?> clazz) {
        String name = clazz.getSimpleName();
        MethodType type = MethodType.methodType(Void.TYPE, clazz);
        try {
            name = "on" + name.substring(0, name.length() - "Event".length());
            return lookup.findVirtual(EventHandlerAdapter.class, name, type);
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
        } // this means this is probably a custom event!
        return null;
    }
}
