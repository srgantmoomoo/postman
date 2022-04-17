package me.srgantmoomoo.postman.backend.event.events.postman;

import me.srgantmoomoo.postman.framework.module.Module;

public class PostmanModuleEvent {
    public final Module mod;

    public PostmanModuleEvent(final Module mod) {
        super();
        this.mod = mod;
    }
}
