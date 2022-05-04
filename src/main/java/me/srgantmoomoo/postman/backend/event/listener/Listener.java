package me.srgantmoomoo.postman.backend.event.listener;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;
import java.util.function.Consumer;

public class Listener<T> {
    private final Class<T> target;

    private final Consumer<T> action;

    private final int priority;

    public Listener(Consumer<T> action, int priority) {
        this.target = (Class<T>) ((ParameterizedType) action.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.action = action;
        this.priority = priority;
    }

    public Listener(Consumer<T> action) {
        // Dumb, I forgot this was easier in kotlin
        this(action, 0);
    }

    public final Class<T> getTarget() {
        return this.target;
    }

    public final Consumer<T> getAction() {
        return this.action;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final void invoke(T event) {
        this.action.accept(Objects.requireNonNull(event));
    }
}
