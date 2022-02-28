package me.srgantmoomoo.postman.impl.modules.render;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.events.RenderCameraEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class CameraClip extends Module {
	
	public CameraClip() {
		super ("cameraClip", "camera clips when in 3rd person.", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	@EventHandler
    private Listener<RenderCameraEvent> onRenderCameraEvent = new Listener<>(event -> {
        event.cancel();
    });

}
