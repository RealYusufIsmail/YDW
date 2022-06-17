package io.github.realyusufismail.handler.util;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ClassWalker implements Iterable<Class<?>>{
    private final Class<?> clazz;
    private final Class<?> interfaces;

    public ClassWalker(Class<?> clazz, Class<?> interfaces) {
        this.clazz = clazz;
        this.interfaces = interfaces;
    }

    public static ClassWalker range(Class<?> start, Class<?> interfaces)
    {
        return new ClassWalker(start, interfaces);
    }

    @NotNull
    @Override
    public Iterator<Class<?>> iterator() {
        return new Iterator<Class<?>>() {
            private final Set<Class<?>> done = new HashSet<>();
            private final Deque<Class<?>> work = new LinkedList<>();

            {
                work.addLast(clazz);
                done.add(interfaces);
            }

            @Override
            public boolean hasNext()
            {
                return !work.isEmpty();
            }

            @Override
            public Class<?> next()
            {
                Class<?> current = work.removeFirst();
                done.add(current);
                for (Class<?> parent : current.getInterfaces())
                {
                    if (!done.contains(parent))
                        work.addLast(parent);
                }

                Class<?> parent = current.getSuperclass();
                if (parent != null && !done.contains(parent))
                    work.addLast(parent);
                return current;
            }
        };
    }
}
