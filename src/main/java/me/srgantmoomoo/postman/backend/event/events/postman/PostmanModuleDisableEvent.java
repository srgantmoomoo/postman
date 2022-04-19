package me.srgantmoomoo.postman.backend.event.events.postman;

import me.srgantmoomoo.postman.framework.module.Module;

public class PostmanModuleDisableEvent extends PostmanModuleEvent {
    public PostmanModuleDisableEvent(Module mod) {
        super(mod);
    }
}
