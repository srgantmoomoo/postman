package me.srgantmoomoo.postman.backend.event.events.postman;

import me.srgantmoomoo.postman.framework.module.Module;

public class PostmanModuleEnableEvent extends PostmanModuleEvent {
    public PostmanModuleEnableEvent(Module mod) {
        super(mod);
    }
}
