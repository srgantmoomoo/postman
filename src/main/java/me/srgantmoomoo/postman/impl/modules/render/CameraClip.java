package me.srgantmoomoo.postman.impl.modules.render;

import me.srgantmoomoo.postman.backend.event.Event;
import me.srgantmoomoo.postman.backend.event.events.RenderCameraEvent;
import me.srgantmoomoo.postman.backend.event.listener.EventHandler;
import me.srgantmoomoo.postman.backend.event.listener.Listener;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import org.lwjgl.input.Keyboard;

public class CameraClip extends Module {

    @EventHandler
    private final Listener<RenderCameraEvent> onRenderCameraEvent = new Listener<>(Event::cancel);

    public CameraClip() {
        super("cameraClip", "camera clips when in 3rd person.", Keyboard.KEY_NONE, Category.RENDER);
    }

}
