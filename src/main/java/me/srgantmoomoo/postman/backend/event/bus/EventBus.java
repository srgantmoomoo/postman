package me.srgantmoomoo.postman.backend.event.bus;

import me.srgantmoomoo.postman.backend.event.listener.EventHandler;
import me.srgantmoomoo.postman.backend.event.listener.Listener;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

public final class EventBus {
    private final ConcurrentHashMap<Class<?>, List<Listener<?>>> LISTENER_REGISTRY = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Object, List<Listener<?>>> LISTENER_CACHE = new ConcurrentHashMap<>();

    private final CopyOnWriteArraySet<Object> SUBSCRIBER_REGISTRY = new CopyOnWriteArraySet<>();

    public void register(Listener<?> listener) {
        List<Listener<?>> listeners = LISTENER_REGISTRY.computeIfAbsent(listener.getTarget(), target -> new CopyOnWriteArrayList<>());

        int index = 0;
        for (; index < listeners.size(); index++) {
            if (listener.getPriority() > listeners.get(index).getPriority()) {
                break;
            }
        }

        listeners.add(index, listener);
    }

    public void unregister(Listener<?> listener) {
        LISTENER_REGISTRY.get(listener.getTarget()).removeIf(l -> l.equals(listener));
    }

    public void register(Object subscriber) {
        List<Listener<?>> listeners = LISTENER_CACHE.computeIfAbsent(subscriber, object -> Arrays.stream(object.getClass().getDeclaredFields()).filter(this::isValid).map(field -> asListener(field, object)).filter(Objects::nonNull).collect(Collectors.toList()));

        listeners.forEach(this::register);
    }

    public void unregister(Object subscriber) {
        List<Listener<?>> listeners = LISTENER_CACHE.get(subscriber);

        if (listeners == null) return;

        LISTENER_REGISTRY.values().forEach(list -> list.removeIf(listeners::contains));
    }

    public <T> T post(T event) {
        this.LISTENER_REGISTRY.get(event.getClass()).forEach(listener -> ((Listener<T>) listener).invoke(event));

        return event;
    }

    private <T> Listener<T> asListener(Field field, Object parent) {
        final boolean acc = field.isAccessible();

        try {
            field.setAccessible(true);

            return (Listener<T>) field.get(parent);
        } catch (SecurityException | IllegalAccessException e) {
            e.printStackTrace();

            return null;
        } finally {
            if (!acc) field.setAccessible(false);
        }
    }


    private boolean isRegistered(Object subscriber) {
        return this.SUBSCRIBER_REGISTRY.contains(subscriber);
    }

    private boolean isValid(Field field) {
        return field.getAnnotation(EventHandler.class) != null && Listener.class.isAssignableFrom(field.getType());
    }
}
